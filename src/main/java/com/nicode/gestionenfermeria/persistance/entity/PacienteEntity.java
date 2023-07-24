package com.nicode.gestionenfermeria.persistance.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToOne(mappedBy = "paciente",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private HistoriaClinicaEntity historiaClinica;

}
