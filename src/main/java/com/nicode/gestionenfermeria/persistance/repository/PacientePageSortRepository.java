package com.nicode.gestionenfermeria.persistance.repository;

import com.nicode.gestionenfermeria.persistance.entity.PacienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PacientePageSortRepository extends PagingAndSortingRepository<PacienteEntity, Integer> {

    Page<PacienteEntity> findAllByNombreContainingOrApellidoContainingOrDniContainingOrServicioContaining(String keyword,
                                                                                                          String keyword2,
                                                                                                          String keyword3,
                                                                                                          String keyword4,
                                                                                                          Pageable page);

}
