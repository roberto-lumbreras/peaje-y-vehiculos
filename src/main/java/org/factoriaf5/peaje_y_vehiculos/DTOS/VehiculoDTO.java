package org.factoriaf5.peaje_y_vehiculos.DTOS;

import org.factoriaf5.peaje_y_vehiculos.models.Vehiculo.Categoria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehiculoDTO {
    private String matricula;
    private Long peajeId;
    private Categoria categoria;
    private Integer numeroDeEjes;
}
