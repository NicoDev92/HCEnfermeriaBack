package com.nicode.gestionenfermeria.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "enfermero")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class EnfermeroEntity extends PersonaEntity{

    @Column(length = 10, nullable = false, unique = true)
    private String matricula;

    @Column(name = "fecha_de_ingreso")
    private LocalDate fechaDeIngreso;

    @Column(name = "fecha_de_egreso")
    private LocalDate fechaDeEngreso;

    @Column(length = 15)
    private String estado;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "enfermero_servicio",
            joinColumns = @JoinColumn(name = "enfermero_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "servicio_id", referencedColumnName = "id"))
    private List<ServicioEntity> servicios;

}
