package com.nicode.gestionenfermeria.service;


import com.nicode.gestionenfermeria.persistance.entity.EnfermeroEntity;
import com.nicode.gestionenfermeria.persistance.entity.HistoriaClinicaEntity;
import com.nicode.gestionenfermeria.persistance.repository.HistoriaClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriaClinicaService {

    private final HistoriaClinicaRepository historiaClinicaRepository;

    @Autowired
    public HistoriaClinicaService(HistoriaClinicaRepository historiaClinicaRepository) {
        this.historiaClinicaRepository = historiaClinicaRepository;
    }

    public List<HistoriaClinicaEntity> getAll(){
        return this.historiaClinicaRepository.findAll();
    }
    public HistoriaClinicaEntity getByIdPaciente(int id) {
        return this.historiaClinicaRepository.getHistoriaClinicaByPacienteId(id);
    }

    public Optional<HistoriaClinicaEntity> getById(int id){
        return Optional.ofNullable(this.historiaClinicaRepository.findById(id).orElse(null));
    }

    public HistoriaClinicaEntity save(HistoriaClinicaEntity historiaClinicaEntity){
        return this.historiaClinicaRepository.save(historiaClinicaEntity);
    }

    public void delete(int id) {
        this.historiaClinicaRepository.deleteById(id);
    }
}

