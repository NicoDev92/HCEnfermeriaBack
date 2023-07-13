package com.nicode.gestionenfermeria.service;


import com.nicode.gestionenfermeria.persistance.entity.EnfermeroEntity;
import com.nicode.gestionenfermeria.persistance.repository.EnfermeroPageSortRepository;
import com.nicode.gestionenfermeria.persistance.repository.EnfermeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnfermeroService {

    private final EnfermeroRepository enfermeroRepository;
    private final EnfermeroPageSortRepository enfermeroPageSortRepository;

    @Autowired
    public EnfermeroService(EnfermeroRepository enfermeroRepository, EnfermeroPageSortRepository enfermeroPageSortRepository) {
        this.enfermeroRepository = enfermeroRepository;
        this.enfermeroPageSortRepository = enfermeroPageSortRepository;
    }

    public Page<EnfermeroEntity> getAll(int page, int elements) {

        Pageable pageRequest = PageRequest.of(page, elements);

        return this.enfermeroPageSortRepository.findAll(pageRequest);
    }

    public Page<EnfermeroEntity> getBy(String keyword, int page, int elements) {

        Pageable pageRequest = PageRequest.of(page, elements);

        return this.enfermeroPageSortRepository.findAllByNombreContainingOrApellidoContainingOrMatriculaContaining(keyword, keyword, keyword, pageRequest);
    }

    public Optional<EnfermeroEntity> getById(int id){
        return Optional.ofNullable(this.enfermeroRepository.findById(id).orElse(null));
    }

    public EnfermeroEntity save(EnfermeroEntity enfermeroEntity){
        return this.enfermeroRepository.save(enfermeroEntity);
    }

    public boolean exists(EnfermeroEntity enfermeroEntity){
        boolean isRegistered = false;
        List<EnfermeroEntity> enfermeros = this.enfermeroRepository.findAll();

        for(EnfermeroEntity e : enfermeros){
            if(e.getMatricula().equals(enfermeroEntity.getMatricula())){
                isRegistered = true;
                return isRegistered;
            }
        }
        return isRegistered;

    }

    public void delete(int id){
        this.enfermeroRepository.deleteById(id);
    }
}
