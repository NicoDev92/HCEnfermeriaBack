package com.nicode.gestionenfermeria.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "servicio")
@Getter
@Setter
@NoArgsConstructor
public class ServicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_servicio", length = 35, nullable = false)
    private String nombreServicio;

    @ManyToMany(mappedBy = "servicios")
    private List<EnfermeroEntity> enfermeros;

}
