package Controlador;

import Modelos.Entidades.Excepciones.ColeccionNotFoundException;
import Modelos.HechoDTO;
import Servicio.ConsensoServicio;
import Servicio.NavegadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("publico/")

public class NavegadorControlador {

    @Autowired
    NavegadorServicio navegadorServicio;
    @Autowired
    ConsensoServicio consensoServicio;

    @GetMapping("colecciones/{id}/hechos")
    public ResponseEntity<?> coleccionFiltrada(@PathVariable Long id,
                                               @RequestParam(required = false) String categoria,
                                               @RequestParam(required = false) Boolean contenidoMultimedia,
                                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaCargaDesde,
                                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaCargaHasta,
                                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHechoDesde,
                                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHechoHasta,
                                               @RequestParam(required = false) String origen,
                                               @RequestParam(required = false) String titulo,
                                               @RequestParam(required = false) String pais,
                                               @RequestParam(required = false) String provincia,
                                               @RequestParam(required = false) String localidad) {

        try {
            List<HechoDTO> hechos = navegadorServicio.filtrarHechos(id, categoria, contenidoMultimedia,
                    fechaCargaDesde, fechaCargaHasta, fechaHechoDesde, fechaHechoHasta,
                    origen, titulo, pais, provincia, localidad);

            return ResponseEntity.ok(hechos);
        } catch (ColeccionNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("hechos")
    public ResponseEntity<?> hechosFiltrados(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Boolean contenidoMultimedia,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaCargaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaCargaHasta,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHechoDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHechoHasta,
            @RequestParam(required = false) String origen,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String pais,
            @RequestParam(required = false) String provincia,
            @RequestParam(required = false) String localidad) {

        try {
            List<HechoDTO> hechos = navegadorServicio.filtrarHechos(null, categoria, contenidoMultimedia,
                    fechaCargaDesde, fechaCargaHasta, fechaHechoDesde, fechaHechoHasta,
                    origen, titulo, pais, provincia, localidad);

            return ResponseEntity.ok(hechos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al filtrar hechos");
        }
    }

    @GetMapping("colecciones/{id}/curada")
    public ResponseEntity<?> hechosConsensuados(@PathVariable Long id) {
        try {
            List<HechoDTO> hechos = consensoServicio.hechosConConsenso(id);
            return ResponseEntity.ok(hechos);
        } catch (ColeccionNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("colecciones/{id}/irrestricta")
    public ResponseEntity<?> hechosIrrestrictos(@PathVariable Long id) {
        try {
            List<HechoDTO> hechos = consensoServicio.hechosIrrestrictos(id);
            return ResponseEntity.ok(hechos);
        } catch (ColeccionNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }


}