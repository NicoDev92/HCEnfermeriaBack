package com.nicode.gestionenfermeria.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "paciente")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class PacienteEntity extends PersonaEntity{

    @Column(nullable = true, length = 10)
    private Integer habitacion;

    @Column(nullable = true, length = 10)
    private Integer cama;

    @Column(nullable = true, length = 35)
    private String servicio;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_historia_clinica", referencedColumnName = "id", insertable = false, updatable = false)
    private HistoriaClinicaEntity historiaClinica;

}
