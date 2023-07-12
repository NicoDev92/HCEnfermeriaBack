package com.nicode.gestionenfermeria.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "historia_clinica")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties("hibernateLazyInitializer")
public class HistoriaClinicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    private String sexo;

    @Column(columnDefinition = "Decimal(3,2)")
    private Float altura;

    @Column(columnDefinition = "Decimal(4,2)")
    private Float peso;

    @Column(name = "grupo_sanguineo", length = 10)
    private String grupoSanguineo;

    @Column(length = 250)
    private String historialFamiliar;

    @Column(name = "ultima_modificacion", columnDefinition = "DATETIME")
    private LocalDateTime ultimaModificacion;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_paciente", referencedColumnName = "id", insertable = false, updatable = false)
    private PacienteEntity paciente;
}
