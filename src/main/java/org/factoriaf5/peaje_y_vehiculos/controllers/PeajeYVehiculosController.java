package org.factoriaf5.peaje_y_vehiculos.controllers;

import org.factoriaf5.peaje_y_vehiculos.DTOS.VehiculoDTO;
import org.factoriaf5.peaje_y_vehiculos.models.Peaje;
import org.factoriaf5.peaje_y_vehiculos.models.Vehiculo;
import org.factoriaf5.peaje_y_vehiculos.services.PeajeYVehiculosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/peaje-y-vehiculos-api")
public class PeajeYVehiculosController {
    @Autowired
    PeajeYVehiculosService service;

    @GetMapping("/{peaje-id}")
    public ResponseEntity<Peaje> obtenerDatosDePeajePorIdentificador(@PathVariable("peaje-id")Long peajeId){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(service.obtenerDatosDePeajePorIdentificador(peajeId));
    }

    @PostMapping("/vehiculos")
    public ResponseEntity<Vehiculo> registrarUnVehiculo(@RequestBody VehiculoDTO vehiculoDTO){
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.registrarUnVehiculo(vehiculoDTO));
    }
}
