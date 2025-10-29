package Servicio;

import Modelos.DTOS.HechoDTO;
import Modelos.Entidades.Archivo;
import Modelos.Entidades.Hecho;
import Modelos.Entidades.HechoCSV;
import Repositorio.ArchivoRepository;
import Repositorio.HechosRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FuenteEstaticaTest {

    // --- Mocks para las dependencias @Autowired ---
    @Mock
    private HechosRepositorio repositorio;

    @Mock
    private ArchivoRepository archivoRepository;

    // --- Mock para la dependencia instanciada manualmente ---
    // Vamos a mockear la *interfaz*
    @Mock
    private Importador importador;

    // --- Instancia bajo prueba ---
    // @InjectMocks inyectará los repositorios, pero no el importador
    @InjectMocks
    private FuenteEstatica fuenteEstatica;

    // --- Stubs de Modelos (para reutilizar en tests) ---
    private Archivo mockArchivo;
    private Hecho mockHecho;

    @BeforeEach
    void setUp() throws Exception {
        // --- Inyección por Reflexión ---
        // Usamos reflexión para forzar el campo 'importador' (que es private)
        // y reemplazar el 'new ImportadorFileServerLocal()' con nuestro mock.
        // Esto aísla el test del sistema de archivos real.
        Field importadorField = FuenteEstatica.class.getDeclaredField("importador");
        importadorField.setAccessible(true);
        importadorField.set(fuenteEstatica, importador);

        // --- Configuración de Mocks Comunes ---
        mockArchivo = new Archivo();
        mockArchivo.setId(1L);
        mockArchivo.setPath("test/path.csv");

        mockHecho = new Hecho(
                false,
                LocalDateTime.now().minusDays(1),
                "-58.123",
                "-34.456",
                "Robo",
                mockArchivo,
                "Descripción de prueba",
                "Titulo de prueba"
        );
    }

    @Test
    void testGetHechosNoEnviados_ConvierteYActualizaCorrectamente() {
        // Given (Dado)
        // 1. Configuramos el repositorio para que devuelva nuestro hecho mock
        when(repositorio.findAllByProcesadoFalse()).thenReturn(List.of(mockHecho));

        // 2. (Opcional pero bueno) Mockeamos el save para que devuelva el mismo hecho
        when(repositorio.save(any(Hecho.class))).thenReturn(mockHecho);

        // When (Cuando)
        List<HechoDTO> dtos = fuenteEstatica.getHechosNoEnviados();

        // Then (Entonces)
        // 1. Verificamos que se buscaron los hechos no procesados
        verify(repositorio).findAllByProcesadoFalse();

        // 2. Verificamos que el hecho se marcó como procesado
        // (Usamos un ArgumentCaptor para asegurar que setProcesado(true) fue llamado *antes* de save)
        ArgumentCaptor<Hecho> hechoCaptor = ArgumentCaptor.forClass(Hecho.class);
        verify(repositorio).save(hechoCaptor.capture());
        assertTrue(hechoCaptor.getValue().getProcesado(), "El hecho debió marcarse como procesado");

        // 3. Verificamos que la lista de DTOs no está vacía y tiene 1 elemento
        assertEquals(1, dtos.size());

        // 4. Verificamos que la conversión de Hecho a HechoDTO fue correcta
        HechoDTO dto = dtos.get(0);
        assertEquals(mockHecho.getTitulo(), dto.getTitulo());
        assertEquals(mockHecho.getDescripcion(), dto.getDescripcion());
        assertEquals(mockHecho.getCategoria(), dto.getCategoria());
        assertEquals(mockHecho.getArchivo().getId(), dto.getIdFuente());
        assertEquals(Double.parseDouble(mockHecho.getLatitud()), dto.getLatitud());
        assertEquals(Double.parseDouble(mockHecho.getLongitud()), dto.getLongitud());
    }

    @Test
    void testGetHechosNoEnviados_CuandoNoHayHechos() {
        // Given
        when(repositorio.findAllByProcesadoFalse()).thenReturn(Collections.emptyList());

        // When
        List<HechoDTO> dtos = fuenteEstatica.getHechosNoEnviados();

        // Then
        assertTrue(dtos.isEmpty(), "La lista de DTOs debería estar vacía");
        verify(repositorio).findAllByProcesadoFalse();
        verify(repositorio, never()).save(any(Hecho.class)); // Verificamos que save NUNCA se llamó
    }

    @Test
    void testCargarHechos_ProcesaArchivoNuevoCorrectamente() throws Exception {
        // Given
        String pathNuevo = "nuevo/archivo.csv";

        // 1. Mockeamos el importador para que devuelva un path
        when(importador.getPaths()).thenReturn(List.of(pathNuevo));

        // 2. Mockeamos el ArchivoRepository para que diga "no encontré este path"
        when(archivoRepository.findByPath(pathNuevo)).thenReturn(Optional.empty());

        // 3. Creamos HechosCSV de prueba
        HechoCSV hechoPasado = HechoCSV.getInstance("Titulo 1", "Desc 1", "Robo", LocalDate.now().minusDays(1), "-34.1", "-58.1");
        HechoCSV hechoFuturo = HechoCSV.getInstance("Titulo 2", "Desc 2", "Hurto", LocalDate.now().plusDays(1), "-34.2", "-58.2"); // Este debe ser ignorado

        // 4. Mockeamos el importador para que devuelva estos hechos
        when(importador.getHechoFromFile(pathNuevo)).thenReturn(List.of(hechoPasado, hechoFuturo));

        // 5. ArgumentCaptors para capturar lo que se guarda
        ArgumentCaptor<Archivo> archivoCaptor = ArgumentCaptor.forClass(Archivo.class);
        ArgumentCaptor<Hecho> hechoCaptor = ArgumentCaptor.forClass(Hecho.class);

        // When
        fuenteEstatica.cargarHechos();

        // Then
        // 1. Verificamos que se buscó el path
        verify(archivoRepository).findByPath(pathNuevo);

        // 2. Verificamos que se leyeron los hechos del archivo
        verify(importador).getHechoFromFile(pathNuevo);

        // 3. Verificamos que se guardó un *nuevo* Archivo en el repo
        verify(archivoRepository).save(archivoCaptor.capture());
        assertEquals(pathNuevo, archivoCaptor.getValue().getPath());

        // 4. Verificamos que SOLO se guardó el hecho del pasado (1 solo)
        verify(repositorio, times(1)).save(hechoCaptor.capture());

        // 5. Verificamos que el hecho guardado es el correcto
        assertEquals("Titulo 1", hechoCaptor.getValue().getTitulo());
        assertEquals("Desc 1", hechoCaptor.getValue().getDescripcion());
    }

    @Test
    void testCargarHechos_OmiteArchivoExistenteNoModificado() throws Exception {
        // Given
        String pathExistente = "existente/archivo.csv";

        // 1. Mockeamos el importador
        when(importador.getPaths()).thenReturn(List.of(pathExistente));

        // 2. Mockeamos el repo para que devuelva un Archivo existente
        when(archivoRepository.findByPath(pathExistente)).thenReturn(Optional.of(mockArchivo));

        // 3. NOTA IMPORTANTE:
        // Tu método 'esPosteriorAUltimaCarga' usa 'new File(path)'.
        // Como el path 'existente/archivo.csv' no existe en el entorno de test,
        // 'new File().exists()' será 'false', y el método devolverá 'false'.
        // La lógica del 'if' es: if (archivo != null && !esPosteriorAUltimaCarga)
        // Se traducirá a: if (true && !false) -> if (true && true) -> continue.
        // Este test prueba EXACTAMENTE ese comportamiento de "omisión".

        // When
        fuenteEstatica.cargarHechos();

        // Then
        // Verificamos que el flujo se detuvo después de encontrar el archivo
        verify(archivoRepository).findByPath(pathExistente);

        // Verificamos que NUNCA se intentó leer el archivo o guardar nada
        verify(importador, never()).getHechoFromFile(anyString());
        verify(archivoRepository, never()).save(any(Archivo.class));
        verify(repositorio, never()).save(any(Hecho.class));
    }

    // --- Nota sobre Cobertura ---
    // El caso de "procesar un archivo existente que SÍ fue modificado"
    // es (como vimos en el test anterior) muy difícil de probar unitariamente
    // porque tu método 'esPosteriorAUltimaCarga' crea una dependencia
    // interna ('new File(path)') que no podemos mockear.
    //
    // Para testear esa rama, necesitarías "refactorizar" el código
    // para inyectar esa dependencia. Por ejemplo:
    // private Boolean esPosterior(File archivo) { ... }
    // O mejor aún, mover toda la lógica de 'File' al Importador.
}