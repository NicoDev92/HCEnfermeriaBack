package com.nicode.gestionenfermeria.persistance.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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

    @Column(columnDefinition = "Decimal(5,2)")
    private Float peso;

    @Column(name = "grupo_sanguineo", length = 10)
    private String grupoSanguineo;

    @Column(length = 250)
    private String historialFamiliar;

    @Column(length = 250)
    private String observaciones;

    @Column(name = "creado_el")
    @CreatedDate
    private LocalDateTime creadoEl;

    @Column(name = "ultima_modificacion")
    @LastModifiedDate
    private LocalDateTime ultimaModificacion;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_paciente")
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PacienteEntity paciente;
}
