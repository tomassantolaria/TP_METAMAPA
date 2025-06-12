package Servicio;

import Modelos.DTOs.ContribuyenteDTOInput;
import Modelos.Entidades.Contribuyente;
import Modelos.Repositorio.ContribuyenteRepositorio;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistroServicio {
    private final ContribuyenteRepositorio contribuyenteRepositorio;

    public RegistroServicio(ContribuyenteRepositorio contribuyenteRepositorio) {
        this.contribuyenteRepositorio = contribuyenteRepositorio;}

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
