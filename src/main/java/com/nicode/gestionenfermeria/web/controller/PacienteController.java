package com.nicode.gestionenfermeria.web.controller;

import com.nicode.gestionenfermeria.persistance.entity.PacienteEntity;
import com.nicode.gestionenfermeria.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {

        this.pacienteService = pacienteService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<PacienteEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int elements) {

        return ResponseEntity.ok(this.pacienteService.getAll(page, elements));
    }

    @GetMapping("/findBy/{param}")
    public ResponseEntity<Page<PacienteEntity>> getByQuery(@PathVariable String param,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int elements) {
        Page<PacienteEntity> pacientes = pacienteService.getBy(param, page, elements);
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PacienteEntity>> getById(@PathVariable int id) {
        return ResponseEntity.ok(pacienteService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PacienteEntity> add(@RequestBody PacienteEntity pacienteEntity) {

        if(!this.pacienteService.exists(pacienteEntity)) {
            return ResponseEntity.ok(this.pacienteService.save(pacienteEntity));
        }

        return ResponseEntity.badRequest().build();

    }

    @PutMapping
    public ResponseEntity<PacienteEntity> update(@RequestBody PacienteEntity pacienteEntity) {

        if(this.pacienteService.exists(pacienteEntity)){
            return ResponseEntity.ok(this.pacienteService.save(pacienteEntity));
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.pacienteService.delete(id);
        return ResponseEntity.ok().build();
    }
}
