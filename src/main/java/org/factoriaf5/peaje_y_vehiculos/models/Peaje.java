package org.factoriaf5.peaje_y_vehiculos.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "peajes")
@AllArgsConstructor
@Builder
public class Peaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)    
    private String ciudad;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal recaudacion;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="peaje_id")
    private List<Vehiculo> vehiculos;

    public void actualizarRecaudacion(){
        if(this.vehiculos != null){
            this.recaudacion = new BigDecimal(vehiculos.stream()
                                            .mapToDouble(x -> x.getImporte().doubleValue())
                                            .sum())
                                            .setScale(2,RoundingMode.FLOOR);
        }
    }
}
