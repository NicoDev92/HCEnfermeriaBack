package com.nicode.gestionenfermeria.persistance.repository;

import com.nicode.gestionenfermeria.persistance.entity.PacienteEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends ListCrudRepository<PacienteEntity, Integer> {

    @Query("SELECT p FROM PacienteEntity p WHERE p.nombre LIKE %:keyword%"
            + " OR p.apellido LIKE %:keyword%"
            + " OR p.dni LIKE %:keyword%"
            + " OR p.servicio LIKE %:keyword%")
    List<PacienteEntity> getAllBy(@Param("keyword") String keyword);
}
