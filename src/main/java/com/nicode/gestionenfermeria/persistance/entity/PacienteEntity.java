package com.nicode.gestionenfermeria.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties("hibernateLazyInitializer")
public class PacienteEntity extends PersonaEntity{

    @Column(nullable = true, length = 10)
    private Integer habitacion;

    @Column(nullable = true, length = 10)
    private Integer cama;

    @Column(nullable = true, length = 35)
    private String servicio;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_historia_clinica", referencedColumnName = "id", insertable = false, updatable = false)
    private HistoriaClinicaEntity historiaClinica;

}
