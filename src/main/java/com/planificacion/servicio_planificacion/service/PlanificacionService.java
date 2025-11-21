package com.planificacion.servicio_planificacion.service;

import com.planificacion.servicio_planificacion.model.Planificacion;

import java.time.LocalDate;
import java.util.List;

public interface PlanificacionService {

    Planificacion crearPlanificacion(Planificacion planificacion);

    List<Planificacion> listarPlanificaciones();

    Planificacion obtenerPorId(Long id);

    List<Planificacion> buscarPorFecha(LocalDate fecha);

    void eliminar(Long id);
}
