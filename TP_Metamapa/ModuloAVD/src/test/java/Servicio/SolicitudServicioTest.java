package Servicio;

import Modelos.DTOs.SolicitudDTOOutput;
import Modelos.Entidades.*;
import Modelos.Exceptions.EstadoInvalidoException;
import Modelos.Exceptions.SolicitudInvalidaException;
import Repositorio.SolicitudRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime ;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SolicitudServicioTest {

    @Mock
    SolicitudRepositorio solicitudRepositorio;
    @Mock
    ColeccionServicio coleccionServicio;

    @InjectMocks
    SolicitudServicio solicitudServicio;

    private Solicitud solicitud;

    @BeforeEach
    void setUp() {
        Hecho hecho = new Hecho();
        hecho.setId(10L);

        solicitud = new Solicitud();
        solicitud.setIdSolcitud(1L);
        solicitud.setMotivo("Prueba");
        solicitud.setHecho(hecho);
        solicitud.setEstado(Estado.PENDIENTE);
        solicitud.setFecha_creacion(LocalDateTime.now());
    }

    // ---------- TEST 1 ----------
    @Test
    void solicitudesPendientes_devuelveListaDeDTOs() {
        when(solicitudRepositorio.findByEstado(Estado.PENDIENTE)).thenReturn(List.of(solicitud));

        List<SolicitudDTOOutput> resultado = solicitudServicio.solicitudesPendientes();

        assertEquals(1, resultado.size());
        assertEquals(solicitud.getHecho().getId(), resultado.get(0).getIdHecho());
        verify(solicitudRepositorio).findByEstado(Estado.PENDIENTE);
    }

    // ---------- TEST 2 ----------
    @Test
    void actualizarEstadoSolicitud_existenteAceptada_eliminaHechoYGuarda() {
        when(solicitudRepositorio.findById(1L)).thenReturn(Optional.of(solicitud));

        solicitudServicio.actualizarEstadoSolicitud(1L, "ACEPTADA");

        assertEquals(Estado.ACEPTADA, solicitud.getEstado());
        verify(coleccionServicio).eliminarHecho(10L);
        verify(solicitudRepositorio).save(solicitud);
    }

    // ---------- TEST 3 ----------
    @Test
    void actualizarEstadoSolicitud_existenteRechazada_soloGuarda() {
        when(solicitudRepositorio.findById(1L)).thenReturn(Optional.of(solicitud));

        solicitudServicio.actualizarEstadoSolicitud(1L, "RECHAZADA");

        assertEquals(Estado.RECHAZADA, solicitud.getEstado());
        verify(coleccionServicio, never()).eliminarHecho(anyLong());
        verify(solicitudRepositorio).save(solicitud);
    }

    // ---------- TEST 4 ----------
    @Test
    void actualizarEstadoSolicitud_inexistente_lanzaExcepcion() {
        when(solicitudRepositorio.findById(99L)).thenReturn(Optional.empty());

        assertThrows(SolicitudInvalidaException.class, () ->
                solicitudServicio.actualizarEstadoSolicitud(99L, "ACEPTADA")
        );

        verify(solicitudRepositorio, never()).save(any());
    }

    // ---------- TEST 5 ----------
    @Test
    void crearEstado_valido_devuelveEnumCorrecto() {
        Estado estado = solicitudServicio.crearEstado("ACEPTADA");
        assertEquals(Estado.ACEPTADA, estado);
    }

    // ---------- TEST 6 ----------
    @Test
    void crearEstado_invalido_lanzaExcepcion() {
        assertThrows(EstadoInvalidoException.class, () ->
                solicitudServicio.crearEstado("EN_REVISION")
        );
    }
}
