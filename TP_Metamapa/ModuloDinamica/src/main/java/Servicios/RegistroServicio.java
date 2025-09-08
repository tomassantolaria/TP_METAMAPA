package Servicios;

import Modelos.ContribuyenteDTO;
import Modelos.Entidades.Contribuyente;
import Repositorios.ContribuyenteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class RegistroServicio {

    @Autowired
    ContribuyenteRepositorio contribuyenteRepositorio;


    public void registrar(ContribuyenteDTO contribuyente){
        Contribuyente contribuyente_listo = this.crearContribuyente(contribuyente);
        contribuyenteRepositorio.save(contribuyente_listo);
    }
    public Contribuyente crearContribuyente(ContribuyenteDTO contribuyenteDTO){
        return new Contribuyente(contribuyenteDTO.getUsuario(), contribuyenteDTO.getNombre(), contribuyenteDTO.getApellido(), contribuyenteDTO.getFecha_nacimiento());
    }

}
