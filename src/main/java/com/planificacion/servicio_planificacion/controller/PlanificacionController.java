package com.planificacion.servicio_planificacion.controller;

import com.planificacion.servicio_planificacion.model.Planificacion;
import com.planificacion.servicio_planificacion.service.PlanificacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/planificacion")
public class PlanificacionController {

    private final PlanificacionService service;

    public PlanificacionController(PlanificacionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Planificacion> crear(@RequestBody Planificacion planificacion) {
        return ResponseEntity.ok(service.crearPlanificacion(planificacion));
    }

    @GetMapping
    public ResponseEntity<List<Planificacion>> listar() {
        return ResponseEntity.ok(service.listarPlanificaciones());
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
}
