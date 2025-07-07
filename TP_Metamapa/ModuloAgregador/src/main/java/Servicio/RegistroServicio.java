package Servicio;

import Modelos.DTOs.ContribuyenteDTOInput;
import Modelos.Entidades.Contribuyente;
import Repositorio.ContribuyenteRepositorio;
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
        contribuyente.setFecha_nacimiento(contribuyenteDTO.getFecha_nacimiento());
        return contribuyente;
    }

}
