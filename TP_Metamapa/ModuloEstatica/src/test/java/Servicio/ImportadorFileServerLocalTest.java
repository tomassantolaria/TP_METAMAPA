package Servicio;

// Imports de tus clases de Modelo reales
import Modelos.Entidades.HechoCSV;
import Modelos.Entidades.HechosCSV;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ImportadorFileServerLocalTest {

    private ImportadorFileServerLocal importador;

    // JUnit creará una carpeta temporal para nosotros antes de cada test
    @TempDir
    File tempFolder;

    @BeforeEach
    void setUp() {
        importador = new ImportadorFileServerLocal();
    }

    @AfterEach
    void tearDown() throws Exception {
        // IMPORTANTE: Limpiamos el mapa estático en HechoCSV después de cada test
        // para asegurar que los tests estén aislados y no compartan estado.
        Field mapField = HechoCSV.class.getDeclaredField("hechosConTitulos");
        mapField.setAccessible(true);
        Map<String, HechoCSV> mapaEstatico = (Map<String, HechoCSV>) mapField.get(null); // 'null' para campo estático
        mapaEstatico.clear();
    }

    /**
     * Helper para modificar el campo privado 'carpeta' usando reflexión.
     */
    private void setPrivateCarpetaField(File newCarpeta) throws Exception {
        Field carpetaField = ImportadorFileServerLocal.class.getDeclaredField("carpeta");
        carpetaField.setAccessible(true);
        carpetaField.set(importador, newCarpeta);
    }

    @Test
    void testGetPaths_ReturnsFilesAndSkipsDirectories() throws Exception {
        // Given: Creamos archivos y una subcarpeta en el directorio temporal
        File file1 = new File(tempFolder, "data1.csv");
        file1.createNewFile();
        File file2 = new File(tempFolder, "data2.txt");
        file2.createNewFile();
        File subDir = new File(tempFolder, "subfolder");
        subDir.mkdir();

        // Usamos reflexión para apuntar 'carpeta' a nuestro tempFolder
        setPrivateCarpetaField(tempFolder);

        // When
        List<String> paths = importador.getPaths();

        // Then
        assertEquals(2, paths.size());
        assertTrue(paths.contains(file1.getAbsolutePath()), "Debería contener file1");
        assertTrue(paths.contains(file2.getAbsolutePath()), "Debería contener file2");
        assertFalse(paths.contains(subDir.getAbsolutePath()), "No debería contener subdirectorios");
    }

    @Test
    void testGetPaths_ThrowsException_WhenFolderDoesNotExist() throws Exception {
        // Given: Apuntamos 'carpeta' a un directorio que no existe
        File nonExistentFolder = new File(tempFolder, "nonexistent");
        setPrivateCarpetaField(nonExistentFolder);

        // When & Then
        Exception exception = assertThrows(Exception.class, () -> {
            importador.getPaths();
        });

        assertEquals("No existe la carpeta o esta vacia", exception.getMessage());
    }

    @Test
    void testGetHechoFromFile_ParsesCsvCorrectly() throws Exception {
        // Given: Creamos un archivo CSV de prueba en el directorio temporal
        File csvFile = new File(tempFolder, "test.csv");
        try (FileWriter writer = new FileWriter(csvFile)) {
            // Nota: Cambié el orden de las columnas para coincidir con tu parser
            // Título,Descripción,Categoría,Latitud,Longitud,Fecha
            writer.write("Título,Descripción,Categoría,Latitud,Longitud,Fecha\n"); // Header
            writer.write("\"Titulo 1, con coma\",\"Descripción simple\",\"Robo\",-34.123, -58.456 ,01/10/2025\n");
            writer.write("Titulo 2,\"Descripción con \"\"comillas\"\" dobles\",\"Hurto\", -34.789 ,-58.999,02/10/2025\n");
            writer.write("Titulo 3 sin quotes,Desc 3,Cat 3, -35.000, -59.000, 03/10/2025\n");
        }

        // When
        List<HechoCSV> hechos = importador.getHechoFromFile(csvFile.getAbsolutePath());

        // Then
        assertEquals(3, hechos.size());

        // Verificar Hecho 1 (con comas y quotes)
        HechoCSV hecho1 = hechos.get(0);
        assertEquals("Titulo 1, con coma", hecho1.getTitulo());
        assertEquals("Descripción simple", hecho1.getDescripcion());
        assertEquals("Robo", hecho1.getCategoria());
        assertEquals("-34.123", hecho1.getLatitud()); // Corregido
        assertEquals("-58.456", hecho1.getLongitud()); // Corregido
        assertEquals(LocalDate.of(2025, 10, 1), hecho1.getFechaAcontecimiento()); // Corregido

        // Verificar Hecho 2 (con comillas dobles escapadas)
        HechoCSV hecho2 = hechos.get(1);
        assertEquals("Titulo 2", hecho2.getTitulo());
        assertEquals("Descripción con \"comillas\" dobles", hecho2.getDescripcion());
        assertEquals("Hurto", hecho2.getCategoria());
        assertEquals("-34.789", hecho2.getLatitud()); // Corregido
        assertEquals("-58.999", hecho2.getLongitud()); // Corregido
        assertEquals(LocalDate.of(2025, 10, 2), hecho2.getFechaAcontecimiento()); // Corregido

        // Verificar Hecho 3 (sin quotes)
        HechoCSV hecho3 = hechos.get(2);
        assertEquals("Titulo 3 sin quotes", hecho3.getTitulo());
        assertEquals("Desc 3", hecho3.getDescripcion());
        assertEquals("Cat 3", hecho3.getCategoria());
        assertEquals("-35.000", hecho3.getLatitud()); // Corregido
        assertEquals("-59.000", hecho3.getLongitud()); // Corregido
        assertEquals(LocalDate.of(2025, 10, 3), hecho3.getFechaAcontecimiento()); // Corregido
    }

    @Test
    void testGetHechoFromFile_HandlesDuplicatesByTitle() throws Exception {
        // Este test comprueba la lógica de HechoCSV.getInstance() y HechosCSV.addHecho()

        // Given: Un CSV con títulos duplicados
        File csvFile = new File(tempFolder, "duplicates.csv");
        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.write("Título,Descripción,Categoría,Latitud,Longitud,Fecha\n");
            writer.write("Titulo Repetido,\"Desc 1\",\"Robo\",-34.1, -58.1 ,01/10/2025\n");
            writer.write("Titulo Unico,\"Desc 2\",\"Hurto\", -34.2 ,-58.2,02/10/2025\n");
            writer.write("Titulo Repetido,\"Desc 3 Sobreescrita\",\"Robo\", -34.3, -58.3, 03/10/2025\n");
        }

        // When
        List<HechoCSV> hechos = importador.getHechoFromFile(csvFile.getAbsolutePath());

        // Then
        // 1. La lista 'hechos' (de HechosCSV) solo debe contener 2 elementos,
        //    porque 'Titulo Repetido' es la misma instancia y .addHecho lo ignora.
        assertEquals(2, hechos.size());

        // 2. El HechoCSV con "Titulo Repetido" debe tener los datos de la ÚLTIMA aparición
        //    debido a tu lógica de 'sobreescribirse' en HechoCSV.getInstance()
        HechoCSV hechoRepetido = hechos.stream()
                .filter(h -> h.getTitulo().equals("Titulo Repetido"))
                .findFirst()
                .orElse(null);

        HechoCSV hechoUnico = hechos.stream()
                .filter(h -> h.getTitulo().equals("Titulo Unico"))
                .findFirst()
                .orElse(null);

        assertNotNull(hechoRepetido);
        assertNotNull(hechoUnico);

        // Verificamos que el hecho repetido tiene los datos de la última línea
        assertEquals("Desc 3 Sobreescrita", hechoRepetido.getDescripcion());
        assertEquals(LocalDate.of(2025, 10, 3), hechoRepetido.getFechaAcontecimiento());
        assertEquals("-34.3", hechoRepetido.getLatitud());
    }

    @Test
    void testGetHechoFromFile_HandlesEmptyFile() throws Exception {
        // Given: Un archivo solo con cabecera
        File csvFile = new File(tempFolder, "empty.csv");
        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.write("Título,Descripción,Categoría,Latitud,Longitud,Fecha\n");
        }

        // When
        List<HechoCSV> hechos = importador.getHechoFromFile(csvFile.getAbsolutePath());

        // Then
        assertTrue(hechos.isEmpty());
    }

    @Test
    void testUnquoteCsvField() throws Exception {
        // Probamos el método privado 'unquoteCsvField' usando reflexión
        Method unquoteMethod = ImportadorFileServerLocal.class.getDeclaredMethod("unquoteCsvField", String.class);
        unquoteMethod.setAccessible(true);

        // Casos de prueba
        assertEquals("simple", unquoteMethod.invoke(null, "simple"));
        assertEquals("simple", unquoteMethod.invoke(null, "\"simple\""));
        assertEquals("campo con, coma", unquoteMethod.invoke(null, "\"campo con, coma\""));
        assertEquals("campo con \"comillas\"", unquoteMethod.invoke(null, "\"campo con \"\"comillas\"\"\""));
        assertEquals("campo", unquoteMethod.invoke(null, "  \"campo\"  ")); // Verifica trim exterior
        assertEquals("  no trim interno  ", unquoteMethod.invoke(null, "\"  no trim interno  \""));
    }
}