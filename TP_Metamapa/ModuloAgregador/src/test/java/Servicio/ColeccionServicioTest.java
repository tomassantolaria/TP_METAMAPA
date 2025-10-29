package Servicio;

import Modelos.Entidades.*;
import Modelos.Entidades.Consenso.Consenso;
import Repositorio.ColeccionRepositorio;
import Repositorio.HechoRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ColeccionServicioTest {

    @Mock
    private ColeccionRepositorio coleccionRepositorio;

    @Mock
    private HechoRepositorio hechoRepositorio;

    @InjectMocks
    private ColeccionServicio coleccionServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // --- Caso 1: con consenso ---
    @Test
    void actualizarHechosConsensuados_conConsenso_filtraCorrectamente() {
        // Arrange
        Hecho hecho1 = new Hecho();
        hecho1.setId(1L);

        Hecho hecho2 = new Hecho();
        hecho2.setId(2L);

        List<Hecho> hechos = List.of(hecho1, hecho2);

        Consenso consensoMock = mock(Consenso.class);
        when(consensoMock.tieneConsenso(eq(hecho1), any())).thenReturn(true);
        when(consensoMock.tieneConsenso(eq(hecho2), any())).thenReturn(false);

        Coleccion coleccion = new Coleccion();
        coleccion.setHechos(new ArrayList<>(hechos));
        coleccion.setConsenso(consensoMock);

        when(coleccionRepositorio.findAll()).thenReturn(List.of(coleccion));

        // Act
        coleccionServicio.actualizarHechosConsensuados();

        // Assert
        assertEquals(1, coleccion.getHechosConsensuados().size());
        assertTrue(coleccion.getHechosConsensuados().contains(hecho1));
        assertFalse(coleccion.getHechosConsensuados().contains(hecho2));
        verify(coleccionRepositorio, times(1)).save(coleccion);
    }

    // --- Caso 2: sin consenso ---
    @Test
    void actualizarHechosConsensuados_sinConsenso_copiaTodosLosHechos() {
        Hecho hecho1 = new Hecho(); hecho1.setId(1L);
        Hecho hecho2 = new Hecho(); hecho2.setId(2L);

        Coleccion coleccion = new Coleccion();
        coleccion.setHechos(List.of(hecho1, hecho2));
        coleccion.setConsenso(null);

        when(coleccionRepositorio.findAll()).thenReturn(List.of(coleccion));

        coleccionServicio.actualizarHechosConsensuados();

        assertEquals(2, coleccion.getHechosConsensuados().size());
        verify(coleccionRepositorio, times(1)).save(coleccion);
    }

    // --- Caso 3: lista vacía ---
    @Test
    void actualizarHechosConsensuados_listaColeccionesVacia_noGuardaNada() {
        when(coleccionRepositorio.findAll()).thenReturn(new ArrayList<>());

        coleccionServicio.actualizarHechosConsensuados();

        verify(coleccionRepositorio, never()).save(any());
    }

    // --- Caso 4: consenso lanza excepción (robustez) ---
    @Test
    void actualizarHechosConsensuados_siConsensoFalla_lanzaExcepcion() {
        Hecho hecho = new Hecho();
        hecho.setId(1L);

        Consenso consensoMock = mock(Consenso.class);
        when(consensoMock.tieneConsenso(any(), any())).thenThrow(new RuntimeException("error interno"));

        Coleccion coleccion = new Coleccion();
        coleccion.setHechos(List.of(hecho));
        coleccion.setConsenso(consensoMock);

        when(coleccionRepositorio.findAll()).thenReturn(List.of(coleccion));

        assertThrows(RuntimeException.class, () -> coleccionServicio.actualizarHechosConsensuados());
    }

}
