package Servicio;

import Controlador.Modelos.DTOs.ContribuyenteDTOInput;
import Controlador.Modelos.Entidades.Contribuyente;
import Repositorio.ContribuyenteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class RegistroServicio {

    @Autowired
    private ContribuyenteRepositorio contribuyenteRepositorio;

    public void registrar(@RequestBody ContribuyenteDTOInput contribuyente){
        Contribuyente contribuyente_listo = this.crearContribuyente(contribuyente);
        contribuyenteRepositorio.agregarContribuyente(contribuyente_listo);
    }
    public Contribuyente crearContribuyente(ContribuyenteDTOInput contribuyenteDTO){
        Contribuyente contribuyente = new Contribuyente();
        contribuyente.setUsuario( contribuyenteDTO.getUsuario());
        contribuyente.setNombre(contribuyenteDTO.getNombre());
        contribuyente.setApellido(contribuyenteDTO.getApellido());
        String fechaString = contribuyenteDTO.getFecha_nacimiento();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse(fechaString, formatter);
        contribuyente.setFecha_nacimiento(fecha);
        return contribuyente;
    }

}
