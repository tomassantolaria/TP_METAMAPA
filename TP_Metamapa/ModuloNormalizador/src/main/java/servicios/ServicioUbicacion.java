package servicios;

import Modelos.*;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

@Service
public class ServicioUbicacion {

    private static final String NOMINATIM_URL = "https://nominatim.openstreetmap.org/reverse?lat={lat}&lon={lon}&format=json";

    public UbicacionDTOoutput normalizarUbicacion (Double latitud, Double longitud) {

        RestTemplate restTemplate = new RestTemplate();
        Ubicacion ubicacion = new Ubicacion();

        try{
            String response = restTemplate.getForObject(NOMINATIM_URL, String.class, latitud, longitud);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode address = root.path("address");

            Pais pais = new Pais(capitalizarCadaPalabra(address.path("country").asText()));
            Provincia provincia = new Provincia(capitalizarCadaPalabra(address.path("state").asText()), pais );

            String ciudad = address.path("city").asText();
            if (ciudad == null){
                ciudad = address.path("town").asText();
            }
            Localidad localidad = new Localidad(capitalizarCadaPalabra(ciudad), provincia);

            ubicacion.setPais(pais);
            ubicacion.setProvincia(provincia);
            ubicacion.setLocalidad(localidad);
            ubicacion.setLatitud(latitud);
            ubicacion.setLongitud(longitud);

        }catch (Exception e){
            e.printStackTrace();
        }
        UbicacionDTOoutput ubicacionDTOoutput = new UbicacionDTOoutput(ubicacion.getPais().getNombre_pais(), ubicacion.getProvincia().getNombre_provincia(), ubicacion.getLocalidad().getNombre_localidad(), ubicacion.getLatitud(), ubicacion.getLongitud());
        return ubicacionDTOoutput;
    }

    private String capitalizarCadaPalabra(String texto) {
        if (texto == null || texto.isBlank()) return texto;

        String[] palabras = texto.trim().toLowerCase().split("\\s+");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            resultado.append(Character.toUpperCase(palabra.charAt(0)))
                    .append(palabra.substring(1))
                    .append(" ");
        }

        return resultado.toString().trim();
    }
}


//@Service
//public class ServicioUbicacion {
//    @Autowired
//    RepositorioLocalidad repositorioLocalidad;
//    @Autowired
//    RepositorioProvincia repositorioProvincia;
//    @Autowired
//    RepositorioPais repositorioPais;
//
//    public Ubicacion normalizarUbicacion (String nombre_pais, String nombre_provincia, String nombre_localidad) {
//        Ubicacion ubicacion = new Ubicacion();
//        ubicacion.setPais(this.normalizarPais(nombre_pais));
//        ubicacion.setProvincia(this.normalizarProvincia(nombre_provincia, ubicacion.getPais()));
//        ubicacion.setLocalidad(this.normalizarLocalidad(nombre_localidad, ubicacion.getProvincia()));
//        return ubicacion;
//    }
//
//    public Pais normalizarPais (String nombre_pais) {
//        nombre_pais = nombre_pais.toUpperCase();
//        return repositorioPais.crearPais(nombre_pais);
//    }
//
//    public Provincia normalizarProvincia(String nombre_provincia, Pais pais) {
//        nombre_provincia = nombre_provincia.toUpperCase();
//        return repositorioProvincia.crearProvincia(nombre_provincia, pais);
//    }
//
//    public Localidad normalizarLocalidad(String nombre_Localidad, Provincia provincia) {
//        nombre_Localidad = nombre_Localidad.toUpperCase();
//        return repositorioLocalidad.crearLocalidad(nombre_Localidad, provincia);
//    }
//}

