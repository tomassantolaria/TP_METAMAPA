package Servicio;

import Modelos.Entidades.*;
import Modelos.HechoDTO;
import Repositorio.ColeccionRepositorio;
import Repositorio.HechoRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NavegadorServicioTest {

    private NavegadorServicio navegadorServicio;
    private HechoRepositorio hechoRepositorio;
    private ColeccionRepositorio coleccionRepositorio;

    @BeforeEach
    void setUp() {
        hechoRepositorio = mock(HechoRepositorio.class);
        coleccionRepositorio = mock(ColeccionRepositorio.class);
        navegadorServicio = new NavegadorServicio();
        navegadorServicio.hechoRepositorio = hechoRepositorio;
        navegadorServicio.coleccionRepositorio = coleccionRepositorio;
    }

    @Test
    void filtrarHechos_SinColeccion_DeberiaRetornarLista() {
        Hecho hecho = new Hecho();
        hecho.setTitulo("Prueba");
        when(hechoRepositorio.filtrarHechos(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(List.of(hecho));

        List<HechoDTO> resultado = navegadorServicio.filtrarHechos(null, null, null, null, null, null, null, null, null, null, null, null);

        assertEquals(1, resultado.size());
        assertEquals("Prueba", resultado.get(0).getTitulo());
    }

    @Test
    void crearOrigen_DeberiaTransformarCorrectamente() {
        assertEquals(OrigenCarga.FUENTE_DINAMICA, navegadorServicio.crearOrigen("FUENTE_DINAMICA"));
        assertNull(navegadorServicio.crearOrigen(null));
    }

    @Test
    void buscarPorTextoLibre_NoEncontrado_DeberiaLanzarExcepcion() {
        when(hechoRepositorio.buscarTodosVisibles()).thenReturn(List.of());

        assertThrows(RuntimeException.class, () -> navegadorServicio.buscarPorTextoLibre("abc"));
    }
}

