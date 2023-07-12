package com.nicode.gestionenfermeria.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "enfermero")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties("hibernateLazyInitializer")
public class EnfermeroEntity extends PersonaEntity{

    @Column(length = 10, nullable = false, unique = true)
    private String matricula;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "enfermero_servicio",
            joinColumns = @JoinColumn(name = "enfermero_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "servicio_id", referencedColumnName = "id"))
    private List<ServicioEntity> servicios;

}
