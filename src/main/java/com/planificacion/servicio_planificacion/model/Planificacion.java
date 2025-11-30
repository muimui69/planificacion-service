package com.planificacion.servicio_planificacion.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Planificacion {

    private Long id;
    private Long productoId;
    private Integer cantidad;
    private LocalDate fechaObjetivo;
    private String estado;
}