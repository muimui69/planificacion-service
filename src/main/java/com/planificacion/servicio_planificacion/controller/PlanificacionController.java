package com.planificacion.servicio_planificacion.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planificacion.servicio_planificacion.config.SecurityConfig;
import com.planificacion.servicio_planificacion.model.Planificacion;
import com.planificacion.servicio_planificacion.service.PlanificacionService;

@RestController
@RequestMapping("/api/planificacion")
@CrossOrigin(origins = "*")
public class PlanificacionController {

    private final PlanificacionService service;

    @Autowired
    private SecurityConfig securityConfig;

    public PlanificacionController(PlanificacionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Planificacion>> listar() {
        return ResponseEntity.ok(service.listarPlanificaciones());
    }

    @PostMapping
    public ResponseEntity<Planificacion> crear(@RequestBody Planificacion planificacion) {
        return ResponseEntity.ok(service.crearPlanificacion(planificacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planificacion> obtener(@PathVariable Long id) {
        Planificacion p = service.obtenerPorId(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Planificacion>> buscarPorFecha(@PathVariable String fecha) {
        LocalDate f = LocalDate.parse(fecha);
        return ResponseEntity.ok(service.buscarPorFecha(f));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/env-vars")
    public ResponseEntity<Map<String, String>> getEnvVars() {
        Map<String, String> envVars = new HashMap<>();
        envVars.put("api_url_base", securityConfig.urlBase);
        envVars.put("db_name", securityConfig.nameBd);
        envVars.put("db_password", securityConfig.password);
        envVars.put("jwt_secret", securityConfig.jwtSecret);

        return ResponseEntity.ok(envVars);
    }
}