package Servicios;

import Modelos.Localidad;
import Modelos.Provincia;
import Modelos.Pais;
import Modelos.Ubicacion;
import Repositorio.RepositorioLocalidad;
import Repositorio.RepositorioProvincia;
import Repositorio.RepositorioPais;
import org.springframework.stereotype.Service;



@Service
public class ServicioUbicacion {
    private final RepositorioLocalidad repositorioLocalidad;
    private final RepositorioProvincia repositorioProvincia;
    private final RepositorioPais repositorioPais;

    public ServicioUbicacion(RepositorioLocalidad repositorioLocalidad, RepositorioProvincia repositorioProvincia, RepositorioPais repositorioPais) {
        this.repositorioLocalidad = repositorioLocalidad;
        this.repositorioProvincia = repositorioProvincia;
        this.repositorioPais = repositorioPais;
    }

    public Ubicacion normalizarUbicacion (String nombre_pais, String nombre_provincia, String nombre_localidad) {
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setPais(this.normalizarPais(nombre_pais));
        ubicacion.setProvincia(this.normalizarProvincia(nombre_provincia, ubicacion.getPais()));
        ubicacion.setLocalidad(this.normalizarLocalidad(nombre_localidad, ubicacion.getProvincia()));
        return ubicacion;
    }

    public Pais normalizarPais (String nombre_pais) {
        nombre_pais = nombre_pais.toUpperCase();
        return repositorioPais.crearPais(nombre_pais);
    }

    public Provincia normalizarProvincia(String nombre_provincia, Pais pais) {
        nombre_provincia = nombre_provincia.toUpperCase();
        return repositorioProvincia.crearProvincia(nombre_provincia, pais);
    }

    public Localidad normalizarLocalidad(String nombre_Localidad, Provincia provincia) {
        nombre_Localidad = nombre_Localidad.toUpperCase();
        return repositorioLocalidad.crearLocalidad(nombre_Localidad, provincia);
    }
}

