package com.nicode.gestionenfermeria.service;

import com.nicode.gestionenfermeria.persistance.entity.PacienteEntity;
import com.nicode.gestionenfermeria.persistance.projection.PacienteReducedSumary;
import com.nicode.gestionenfermeria.persistance.projection.PacienteSummary;
import com.nicode.gestionenfermeria.persistance.repository.PacientePageSortRepository;
import com.nicode.gestionenfermeria.persistance.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
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

    public Page<PacienteReducedSumary> getAll(int page, int elements) {
        Pageable pageRequest  = PageRequest.of(page, elements);
        return this.pacientePageSortRepository.getReducedInfo(pageRequest);
    }

    public Page<PacienteEntity> getBy(String param, int page, int elements){
        Pageable pageRequest  = PageRequest.of(page, elements);
        return this.pacientePageSortRepository
                .findAllByNombreContainingOrApellidoContainingOrDniContainingOrServicioContaining(param, param, param, param, pageRequest);
    }

    public Optional<PacienteSummary> getById(int id) {
        return Optional.of(this.pacienteRepository.getSumaryById(id)).orElse(null);
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

    @Secured({"ROLE_ADMIN", "ROLE_CUSTOM"})
    public PacienteEntity save(PacienteEntity pacienteEntity){
        return this.pacienteRepository.save(pacienteEntity);
    }



    @Secured({"ROLE_ADMIN", "ROLE_CUSTOM"})
    public void delete(int id) {
        this.pacienteRepository.deleteById(id);
    }
}
