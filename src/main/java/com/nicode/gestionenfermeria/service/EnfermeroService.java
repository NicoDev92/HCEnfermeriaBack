package com.nicode.gestionenfermeria.service;


import com.nicode.gestionenfermeria.persistance.entity.EnfermeroEntity;
import com.nicode.gestionenfermeria.persistance.repository.EnfermeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnfermeroService {

    private final EnfermeroRepository enfermeroRepository;

    @Autowired
    public EnfermeroService(EnfermeroRepository enfermeroRepository) {
        this.enfermeroRepository = enfermeroRepository;
    }

    public List<EnfermeroEntity> getAll(){
        return this.enfermeroRepository.findAll();
    }

    public List<EnfermeroEntity> getBy(String keyword){
        return this.enfermeroRepository.getEnfermerosBy(keyword);
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
