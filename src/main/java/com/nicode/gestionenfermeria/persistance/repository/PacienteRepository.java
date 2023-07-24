package com.nicode.gestionenfermeria.persistance.repository;

import com.nicode.gestionenfermeria.persistance.entity.PacienteEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends ListCrudRepository<PacienteEntity, Integer> {

}
