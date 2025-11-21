package com.planificacion.servicio_planificacion.repository;

import com.planificacion.servicio_planificacion.model.Planificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PlanificacionRepository extends JpaRepository<Planificacion, Long> {

    List<Planificacion> findByFechaObjetivo(LocalDate fechaObjetivo);
}
