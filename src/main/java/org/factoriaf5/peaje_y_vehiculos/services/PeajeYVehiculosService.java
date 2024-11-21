package org.factoriaf5.peaje_y_vehiculos.services;


import java.math.BigDecimal;

import org.factoriaf5.peaje_y_vehiculos.DTOS.VehiculoDTO;
import org.factoriaf5.peaje_y_vehiculos.models.Peaje;
import org.factoriaf5.peaje_y_vehiculos.models.Vehiculo;
import org.factoriaf5.peaje_y_vehiculos.repositories.PeajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PeajeYVehiculosService {
    @Autowired
    PeajeRepository peajeRepository;

    public Peaje obtenerDatosDePeajePorIdentificador(Long id) {
        try {
            return peajeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    public Vehiculo registrarUnVehiculo(VehiculoDTO vehiculoDTO){
        try {
            Peaje peaje = peajeRepository.findById(vehiculoDTO.getPeajeId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            BigDecimal importe = switch (vehiculoDTO.getCategoria()){
                case Vehiculo.Categoria.COCHE -> new BigDecimal("100.00");
                case Vehiculo.Categoria.MOTO -> new BigDecimal("50.00");
                case Vehiculo.Categoria.CAMION -> new BigDecimal(50*vehiculoDTO.getNumeroDeEjes()).setScale(2);
                default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Categoria no valida");
            };
            Vehiculo vehiculo = Vehiculo.builder()
                                    .matricula(vehiculoDTO.getMatricula())
                                    .categoria(vehiculoDTO.getCategoria())
                                    .importe(importe)
                                    .build();
            peaje.getVehiculos().add(vehiculo);
            peaje.actualizarRecaudacion();
            return peajeRepository.save(peaje).getVehiculos().get(peaje.getVehiculos().size()-1);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}