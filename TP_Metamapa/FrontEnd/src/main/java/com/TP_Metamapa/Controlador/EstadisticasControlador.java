package com.TP_Metamapa.Controlador;

import com.TP_Metamapa.DTOS.ColeccionDTO;
import com.TP_Metamapa.DTOS.UltimasEstadisticasDTO;
import com.TP_Metamapa.Servicio.ColeccionServicio;
import com.TP_Metamapa.Servicio.EstadisticasServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class EstadisticasControlador {

    @Autowired
    private EstadisticasServicio estadisticasServicio;
    @Autowired
    private ColeccionServicio coleccionServicio;

    @GetMapping("/estadisticas")
    public String mostrarEstadisticas(
            @RequestParam(required = false) Long coleccionSeleccionadaId,
            @RequestParam(required = false) String categoriaSeleccionada,
            @RequestParam(required = false) String categoriaParaHoraPico,
            Model model
    ) {
        List<ColeccionDTO> colecciones = coleccionServicio.getColecciones();
        UltimasEstadisticasDTO estadisticas = estadisticasServicio.obtenerEstadisticas();

        // Simula la llamada para traer TODAS las estadísticas
        //UltimasEstadisticasDTO estadisticas = new UltimasEstadisticasDTO();
        /*estadisticas.setProvinciaConMasHechosPorColeccion(Map.of(1L, "Buenos Aires", 2L, "Tucumán"));
        estadisticas.setCategoriaConMasHechos("Cultura");
        estadisticas.setProvinciaConMasHechosDeCategoria(Map.of("Cultura", "Córdoba", "Historia", "Salta"));
        estadisticas.setHoraConMasHechosPorCategoria(Map.of("Cultura", 15, "Historia", 18));
        estadisticas.setCantidadSolicitudesSpam(20L);
        estadisticas.setHoraConMasHechosPorCategoria(Map.of("Cultura", 15, "Historia", 18, "Deportes", 21));
*/
        // Simula la llamada para traer las colecciones para el dropdown
        /*List<ColeccionDTO> colecciones = List.of(
                new ColeccionDTO(1L, "Avistamientos de Fauna", "...", null, null, null, null),
                new ColeccionDTO(2L, "Sucesos Históricos", "...", null, null, null, null)
        );*/


        // Busca la provincia para la colección seleccionada
        String provinciaPorColeccion = null;
        if (coleccionSeleccionadaId != null) {
            provinciaPorColeccion = estadisticas.getProvinciaConMasHechosPorColeccion().get(coleccionSeleccionadaId);
        }

        // Busca la provincia para la categoría seleccionada
        String provinciaPorCategoria = null;
        if (categoriaSeleccionada != null) {
            provinciaPorCategoria = estadisticas.getProvinciaConMasHechosDeCategoria().get(categoriaSeleccionada);
        }

        Integer horaPicoParaCategoria = null;
        if (categoriaParaHoraPico != null) {
            horaPicoParaCategoria = estadisticas.getHoraConMasHechosPorCategoria().get(categoriaParaHoraPico);
        }

        model.addAttribute("estadisticas", estadisticas);
        model.addAttribute("colecciones", colecciones);

        // Los datos específicos para las tarjetas interactivas
        model.addAttribute("provinciaPorColeccion", provinciaPorColeccion);
        model.addAttribute("provinciaPorCategoria", provinciaPorCategoria);

        // Los valores seleccionados actualmente por el usuario
        model.addAttribute("coleccionSeleccionadaId", coleccionSeleccionadaId);
        model.addAttribute("categoriaSeleccionada", categoriaSeleccionada);

        model.addAttribute("horaPicoParaCategoria", horaPicoParaCategoria);
        model.addAttribute("categoriaParaHoraPico", categoriaParaHoraPico);

        model.addAttribute("activePage", "estadisticas");
        return "estadisticas";
    }

    @GetMapping("/csv")
    public ResponseEntity<String> generarCSV() {
        String csv = estadisticasServicio.exportarCSV();

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=estadisticas.csv")
                .body(csv);
    }
}