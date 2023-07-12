package com.nicode.gestionenfermeria.web.controller;


import com.nicode.gestionenfermeria.persistance.entity.HistoriaClinicaEntity;
import com.nicode.gestionenfermeria.service.HistoriaClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/hc")
public class HistoriaClinicaController {

    private final HistoriaClinicaService hcService;

    @Autowired
    public HistoriaClinicaController(HistoriaClinicaService hcService) {
        this.hcService = hcService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<HistoriaClinicaEntity>> getAll(){
        List<HistoriaClinicaEntity> historiasClinicas = this.hcService.getAll();
        return  ResponseEntity.ok(historiasClinicas);
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<HistoriaClinicaEntity> getByIdPaciente(@PathVariable int id){
        return ResponseEntity.ok(this.hcService.getByIdPaciente(id));
    }

    @GetMapping("/hcid/{id}")
    public ResponseEntity<Optional<HistoriaClinicaEntity>> getById(@PathVariable int id){
        return ResponseEntity.ok(this.hcService.getById(id));
    }

    @PostMapping
    public ResponseEntity<HistoriaClinicaEntity> add(@RequestBody HistoriaClinicaEntity historiaClinicaEntity){
        return ResponseEntity.ok(this.hcService.save(historiaClinicaEntity));
    }
}
