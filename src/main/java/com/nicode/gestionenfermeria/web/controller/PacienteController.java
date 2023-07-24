package com.nicode.gestionenfermeria.web.controller;

import com.nicode.gestionenfermeria.persistance.entity.HistoriaClinicaEntity;
import com.nicode.gestionenfermeria.persistance.entity.PacienteEntity;
import com.nicode.gestionenfermeria.persistance.projection.PacienteReducedSumary;
import com.nicode.gestionenfermeria.service.PacienteService;
import com.nicode.gestionenfermeria.service.dto.UpdatePacienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {

        this.pacienteService = pacienteService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<PacienteReducedSumary>> getAll(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int elements) {

        return ResponseEntity.ok(this.pacienteService.getAll(page, elements));
    }

    @GetMapping("/findBy/{param}")
    public ResponseEntity<Page<PacienteEntity>> getBy(@PathVariable String param,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int elements) {
        Page<PacienteEntity> pacientes = pacienteService.getBy(param, page, elements);
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PacienteEntity>> getById(@PathVariable int id) {
        return ResponseEntity.ok(pacienteService.getById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<PacienteEntity> add(@RequestBody PacienteEntity pacienteEntity) {

        if(!this.pacienteService.exists(pacienteEntity)) {
            return ResponseEntity.ok(this.pacienteService.save(pacienteEntity));
        }

        return ResponseEntity.badRequest().build();

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@RequestBody UpdatePacienteDto updatePacienteDto) {
        PacienteEntity pacienteEntity = updatePacienteDto.getPaciente();
        HistoriaClinicaEntity hc = updatePacienteDto.getHc();

        if (this.pacienteService.exists(pacienteEntity)) {
            this.pacienteService.update(pacienteEntity, hc);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.pacienteService.delete(id);
        return ResponseEntity.ok().build();
    }
}
