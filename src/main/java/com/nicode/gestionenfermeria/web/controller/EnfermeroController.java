package com.nicode.gestionenfermeria.web.controller;


import com.nicode.gestionenfermeria.persistance.entity.EnfermeroEntity;
import com.nicode.gestionenfermeria.service.EnfermeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/enfermeros")
public class EnfermeroController {
    private final EnfermeroService enfermeroService;

    @Autowired
    public EnfermeroController(EnfermeroService enfermeroService) {
        this.enfermeroService = enfermeroService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<EnfermeroEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int elements){

        Page<EnfermeroEntity> enfermeros = this.enfermeroService.getAll(page, elements);

        return ResponseEntity.ok(enfermeros);
    }

    @GetMapping("/findBy/{param}")
    public ResponseEntity<Page<EnfermeroEntity>> getBy(@PathVariable String param,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int elements){

        Page<EnfermeroEntity> enfermeros = this.enfermeroService.getBy(param, page, elements);

        return ResponseEntity.ok(enfermeros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<EnfermeroEntity>> getById(@PathVariable int id){
        return ResponseEntity.ok(this.enfermeroService.getById(id));
    }

    @PostMapping
    public ResponseEntity<EnfermeroEntity> add(@RequestBody EnfermeroEntity enfermeroEntity){
        if(!this.enfermeroService.exists(enfermeroEntity)){
            return ResponseEntity.ok(this.enfermeroService.save(enfermeroEntity));
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<EnfermeroEntity> update(@RequestBody EnfermeroEntity enfermeroEntity){
        if(this.enfermeroService.exists(enfermeroEntity)){
            return ResponseEntity.ok(this.enfermeroService.save(enfermeroEntity));
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        this.enfermeroService.delete(id);
        return ResponseEntity.ok().build();
    }
}
