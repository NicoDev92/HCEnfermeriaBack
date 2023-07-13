package com.nicode.gestionenfermeria.service;


import com.nicode.gestionenfermeria.persistance.entity.HistoriaClinicaEntity;
import com.nicode.gestionenfermeria.persistance.entity.PacienteEntity;
import com.nicode.gestionenfermeria.persistance.repository.HistoriaClinicaPageSortRepository;
import com.nicode.gestionenfermeria.persistance.repository.HistoriaClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriaClinicaService {

    private final HistoriaClinicaRepository historiaClinicaRepository;
    private final HistoriaClinicaPageSortRepository hcPageSortRepository;

    @Autowired
    public HistoriaClinicaService(HistoriaClinicaRepository historiaClinicaRepository, HistoriaClinicaPageSortRepository hcPageSortRepository) {
        this.historiaClinicaRepository = historiaClinicaRepository;
        this.hcPageSortRepository = hcPageSortRepository;
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

    public Page<PacienteEntity> getBy(String param, int page, int elements){

        Pageable pageRequest = PageRequest.of(page, elements);

        return this.hcPageSortRepository.getHistoriaClinicaBy(param, pageRequest);
    }

    public HistoriaClinicaEntity save(HistoriaClinicaEntity historiaClinicaEntity){
        return this.historiaClinicaRepository.save(historiaClinicaEntity);
    }

    public void delete(int id) {
        this.historiaClinicaRepository.deleteById(id);
    }
}

