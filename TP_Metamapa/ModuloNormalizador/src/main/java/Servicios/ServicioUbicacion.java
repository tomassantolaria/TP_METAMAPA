package Servicios;

import Modelos.Calle;
import Modelos.Localidad;
import Modelos.Provincia;
import Modelos.Ubicacion;
import Repositorio.RepositorioCalle;
import Repositorio.RepositorioLocalidad;
import Repositorio.RepositorioProvincia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioUbicacion {
    @Autowired
    private RepositorioCalle repositorioCalle;
    private RepositorioLocalidad repositorioLocalidad;
    private RepositorioProvincia repositorioProvincia;

    public Ubicacion normalizarUbicacion (String nombre_provincia, String nombre_calle, String nombre_localidad) {
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setProvincia(this.normalizarProvincia(nombre_provincia));
        ubicacion.setLocalidad(this.normalizarLocalidad(nombre_localidad, ubicacion.getProvincia()));
        ubicacion.setCalle(this.normalizarCalle(nombre_calle, ubicacion.getLocalidad()));
        return ubicacion;
    }

    public Provincia normalizarProvincia (String nombre_provincia) {
        nombre_provincia = nombre_provincia.toUpperCase();
        return repositorioProvincia.crearProvincia(nombre_provincia);
    }

    public Localidad normalizarLocalidad(String nombre_localidad, Provincia provincia) {
        nombre_localidad = nombre_localidad.toUpperCase();
        return repositorioLocalidad.crearLocalidad(nombre_localidad, provincia);
    }

    public Calle normalizarCalle(String nombre_calle, Localidad localidad) {
        nombre_calle = nombre_calle.toUpperCase();
        return repositorioCalle.crearCalle(nombre_calle, localidad);
    }
}

