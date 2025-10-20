package servicios;

import Modelos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

@Service
public class ServicioUbicacion {

    @Autowired
    RestTemplate restTemplate;

    //public ServicioUbicacion(RestTemplate restTemplate) {
        //this.restTemplate = restTemplate;
    //}

    private static final String NOMINATIM_URL = "https://nominatim.openstreetmap.org/reverse?lat={lat}&lon={lon}&format=json";

    public UbicacionDTOoutput normalizarUbicacion (Double latitud, Double longitud) {

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

