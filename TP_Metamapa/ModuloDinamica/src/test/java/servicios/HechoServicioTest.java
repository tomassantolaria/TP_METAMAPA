package servicios;

import Modelos.HechoDTOInput;
import Modelos.Entidades.*;
import Repositorios.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        dtoInput.setFechaAcontecimiento(LocalDate.now());
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
        Contribuyente c = new Contribuyente("usuario1", "Juan", "Pérez", LocalDate.of(1990, 1, 1));
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
        Contribuyente c = new Contribuyente("usuario1", "Juan", "Pérez", LocalDate.of(1990, 1, 1));
        Pais pais = new Pais("Argentina");
        Provincia prov = new Provincia("Buenos Aires", pais);
        Localidad loc = new Localidad("La Plata", prov);
        Ubicacion ub = new Ubicacion(loc, prov, pais, -34.0, -57.0);

        when(categoriaRepositorio.findByNombre(any())).thenReturn(cat);
        when(contribuyenteRepositorio.findByUsuario(any())).thenReturn(c);
        when(paisRepositorio.findByPais(any())).thenReturn(pais);
        when(provinciaRepositorio.findByProvinciaAndPais(any(), any())).thenReturn(prov);
        when(localidadRepositorio.findByLocalidadAndProvincia(any(), any())).thenReturn(loc);
        when(ubicacionRepositorio.findByLatitudAndLongitud(any(), any())).thenReturn(ub);

        hechoServicio.crearHecho(dtoInput);

        verify(hechoRepositorio).save(any(Hecho.class));
    }
}
