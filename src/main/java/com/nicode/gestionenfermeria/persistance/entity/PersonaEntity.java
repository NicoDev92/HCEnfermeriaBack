package com.nicode.gestionenfermeria.persistance.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS )
@Getter
@Setter
@NoArgsConstructor
public abstract class PersonaEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(length = 55, nullable = false)
    private String nombre;

    @Column(length = 55, nullable = false)
    private String apellido;

    @Column(length = 15, nullable = false, unique = true)
    private String dni;

    @Column(name = "fecha_de_nacimiento",length = 12)
    private String fechaDeNacimiento;

    @Column(length = 150)
    private String direccion;

}
