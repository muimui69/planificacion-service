package com.planificacion.servicio_planificacion.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.planificacion.servicio_planificacion.model.Planificacion;

@Service
public class PlanificacionServiceImpl implements PlanificacionService {

    private final List<Planificacion> planificaciones = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public PlanificacionServiceImpl() {
        planificaciones.add(Planificacion.builder()
                .id(idGenerator.getAndIncrement())
                .productoId(1L)
                .cantidad(100)
                .fechaObjetivo(LocalDate.now().plusDays(30))
                .estado("PENDIENTE")
                .build());

        planificaciones.add(Planificacion.builder()
                .id(idGenerator.getAndIncrement())
                .productoId(2L)
                .cantidad(50)
                .fechaObjetivo(LocalDate.now().plusDays(45))
                .estado("APROBADA")
                .build());

        planificaciones.add(Planificacion.builder()
                .id(idGenerator.getAndIncrement())
                .productoId(3L)
                .cantidad(200)
                .fechaObjetivo(LocalDate.now().plusDays(15))
                .estado("PENDIENTE")
                .build());
    }

    @Override
    public Planificacion crearPlanificacion(Planificacion planificacion) {
        if (planificacion.getEstado() == null || planificacion.getEstado().isBlank()) {
            planificacion.setEstado("PENDIENTE");
        }

        if (planificacion.getId() == null) {
            planificacion.setId(idGenerator.getAndIncrement());
        }

        planificaciones.add(planificacion);
        return planificacion;
    }

    @Override
    public List<Planificacion> listarPlanificaciones() {
        return new ArrayList<>(planificaciones);
    }

    @Override
    public Planificacion obtenerPorId(Long id) {
        return planificaciones.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Planificacion> buscarPorFecha(LocalDate fecha) {
        return planificaciones.stream()
                .filter(p -> p.getFechaObjetivo().equals(fecha))
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        planificaciones.removeIf(p -> p.getId().equals(id));
    }
}