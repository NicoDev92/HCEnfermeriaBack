package com.nicode.gestionenfermeria.persistance.repository;

import com.nicode.gestionenfermeria.persistance.entity.EnfermeroEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EnfermeroPageSortRepository extends PagingAndSortingRepository<EnfermeroEntity, Integer> {
    Page<EnfermeroEntity> findAllByNombreContainingOrApellidoContainingOrMatriculaContaining(String keyword,
                                                                                             String keyword2,
                                                                                             String keyword3,
                                                                                             Pageable page);
}
