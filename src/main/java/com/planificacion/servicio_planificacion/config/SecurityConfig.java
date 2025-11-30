package com.planificacion.servicio_planificacion.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Value("${jwt_secret:none}")
    public String jwtSecret;

    @Value("${api_url_base:http://localhost:8081}")
    public String urlBase;

    @Value("${db_name:none}")
    public String nameBd;

    @Value("${db_password:}")
    public String password;
}