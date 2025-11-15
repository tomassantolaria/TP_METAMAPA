package com.TP_Metamapa.Configuracion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.upload-url}")
    private String uploadUrl;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Expone la carpeta de subidas bajo la URL /images/hechos/**
        // Asegúrate de que uploadDir tenga una barra al final si es necesario
        // El 'file:' es importante para indicar que es una ruta del sistema de archivos
        registry.addResourceHandler(uploadUrl + "**")
                .addResourceLocations("file:" + uploadDir + "/");

    }
}