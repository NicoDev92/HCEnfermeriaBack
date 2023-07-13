package com.nicode.gestionenfermeria.persistance.repository;

import com.nicode.gestionenfermeria.persistance.entity.EnfermeroEntity;
import com.nicode.gestionenfermeria.persistance.projection.EnfermeroReducedSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EnfermeroPageSortRepository extends PagingAndSortingRepository<EnfermeroEntity, Integer> {
    Page<EnfermeroEntity> findAllByNombreContainingOrApellidoContainingOrMatriculaContaining(String keyword,
                                                                                             String keyword2,
                                                                                             String keyword3,
                                                                                             Pageable page);
    @Query(value = "SELECT id, nombre, apellido, dni, matricula FROM enfermero", nativeQuery = true)
    Page<EnfermeroReducedSummary> getReducedInfo(Pageable page);
}
