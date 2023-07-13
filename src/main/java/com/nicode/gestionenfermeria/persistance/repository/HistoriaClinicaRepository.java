package com.nicode.gestionenfermeria.persistance.repository;

import com.nicode.gestionenfermeria.persistance.entity.HistoriaClinicaEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriaClinicaRepository extends ListCrudRepository<HistoriaClinicaEntity, Integer> {



    HistoriaClinicaEntity getHistoriaClinicaByPacienteId(int pacienteId);
}
