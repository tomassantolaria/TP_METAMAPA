package com.TP_Metamapa.Servicio;

import com.TP_Metamapa.DTOS.HechoDTO;
import com.TP_Metamapa.DTOS.HechoDTOInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class HechoServicio {
    @Autowired
    RestTemplate restTemplate;

    @Value("${file.upload-dir}") // Directorio para guardar archivos
    private String uploadDir;

    @Value("${file.upload-url}") // URL base para acceder a los archivos
    private String uploadUrl;

    public List<HechoDTO> hechosRecientes(){
        UriComponentsBuilder urlHechos = UriComponentsBuilder.fromHttpUrl("http://localhost:8087/publico/hechos");

        ResponseEntity<List<HechoDTO>> respuesta = restTemplate.exchange(
                urlHechos.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<HechoDTO>>() {}
        );
        return obtenerCuatroMasRecientes(respuesta.getBody());

    }

    public static List<HechoDTO> obtenerCuatroMasRecientes(List<HechoDTO> hechos) {
        return hechos.stream()
                .sorted(Comparator.comparing(HechoDTO:: getFechaCarga).reversed())
                .limit(4)
                .collect(Collectors.toList());
    }

    public void eliminarHechoDeColeccion(Long idColeccion, Long idHecho){
        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/coleccion/" + idColeccion + "/hecho/" + idHecho);

        ResponseEntity<String> respuesta = restTemplate.exchange(
                url.toUriString(),
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<String>() {}
        );
    }

    public String guardarMultimediaLocalmente(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null; // No hay archivo para guardar
        }

        Path uploadPath = Paths.get(uploadDir);

        // Crear directorio si no existe
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Generar nombre único
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String uniqueFilename = UUID.randomUUID().toString() + extension;

        // Guardar el archivo
        Path filePath = uploadPath.resolve(uniqueFilename);
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        // Devolver la URL relativa para usar en <img> y guardar en BD (vía backend)
        return uploadUrl + uniqueFilename;
    }

    public void enviarHechoAlBackend(HechoDTOInput hechoParaBackend) {
        String url = "http://localhost:8082/dinamica/hechos"; // Endpoint POST del backend


        HttpEntity<HechoDTOInput> requestEntity = new HttpEntity<>(hechoParaBackend);

        try {
            ResponseEntity<String> respuesta = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity, //TODO: CAMBIAR EN DINAMICA SACAR LO DE REGISTRAR CONTRIBUYENTE Y EL DTO DE HECHO A COMO ESTA ACA
                    new ParameterizedTypeReference<String>() {}
            );


        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con el backend para crear hecho: " + e.getMessage(), e);
        }
    }


}
