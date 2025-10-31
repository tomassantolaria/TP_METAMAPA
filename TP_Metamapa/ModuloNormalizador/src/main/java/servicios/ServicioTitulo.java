package servicios;

import org.springframework.stereotype.Service;

@Service
public class ServicioTitulo {

    public String normalizarTitulo(String titulo){

        if (titulo == null || titulo.isEmpty()){
            return titulo;
        }

        String[] palabras = titulo.trim().split("\\s+");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            if (palabra.isEmpty()) continue;

            String normalizada = palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase();

            resultado.append(normalizada).append(" ");
        }

        return resultado.toString().trim();
    }
}
