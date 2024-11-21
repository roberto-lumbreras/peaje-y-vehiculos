package org.factoriaf5.peaje_y_vehiculos.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.factoriaf5.peaje_y_vehiculos.DTOS.VehiculoDTO;
import org.factoriaf5.peaje_y_vehiculos.models.Peaje;
import org.factoriaf5.peaje_y_vehiculos.models.Vehiculo;
import org.factoriaf5.peaje_y_vehiculos.models.Vehiculo.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PeajeYVehiculosControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void testObtenerDatosDePeajePorIdentificador() throws Exception {
        Peaje peaje = Peaje.builder().nombre("Peaje de Oropesa").ciudad("Castellon").recaudacion(new BigDecimal(0))
                .vehiculos(new ArrayList<>()).id(3L).build();
        peaje.getVehiculos().add(new Vehiculo(7L, "8103 TMN", new BigDecimal(100.00), Categoria.COCHE));
        peaje.getVehiculos().add(new Vehiculo(8L, "6745 DPZ", new BigDecimal(50.00), Categoria.MOTO));
        peaje.getVehiculos().add(new Vehiculo(9L, "2391 CKV", new BigDecimal(200.00), Categoria.CAMION));
        peaje.actualizarRecaudacion();

        mockMvc.perform(MockMvcRequestBuilders.get("/peaje-y-vehiculos-api/{peaje-id}", 3))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(peaje))).andExpect(status().isOk());
    }

    @Test
    void testRegistrarUnVehiculo() throws Exception {
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setMatricula("9999 ZZZ");
        vehiculoDTO.setCategoria(Categoria.COCHE);
        vehiculoDTO.setPeajeId(3L);
        String json = new ObjectMapper().writeValueAsString(vehiculoDTO);
        Vehiculo vehiculo = Vehiculo.builder().categoria(vehiculoDTO.getCategoria())
                .matricula(vehiculoDTO.getMatricula()).importe(new BigDecimal(100.00)).id(10L).build();
        String expectedJson = new ObjectMapper().writeValueAsString(vehiculo);

        mockMvc.perform(MockMvcRequestBuilders.post("/peaje-y-vehiculos-api/vehiculos")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(expectedJson));
    }
}
