package servicios;

import Modelos.HechoDTO;
import Modelos.HechoDTOInput;
import Modelos.Entidades.*;
import Repositorios.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime ;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyDouble;

@ExtendWith(MockitoExtension.class)
class HechoServicioTest {

    @Mock HechoRepositorio hechoRepositorio;
    @Mock ContribuyenteRepositorio contribuyenteRepositorio;
    @Mock CategoriaRepositorio categoriaRepositorio;
    @Mock ProvinciaRepositorio provinciaRepositorio;
    @Mock PaisRepositorio paisRepositorio;
    @Mock LocalidadRepositorio localidadRepositorio;
    @Mock UbicacionRepositorio ubicacionRepositorio;

    @InjectMocks HechoServicio hechoServicio;

    private HechoDTOInput dtoInput;

    @BeforeEach
    void setUp() {
        dtoInput = new HechoDTOInput();
        dtoInput.setCategoria("Ambiente");
        dtoInput.setContenido("Texto de prueba");
        dtoInput.setContenido_multimedia("imagen.png");
        dtoInput.setPais("Argentina");
        dtoInput.setProvincia("Buenos Aires");
        dtoInput.setLocalidad("La Plata");
        dtoInput.setLatitud(-34.9214);
        dtoInput.setLongitud(-57.9544);
        dtoInput.setUsuario("usuario1");
        dtoInput.setTitulo("Título del hecho");
        dtoInput.setDescripcion("Descripción del hecho");
        dtoInput.setFechaAcontecimiento(LocalDateTime.now());
        dtoInput.setAnonimo(false);
    }

    @Test
    void crearCategoria_existente_noCreaNueva() {
        Categoria categoriaExistente = new Categoria("Ambiente");
        when(categoriaRepositorio.findByNombre("Ambiente")).thenReturn(categoriaExistente);

        Categoria resultado = hechoServicio.crearCategoria("Ambiente");

        verify(categoriaRepositorio, never()).save(any());
        assertEquals(categoriaExistente, resultado);
    }

    @Test
    void crearCategoria_nueva_laGuarda() {
        when(categoriaRepositorio.findByNombre("Ambiente")).thenReturn(null);

        Categoria resultado = hechoServicio.crearCategoria("Ambiente");

        verify(categoriaRepositorio).save(any(Categoria.class));
        assertEquals("Ambiente", resultado.getNombre());
    }

    @Test
    void crearContribuyente_existente_retornaCorrecto() {
        Contribuyente c = new Contribuyente("usuario1", "Juan", "Pérez", LocalDateTime.now());
        when(contribuyenteRepositorio.findByUsuario("usuario1")).thenReturn(c);

        Contribuyente resultado = hechoServicio.crearContribuyente("usuario1");

        assertEquals(c, resultado);
    }

    @Test
    void crearContribuyente_inexistente_lanzaExcepcion() {
        when(contribuyenteRepositorio.findByUsuario("usuario1")).thenReturn(null);

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                hechoServicio.crearContribuyente("usuario1")
        );

        assertTrue(ex.getMessage().contains("No existe el contribuyente"));
    }

    @Test
    void crearHecho_conDatosValidos_guardaHecho() {
        Categoria cat = new Categoria("Ambiente");
        Contribuyente c = new Contribuyente("usuario1", "Juan", "Pérez", LocalDateTime.now());
        Pais pais = new Pais("Argentina");
        Provincia prov = new Provincia("Buenos Aires", pais);
        Localidad loc = new Localidad("La Plata", prov);
        Ubicacion ub = new Ubicacion(loc, prov, pais, -34.0, -57.0);

        when(categoriaRepositorio.findByNombre(any())).thenReturn(cat);
        when(contribuyenteRepositorio.findByUsuario(any())).thenReturn(c);
        when(paisRepositorio.findByPais(any())).thenReturn(pais);
        when(provinciaRepositorio.findByProvinciaAndPais(any(), any())).thenReturn(prov);
        when(localidadRepositorio.findByLocalidadAndProvincia(any(), any())).thenReturn(loc);
        when(ubicacionRepositorio.findByLatitudAndLongitud(anyDouble(), anyDouble())).thenReturn(ub);

        hechoServicio.crearHecho(dtoInput);

        verify(hechoRepositorio).save(any(Hecho.class));
    }

    @Test
    void obtenerHechos_devuelveHechosMarcadosComoPublicados() {
        // Arrange: crear hechos no publicados
        Hecho hecho1 = new Hecho();
        hecho1.setPublicado(false);
        hecho1.setTitulo("Hecho 1");
        hecho1.setDescripcion("Descripción 1");

        Hecho hecho2 = new Hecho();
        hecho2.setPublicado(false);
        hecho2.setTitulo("Hecho 2");
        hecho2.setDescripcion("Descripción 2");

        List<Hecho> hechos = List.of(hecho1, hecho2);

        // Simulamos que el repositorio devuelve los hechos no publicados
        when(hechoRepositorio.findByPublicadoFalse()).thenReturn(hechos);

        // Si transformarADTOLista() convierte a DTOs, podés mockearla si no querés testear su lógica acá:
        HechoDTO dto1 = new HechoDTO();
        HechoDTO dto2 = new HechoDTO();
        List<HechoDTO> dtos = List.of(dto1, dto2);
        HechoServicio spyServicio = spy(hechoServicio);
        doReturn(dtos).when(spyServicio).transformarADTOLista(hechos);

        // Act
        var resultado = spyServicio.obtenerHechos();

        // Assert
        assertTrue(hecho1.getPublicado());
        assertTrue(hecho2.getPublicado());
        verify(hechoRepositorio).saveAll(hechos);
        assertEquals(2, resultado.size());
    }

}
