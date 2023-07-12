package com.nicode.gestionenfermeria.persistance.repository;

import com.nicode.gestionenfermeria.persistance.entity.EnfermeroEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnfermeroRepository extends ListCrudRepository<EnfermeroEntity, Integer> {

    @Query("SELECT e FROM EnfermeroEntity e WHERE e.nombre LIKE %:keyword%"
            + "OR e.apellido LIKE %:keyword%"
            + "OR e.matricula LIKE %:keyword%")
    List<EnfermeroEntity> getEnfermerosBy(@Param("keyword") String keyword);
}
