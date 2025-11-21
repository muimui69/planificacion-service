package com.planificacion.servicio_planificacion.service;

import com.planificacion.servicio_planificacion.model.Planificacion;
import com.planificacion.servicio_planificacion.repository.PlanificacionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlanificacionServiceImpl implements PlanificacionService {

    private final PlanificacionRepository repository;

    public PlanificacionServiceImpl(PlanificacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Planificacion crearPlanificacion(Planificacion planificacion) {
        if (planificacion.getEstado() == null || planificacion.getEstado().isBlank()) {
            planificacion.setEstado("PENDIENTE");
        }
        return repository.save(planificacion);
    }

    @Override
    public List<Planificacion> listarPlanificaciones() {
        return repository.findAll();
    }

    @Override
    public Planificacion obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Planificacion> buscarPorFecha(LocalDate fecha) {
        return repository.findByFechaObjetivo(fecha);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
