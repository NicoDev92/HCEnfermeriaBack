package com.nicode.gestionenfermeria.persistance.repository;

import com.nicode.gestionenfermeria.persistance.entity.HistoriaClinicaEntity;
import com.nicode.gestionenfermeria.persistance.entity.PacienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoriaClinicaPageSortRepository extends PagingAndSortingRepository<HistoriaClinicaEntity, Integer> {

    @Query("SELECT h.paciente FROM HistoriaClinicaEntity h WHERE h.grupoSanguineo LIKE %:keyword%")
    Page<PacienteEntity> getHistoriaClinicaBy(@Param("keyword") String keyword, Pageable pageable);

}
