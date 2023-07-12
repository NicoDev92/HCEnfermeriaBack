package com.nicode.gestionenfermeria.service;

import com.nicode.gestionenfermeria.persistance.entity.PacienteEntity;
import com.nicode.gestionenfermeria.persistance.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<PacienteEntity> getAll(){
        return this.pacienteRepository.findAll();
    }

    public List<PacienteEntity> getBy(String keyword) {
            return this.pacienteRepository.getAllBy(keyword);
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
