//package Servicio;
//
//import Modelos.Entidades.*;
//import Modelos.Entidades.Consenso.Consenso;
//import Modelos.Entidades.DTOs.HechoDTOInput;
//import Modelos.Entidades.DTOs.UbicacionDTOInput;
//import Modelos.Entidades.DTOs.UbicacionDTOOutput;
//import Modelos.Exceptions.ColeccionNoEncontradaException;
//import Repositorio.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class AgregadorServicioTest {
//
//    @Mock
//    private RestTemplate restTemplate;
//    @Mock
//    private HechoRepositorio hechoRepositorio;
//    @Mock
//    private ColeccionRepositorio coleccionRepositorio;
//    @Mock
//    private CategoriaRepositorio categoriaRepositorio;
//    @Mock
//    private ProvinciaRepositorio provinciaRepositorio;
//    @Mock
//    private PaisRepositorio paisRepositorio;
//    @Mock
//    private LocalidadRepositorio localidadRepositorio;
//    @Mock
//    private UbicacionRepositorio ubicacionRepositorio;
//    @Mock
//    private ContribuyenteRepositorio contribuyenteRepositorio;
//    @Mock
//    private ContenidoRepositorio contenidoRepositorio;
//
//    @InjectMocks
//    private AgregadorServicio agregadorServicio;
//
//    private HechoDTOInput hechoDTOEjemplo;
//    private Pais paisEjemplo;
//    private Provincia provinciaEjemplo;
//    private Localidad localidadEjemplo;
//    private Categoria categoriaEjemplo;
//    private Ubicacion ubicacionEjemplo;
//    private Contribuyente contribuyenteEjemplo;
//    private Contenido contenidoEjemplo;
//
//    @BeforeEach
//    void setUp() {
//        hechoDTOEjemplo = new HechoDTOInput();
//        hechoDTOEjemplo.setIdFuente(1L);
//        hechoDTOEjemplo.setTitulo("Titulo prueba");
//        hechoDTOEjemplo.setDescripcion("Descripcion prueba");
//        hechoDTOEjemplo.setCategoria("Deportes");
//        hechoDTOEjemplo.setPais("Argentina");
//        hechoDTOEjemplo.setProvincia("Buenos Aires");
//        hechoDTOEjemplo.setLocalidad("CABA");
//        hechoDTOEjemplo.setLatitud(-34.6037);
//        hechoDTOEjemplo.setLongitud(-58.3816);
//        hechoDTOEjemplo.setFechaAcontecimiento(LocalDateTime.now());
//        hechoDTOEjemplo.setOrigen_carga("FUENTE_DINAMICA");
//        hechoDTOEjemplo.setUsuario("usuario1");
//        hechoDTOEjemplo.setNombre("Juan");
//        hechoDTOEjemplo.setApellido("Perez");
//        hechoDTOEjemplo.setFecha_nacimiento(LocalDateTime.of(1990, 1, 1, 0, 0));
//        hechoDTOEjemplo.setContenido("Contenido de prueba");
//        hechoDTOEjemplo.setContenido_multimedia("url/imagen.jpg");
//        hechoDTOEjemplo.setAnonimo(false);
//
//        paisEjemplo = new Pais("Argentina");
//        provinciaEjemplo = new Provincia("Buenos Aires", paisEjemplo);
//        localidadEjemplo = new Localidad("CABA", provinciaEjemplo);
//        categoriaEjemplo = new Categoria("Deportes");
//        ubicacionEjemplo = new Ubicacion(localidadEjemplo, provinciaEjemplo, paisEjemplo, -34.6037, -58.3816);
//        contribuyenteEjemplo = new Contribuyente("usuario1", "Juan", "Perez", LocalDateTime.of(1990, 1, 1, 0, 0));
//        contenidoEjemplo = new Contenido("Contenido de prueba", "url/imagen.jpg");
//    }
//
//    // ==================== TESTS ACTUALIZAR HECHOS ====================
//
//    @Test
//    void cuandoActualizarHechos_deberiaConsultarTodasLasFuentes() {
//        // Arrange
//        List<HechoDTOInput> listaVacia = new ArrayList<>();
//        ResponseEntity<List<HechoDTOInput>> respuestaVacia = ResponseEntity.ok(listaVacia);
//
//        when(restTemplate.exchange(
//                anyString(),
//                eq(HttpMethod.GET),
//                isNull(),
//                any(ParameterizedTypeReference.class)
//        )).thenReturn(respuestaVacia);
//
//        when(coleccionRepositorio.findAll()).thenReturn(new ArrayList<>());
//
//        // Act
//        agregadorServicio.actualizarHechos();
//
//        // Assert
//        verify(restTemplate, times(4)).exchange(
//                anyString(),
//                eq(HttpMethod.GET),
//                isNull(),
//                any(ParameterizedTypeReference.class)
//        );
//    }
//
//    @Test
//    void cuandoActualizarHechos_deberiaSetearOrigenCargaCorrectamente() {
//        // Arrange
//        List<HechoDTOInput> hechosDemo = Arrays.asList(hechoDTOEjemplo);
//        ResponseEntity<List<HechoDTOInput>> respuestaDemo = ResponseEntity.ok(hechosDemo);
//        ResponseEntity<List<HechoDTOInput>> respuestaVacia = ResponseEntity.ok(new ArrayList<>());
//
//        // Mockear todas las fuentes específicamente
//        when(restTemplate.exchange(
//                eq("http://localhost:8082/dinamica/hechos"),
//                eq(HttpMethod.GET),
//                isNull(),
//                any(ParameterizedTypeReference.class)
//        )).thenReturn(respuestaVacia);
//
//        when(restTemplate.exchange(
//                eq("http://localhost:8086/demo/hechos"),
//                eq(HttpMethod.GET),
//                isNull(),
//                any(ParameterizedTypeReference.class)
//        )).thenReturn(respuestaDemo);
//
//        when(restTemplate.exchange(
//                eq("http://localhost:8086/metamapa/hechos"),
//                eq(HttpMethod.GET),
//                isNull(),
//                any(ParameterizedTypeReference.class)
//        )).thenReturn(respuestaVacia);
//
//        when(restTemplate.exchange(
//                eq("http://localhost:8084/fuenteEstatica/hechos"),
//                eq(HttpMethod.GET),
//                isNull(),
//                any(ParameterizedTypeReference.class)
//        )).thenReturn(respuestaVacia);
//
//        configurarMocksParaNormalizacion();
//        configurarMocksParaCreacionEntidades();
//
//        when(coleccionRepositorio.findAll()).thenReturn(new ArrayList<>());
//
//        // Act
//        agregadorServicio.actualizarHechos();
//
//        // Assert
//        ArgumentCaptor<List<Hecho>> captor = ArgumentCaptor.forClass(List.class);
//        verify(hechoRepositorio).saveAll(captor.capture());
//        List<Hecho> hechoGuardado = captor.getValue();
//        assertFalse(hechoGuardado.isEmpty());
//    }
//
//    // ==================== TESTS TRANSFORMAR A HECHO ====================
//
//    @Test
//    void cuandoTransformarAHecho_deberiaCrearHechoCorrectamente() {
//        // Arrange
//        List<HechoDTOInput> hechosDTO = Arrays.asList(hechoDTOEjemplo);
//        configurarMocksParaCreacionEntidades();
//
//        // Act
//        List<Hecho> resultado = agregadorServicio.transaformarAHecho(hechosDTO);
//
//        // Assert
//        assertEquals(1, resultado.size());
//        Hecho hecho = resultado.get(0);
//        assertEquals("Titulo prueba", hecho.getTitulo());
//        assertEquals("Descripcion prueba", hecho.getDescripcion());
//        assertEquals(OrigenCarga.FUENTE_DINAMICA, hecho.getOrigen());
//        assertTrue(hecho.getVisible());
//        assertFalse(hecho.getAnonimo());
//    }
//
//    @Test
//    void cuandoTransformarAHecho_deberiaLlamarCrearPaisProvinciaLocalidad() {
//        // Arrange
//        List<HechoDTOInput> hechosDTO = Arrays.asList(hechoDTOEjemplo);
//        configurarMocksParaCreacionEntidades();
//
//        // Act
//        agregadorServicio.transaformarAHecho(hechosDTO);
//
//        // Assert
//        verify(paisRepositorio).findByPais("Argentina");
//        verify(provinciaRepositorio).findByProvinciaAndPais("Buenos Aires", paisEjemplo);
//        verify(localidadRepositorio).findByLocalidadAndProvincia("CABA", provinciaEjemplo);
//    }
//
//    // ==================== TESTS CREAR ENTIDADES ====================
//
//    @Test
//    void cuandoCrearPaisNoExiste_deberiaCrearYGuardarNuevoPais() {
//        // Arrange
//        when(paisRepositorio.findByPais("Argentina")).thenReturn(null);
//
//        // Act
//        Pais resultado = agregadorServicio.crearPais("Argentina");
//
//        // Assert
//        assertNotNull(resultado);
//        assertEquals("Argentina", resultado.getPais());
//        verify(paisRepositorio).save(any(Pais.class));
//    }
//
//    @Test
//    void cuandoCrearPaisYaExiste_deberiaRetornarExistente() {
//        // Arrange
//        when(paisRepositorio.findByPais("Argentina")).thenReturn(paisEjemplo);
//
//        // Act
//        Pais resultado = agregadorServicio.crearPais("Argentina");
//
//        // Assert
//        assertEquals(paisEjemplo, resultado);
//        verify(paisRepositorio, never()).save(any(Pais.class));
//    }
//
//    @Test
//    void cuandoCrearProvinciaNoExiste_deberiaCrearYGuardarNuevaProvincia() {
//        // Arrange
//        when(provinciaRepositorio.findByProvinciaAndPais("Buenos Aires", paisEjemplo)).thenReturn(null);
//
//        // Act
//        Provincia resultado = agregadorServicio.crearProvincia("Buenos Aires", paisEjemplo);
//
//        // Assert
//        assertNotNull(resultado);
//        assertEquals("Buenos Aires", resultado.getProvincia());
//        verify(provinciaRepositorio).save(any(Provincia.class));
//    }
//
//    @Test
//    void cuandoCrearLocalidadNoExiste_deberiaCrearYGuardarNuevaLocalidad() {
//        // Arrange
//        when(localidadRepositorio.findByLocalidadAndProvincia("CABA", provinciaEjemplo)).thenReturn(null);
//
//        // Act
//        Localidad resultado = agregadorServicio.crearLocalidad("CABA", provinciaEjemplo);
//
//        // Assert
//        assertNotNull(resultado);
//        assertEquals("CABA", resultado.getLocalidad());
//        verify(localidadRepositorio).save(any(Localidad.class));
//    }
//
//    @Test
//    void cuandoCrearCategoriaNoExiste_deberiaCrearYGuardarNuevaCategoria() {
//        // Arrange
//        when(categoriaRepositorio.findByNombre("Deportes")).thenReturn(null);
//
//        // Act
//        Categoria resultado = agregadorServicio.crearCategoria("Deportes");
//
//        // Assert
//        assertNotNull(resultado);
//        assertEquals("Deportes", resultado.getNombre());
//        verify(categoriaRepositorio).save(any(Categoria.class));
//    }
//
//    @Test
//    void cuandoCrearUbicacionNoExiste_deberiaCrearYGuardarNuevaUbicacion() {
//        // Arrange
//        when(ubicacionRepositorio.findByLatitudAndLongitud(-34.6037, -58.3816)).thenReturn(null);
//
//        // Act
//        Ubicacion resultado = agregadorServicio.crearUbicacion(
//                -34.6037, -58.3816, localidadEjemplo, provinciaEjemplo, paisEjemplo
//        );
//
//        // Assert
//        assertNotNull(resultado);
//        assertEquals(-34.6037, resultado.getLatitud());
//        assertEquals(-58.3816, resultado.getLongitud());
//        verify(ubicacionRepositorio).save(any(Ubicacion.class));
//    }
//
//    @Test
//    void cuandoCrearContribuyenteConUsuarioNull_deberiaRetornarNull() {
//        // Act
//        Contribuyente resultado = agregadorServicio.crearContribuyente(
//                null, "Juan", "Perez", LocalDateTime.now()
//        );
//
//        // Assert
//        assertNull(resultado);
//        verify(contribuyenteRepositorio, never()).save(any(Contribuyente.class));
//    }
//
//    @Test
//    void cuandoCrearContribuyenteNoExiste_deberiaCrearYGuardarNuevoContribuyente() {
//        // Arrange
//        when(contribuyenteRepositorio.findByUsuario("usuario1")).thenReturn(null);
//
//        // Act
//        Contribuyente resultado = agregadorServicio.crearContribuyente(
//                "usuario1", "Juan", "Perez", LocalDateTime.of(1990, 1, 1, 0, 0)
//        );
//
//        // Assert
//        assertNotNull(resultado);
//        assertEquals("usuario1", resultado.getUsuario());
//        verify(contribuyenteRepositorio).save(any(Contribuyente.class));
//    }
//
//    @Test
//    void cuandoCrearContenidoNoExiste_deberiaCrearYGuardarNuevoContenido() {
//        // Arrange
//        when(contenidoRepositorio.findByTextoAndContenidoMultimedia(
//                "Texto prueba", "url/imagen.jpg"
//        )).thenReturn(null);
//
//        // Act
//        Contenido resultado = agregadorServicio.crearContenido("Texto prueba", "url/imagen.jpg");
//
//        // Assert
//        assertNotNull(resultado);
//        assertEquals("Texto prueba", resultado.getTexto());
//        verify(contenidoRepositorio).save(any(Contenido.class));
//    }
//
//    // ==================== TESTS ACTUALIZAR COLECCIONES ====================
//
//    @Test
//    void cuandoActualizarColecciones_deberiaProcesarTodasLasColecciones() {
//        // Arrange
//        Coleccion coleccion1 = crearColeccionEjemplo(1L);
//        Coleccion coleccion2 = crearColeccionEjemplo(2L);
//        List<Coleccion> colecciones = Arrays.asList(coleccion1, coleccion2);
//
//        when(coleccionRepositorio.findAll()).thenReturn(colecciones);
//        when(hechoRepositorio.filtrarHechos(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
//                .thenReturn(new ArrayList<>());
//
//        // Act
//        agregadorServicio.actualizarColecciones();
//
//        // Assert
//        verify(coleccionRepositorio, times(2)).save(any(Coleccion.class));
//    }
//
//    @Test
//    void cuandoActualizarColeccion_deberiaFiltrarHechosConCriterios() {
//        // Arrange
//        Coleccion coleccion = crearColeccionEjemplo(1L);
//        when(hechoRepositorio.filtrarHechos(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
//                .thenReturn(new ArrayList<>());
//
//        // Act
//        agregadorServicio.actualizarColeccion(coleccion);
//
//        // Assert
//        verify(hechoRepositorio).filtrarHechos(
//                eq("Deportes"),
//                any(),
//                any(),
//                any(),
//                any(),
//                any(),
//                any(),
//                any(),
//                any(),
//                any(),
//                any()
//        );
//    }
//
//    @Test
//    void cuandoActualizarColeccion_deberiaAgregarSoloHechosNuevosYVisibles() {
//        // Arrange
//        Coleccion coleccion = crearColeccionEjemplo(1L);
//
//        Hecho hechoExistente = new Hecho();
//        hechoExistente.setId(1L);
//        hechoExistente.setVisible(true);
//        coleccion.agregarHecho(hechoExistente);
//
//        Hecho hechoNuevoVisible = new Hecho();
//        hechoNuevoVisible.setId(2L);
//        hechoNuevoVisible.setVisible(true);
//
//        Hecho hechoNuevoNoVisible = new Hecho();
//        hechoNuevoNoVisible.setId(3L);
//        hechoNuevoNoVisible.setVisible(false);
//
//        List<Hecho> hechosFiltrados = Arrays.asList(hechoExistente, hechoNuevoVisible, hechoNuevoNoVisible);
//        when(hechoRepositorio.filtrarHechos(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
//                .thenReturn(hechosFiltrados);
//
//        // Act
//        agregadorServicio.actualizarColeccion(coleccion);
//
//        // Assert
//        assertEquals(2, coleccion.getHechos().size());
//        verify(coleccionRepositorio).save(coleccion);
//    }
//
//    // ==================== TESTS SETEAR ORIGEN CARGA ====================
//
//    @Test
//    void cuandoSetearOrigenCarga_deberiaCambiarOrigenEnTodosLosHechos() {
//        // Arrange
//        HechoDTOInput hecho1 = new HechoDTOInput();
//        HechoDTOInput hecho2 = new HechoDTOInput();
//        List<HechoDTOInput> hechos = Arrays.asList(hecho1, hecho2);
//
//        // Act
//        List<HechoDTOInput> resultado = agregadorServicio.setearOrigenCarga(
//                hechos, OrigenCarga.FUENTE_ESTATICA
//        );
//
//        // Assert
//        assertEquals(2, resultado.size());
//        assertEquals("FUENTE_ESTATICA", resultado.get(0).getOrigen_carga());
//        assertEquals("FUENTE_ESTATICA", resultado.get(1).getOrigen_carga());
//    }
//
//    // ==================== TESTS CARGAR COLECCION CON HECHOS ====================
//
//    @Test
//    void cuandoCargarColeccionConHechosExiste_deberiaActualizarColeccion() throws ColeccionNoEncontradaException {
//        // Arrange
//        Long coleccionId = 1L;
//        Coleccion coleccion = crearColeccionEjemplo(coleccionId);
//        when(coleccionRepositorio.findById(coleccionId)).thenReturn(Optional.of(coleccion));
//        when(hechoRepositorio.filtrarHechos(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
//                .thenReturn(new ArrayList<>());
//
//        // Act
//        agregadorServicio.cargarColeccionConHechos(coleccionId);
//
//        // Assert
//        verify(coleccionRepositorio).findById(coleccionId);
//        verify(coleccionRepositorio).save(coleccion);
//    }
//
//    @Test
//    void cuandoCargarColeccionConHechosNoExiste_deberiaLanzarExcepcion() {
//        // Arrange
//        Long coleccionId = 999L;
//        when(coleccionRepositorio.findById(coleccionId)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        assertThrows(ColeccionNoEncontradaException.class, () -> {
//            agregadorServicio.cargarColeccionConHechos(coleccionId);
//        });
//    }
//
//    // ==================== MÉTODOS AUXILIARES ====================
//
//    private void configurarMocksParaNormalizacion() {
//        when(restTemplate.exchange(
//                eq("http://localhost:8085/normalizacion/categorias"),
//                eq(HttpMethod.POST),
//                any(HttpEntity.class),
//                eq(String.class)
//        )).thenReturn(ResponseEntity.ok("Deportes"));
//
//        UbicacionDTOInput ubicacionNormalizada = new UbicacionDTOInput();
//        ubicacionNormalizada.setPais("Argentina");
//        ubicacionNormalizada.setProvincia("Buenos Aires");
//        ubicacionNormalizada.setLocalidad("CABA");
//
//        when(restTemplate.exchange(
//                eq("http://localhost:8085/normalizacion/ubicaciones"),
//                eq(HttpMethod.POST),
//                any(HttpEntity.class),
//                eq(UbicacionDTOInput.class)
//        )).thenReturn(ResponseEntity.ok(ubicacionNormalizada));
//
//        when(restTemplate.exchange(
//                eq("http://localhost:8085/normalizacion/titulos"),
//                eq(HttpMethod.POST),
//                any(HttpEntity.class),
//                eq(String.class)
//        )).thenReturn(ResponseEntity.ok("Titulo prueba"));
//    }
//
////    private void configurarMocksParaCreacionEntidades() {
////        when(paisRepositorio.findByPais(anyString())).thenReturn(paisEjemplo);
////        when(provinciaRepositorio.findByProvinciaAndPais(anyString(), any())).thenReturn(provinciaEjemplo);
////        when(localidadRepositorio.findByLocalidadAndProvincia(anyString(), any())).thenReturn(localidadEjemplo);
////        when(categoriaRepositorio.findByNombre(anyString())).thenReturn(categoriaEjemplo);
////        when(ubicacionRepositorio.findByLatitudAndLongitud(anyDouble(), anyDouble())).thenReturn(ubicacionEjemplo);
////        when(contribuyenteRepositorio.findByUsuario(anyString())).thenReturn(contribuyenteEjemplo);
////        when(contenidoRepositorio.findByTextoAndContenidoMultimedia(anyString(), anyString()))
////                .thenReturn(contenidoEjemplo);
////    }
//
//    private Coleccion crearColeccionEjemplo(Long id) {
//        Coleccion coleccion = new Coleccion();
//        coleccion.setId(id);
//
//        CriteriosDePertenencia criterio = new CriteriosDePertenencia();
//        criterio.setCategoria(categoriaEjemplo);
//        criterio.setUbicacion(ubicacionEjemplo);
//        coleccion.setCriterio_pertenencia(criterio);
//
//        return coleccion;
//    }
//}