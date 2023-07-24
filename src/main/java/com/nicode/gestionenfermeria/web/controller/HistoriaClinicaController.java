package com.nicode.gestionenfermeria.web.controller;


import com.nicode.gestionenfermeria.persistance.entity.HistoriaClinicaEntity;
import com.nicode.gestionenfermeria.persistance.entity.PacienteEntity;
import com.nicode.gestionenfermeria.service.HistoriaClinicaService;
import com.nicode.gestionenfermeria.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/hc")
@CrossOrigin(origins = "*")
public class HistoriaClinicaController {

    private final HistoriaClinicaService hcService;
    private final PacienteService pacienteService;

    @Autowired
    public HistoriaClinicaController(HistoriaClinicaService hcService, PacienteService pacienteService) {
        this.hcService = hcService;
        this.pacienteService = pacienteService;
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

    @GetMapping("/hcBy/{param}")
    public ResponseEntity<Page<PacienteEntity>> getBy(@PathVariable String param,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int elements){
        return ResponseEntity.ok(this.hcService.getBy(param, page, elements));
    }

    @GetMapping("/hcId/{id}")
    public ResponseEntity<Optional<HistoriaClinicaEntity>> getById(@PathVariable int id){
        return ResponseEntity.ok(this.hcService.getById(id));
    }

    @PostMapping("/save/{id}")
    public ResponseEntity<HistoriaClinicaEntity> add(@RequestBody HistoriaClinicaEntity historiaClinicaEntity,@PathVariable("id") int idPaciente){
        PacienteEntity paciente = this.pacienteService.getById(idPaciente).orElse(null);

        if(paciente == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        historiaClinicaEntity.setPaciente(paciente);
        return ResponseEntity.ok(this.hcService.save(historiaClinicaEntity));
    }

    @PutMapping("/update/{idHc}")
    public ResponseEntity<HistoriaClinicaEntity> update(@RequestBody HistoriaClinicaEntity historiaClinicaEntity){
        return ResponseEntity.ok(this.hcService.save(historiaClinicaEntity));
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(int id){
        this.hcService.delete(id);
        return ResponseEntity.ok().build();
    }
}
