package org.factoriaf5.peaje_y_vehiculos;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.factoriaf5.peaje_y_vehiculos.DTOS.VehiculoDTO;
import org.factoriaf5.peaje_y_vehiculos.models.Peaje;
import org.factoriaf5.peaje_y_vehiculos.models.Vehiculo.Categoria;
import org.factoriaf5.peaje_y_vehiculos.repositories.PeajeRepository;
import org.factoriaf5.peaje_y_vehiculos.services.PeajeYVehiculosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    PeajeRepository peajeRepository;
    @Autowired
    PeajeYVehiculosService service;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //PEAJES
        peajeRepository.save(Peaje.builder().nombre("Peaje de La Roca").ciudad("Barcelona").recaudacion(new BigDecimal(0)).vehiculos(new ArrayList<>()).build());
        peajeRepository.save(Peaje.builder().nombre("Peaje de San Rafael").ciudad("Segovia").recaudacion(new BigDecimal(0)).vehiculos(new ArrayList<>()).build());
        peajeRepository.save(Peaje.builder().nombre("Peaje de Oropesa").ciudad("Castellon").recaudacion(new BigDecimal(0)).vehiculos(new ArrayList<>()).build());

        VehiculoDTO vehiculoDTO = new VehiculoDTO();

        //VEHICULOS PEAJE 1
        vehiculoDTO.setCategoria(Categoria.COCHE);
        vehiculoDTO.setMatricula("4823 FKL");
        vehiculoDTO.setPeajeId(1L);
        service.registrarUnVehiculo(vehiculoDTO);

        vehiculoDTO.setCategoria(Categoria.COCHE);
        vehiculoDTO.setMatricula("9271 GTR");
        vehiculoDTO.setPeajeId(1L);
        service.registrarUnVehiculo(vehiculoDTO);

        vehiculoDTO.setCategoria(Categoria.MOTO);
        vehiculoDTO.setMatricula("3456 JZX");
        vehiculoDTO.setPeajeId(1L);
        service.registrarUnVehiculo(vehiculoDTO);

        //VEHICULOS PEAJE 2
        vehiculoDTO.setCategoria(Categoria.COCHE);
        vehiculoDTO.setMatricula("7148 MNP");
        vehiculoDTO.setPeajeId(2L);
        service.registrarUnVehiculo(vehiculoDTO);

        vehiculoDTO.setCategoria(Categoria.CAMION);
        vehiculoDTO.setMatricula("5632 HRB");
        vehiculoDTO.setPeajeId(2L);
        vehiculoDTO.setNumeroDeEjes(2);
        service.registrarUnVehiculo(vehiculoDTO);
        
        vehiculoDTO.setCategoria(Categoria.CAMION);
        vehiculoDTO.setMatricula("8217 LTV");
        vehiculoDTO.setPeajeId(2L);
        vehiculoDTO.setNumeroDeEjes(3);
        service.registrarUnVehiculo(vehiculoDTO);

        //VEHICULOS PEAJE 3
        vehiculoDTO.setCategoria(Categoria.COCHE);
        vehiculoDTO.setMatricula("8103 TMN");
        vehiculoDTO.setPeajeId(3L);
        service.registrarUnVehiculo(vehiculoDTO);

        vehiculoDTO.setCategoria(Categoria.MOTO);
        vehiculoDTO.setMatricula("6745 DPZ");
        vehiculoDTO.setPeajeId(3L);
        service.registrarUnVehiculo(vehiculoDTO);
        
        vehiculoDTO.setCategoria(Categoria.CAMION);
        vehiculoDTO.setMatricula("2391 CKV");
        vehiculoDTO.setPeajeId(3L);
        vehiculoDTO.setNumeroDeEjes(4);
        service.registrarUnVehiculo(vehiculoDTO);
    }
    
}
