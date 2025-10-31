package servicios;

import Modelos.ContribuyenteDTO;
import Modelos.Entidades.Contribuyente;
import Repositorios.ContribuyenteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegistroServicio {

    @Autowired
    ContribuyenteRepositorio contribuyenteRepositorio;


//    public void registrar(ContribuyenteDTO contribuyente){
//        Contribuyente contribuyente_listo = this.crearContribuyente(contribuyente);
//        contribuyenteRepositorio.save(contribuyente_listo);
//    }
//    public Contribuyente crearContribuyente(ContribuyenteDTO contribuyenteDTO){
//        return new Contribuyente(contribuyenteDTO.getUsuario(), contribuyenteDTO.getNombre(), contribuyenteDTO.getApellido(), contribuyenteDTO.getFecha_nacimiento());
//    }

    public void registrar(ContribuyenteDTO contribuyenteDTO){

        if (contribuyenteRepositorio.findByKeycloakId(contribuyenteDTO.getKeycloakId()) != null) {
            throw new RuntimeException("El contribuyente con ID Keycloak " + contribuyenteDTO.getKeycloakId() + " ya existe.");
        }
        Contribuyente contribuyente_listo = this.crearContribuyente(contribuyenteDTO);
        contribuyenteRepositorio.save(contribuyente_listo);
    }

    public Contribuyente crearContribuyente(ContribuyenteDTO contribuyenteDTO){
        Contribuyente contribuyente = new Contribuyente();

        contribuyente.setKeycloakId(contribuyenteDTO.getKeycloakId()); // Asignar el ID de Keycloak al campo dedicado

        contribuyente.setUsuario( contribuyenteDTO.getUsuario()); // Mantener el nombre de usuario
        contribuyente.setNombre(contribuyenteDTO.getNombre());
        contribuyente.setApellido(contribuyenteDTO.getApellido());
        contribuyente.setFecha_nacimiento(contribuyenteDTO.getFecha_nacimiento());



        return contribuyente;
    }

}
