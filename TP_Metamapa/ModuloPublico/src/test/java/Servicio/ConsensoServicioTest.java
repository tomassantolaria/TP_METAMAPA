package Servicio;

import Modelos.Entidades.*;
import Modelos.HechoDTO;
import Repositorio.ColeccionRepositorio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsensoServicioTest {

    private ConsensoServicio consensoServicio;
    private ColeccionRepositorio coleccionRepositorio;

    @BeforeEach
    void setUp() {
        coleccionRepositorio = mock(ColeccionRepositorio.class);
        consensoServicio = new ConsensoServicio();
        consensoServicio.coleccionRepositorio = coleccionRepositorio;
    }

    @Test
    void hechosConConsenso_DeberiaRetornarListaDeDTO() {
        Coleccion coleccion = new Coleccion();
        Hecho hecho = new Hecho();
        hecho.setId(1L);
        coleccion.setHechosConsensuados(List.of(hecho));

        when(coleccionRepositorio.findById(1L)).thenReturn(Optional.of(coleccion));

        List<HechoDTO> resultado = consensoServicio.hechosConConsenso(1L);

        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getIdHecho());
    }

    @Test
    void hechosConConsenso_ColeccionNoEncontrada_DeberiaLanzarExcepcion() {
        when(coleccionRepositorio.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> consensoServicio.hechosConConsenso(1L));
    }
}
