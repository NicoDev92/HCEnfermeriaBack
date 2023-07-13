package com.nicode.gestionenfermeria.service;

import com.nicode.gestionenfermeria.persistance.entity.PacienteEntity;
import com.nicode.gestionenfermeria.persistance.repository.PacientePageSortRepository;
import com.nicode.gestionenfermeria.persistance.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacientePageSortRepository pacientePageSortRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, PacientePageSortRepository pacientePageSortRepository) {
        this.pacienteRepository = pacienteRepository;
        this.pacientePageSortRepository = pacientePageSortRepository;
    }

    public Page<PacienteEntity> getAll(int page, int elements) {
        Pageable pageRequest  = PageRequest.of(page, elements);
        return this.pacientePageSortRepository.findAll(pageRequest);
    }

    public Page<PacienteEntity> getBy(String param, int page, int elements){
        Pageable pageRequest  = PageRequest.of(page, elements);
        return this.pacientePageSortRepository
                .findAllByNombreContainingOrApellidoContainingOrDniContainingOrServicioContaining(param, param, param, param, pageRequest);
    }

    public Optional<PacienteEntity> getById(int id) {
        return Optional.of(this.pacienteRepository.findById(id)).orElse(null);
    }

    public boolean exists(PacienteEntity paciente){
        boolean isRegistered = false;
        List<PacienteEntity> pacientes = pacienteRepository.findAll();

        for(PacienteEntity p : pacientes){
            if(p.getDni().equalsIgnoreCase(paciente.getDni())){
                isRegistered = true;
                return isRegistered;

            }
        }
        return isRegistered;
    }

    public PacienteEntity save(PacienteEntity pacienteEntity){
        return this.pacienteRepository.save(pacienteEntity);
    }

    public void delete(int id) {
        this.pacienteRepository.deleteById(id);
    }
}
