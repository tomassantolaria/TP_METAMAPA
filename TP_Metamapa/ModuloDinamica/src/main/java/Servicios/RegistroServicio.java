package Servicios;

import Modelos.ContribuyenteDTO;
import Modelos.Entidades.Contribuyente;
import Repositorios.ContribuyenteRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class RegistroServicio {

    private final ContribuyenteRepositorio contribuyenteRepositorio;

    public RegistroServicio(ContribuyenteRepositorio contribuyenteRepositorio) {
        this.contribuyenteRepositorio = contribuyenteRepositorio;}

    public void registrar(@RequestBody ContribuyenteDTO contribuyente){
        Contribuyente contribuyente_listo = this.crearContribuyente(contribuyente);
        contribuyenteRepositorio.agregarContribuyente(contribuyente_listo);
    }
    public Contribuyente crearContribuyente(ContribuyenteDTO contribuyenteDTO){
        Contribuyente contribuyente = new Contribuyente();
        contribuyente.setUsuario( contribuyenteDTO.getUsuario());
        contribuyente.setNombre(contribuyenteDTO.getNombre());
        contribuyente.setApellido(contribuyenteDTO.getApellido());
        contribuyente.setFecha_nacimiento(contribuyenteDTO.getFecha_nacimiento());
        return contribuyente;
    }

}
