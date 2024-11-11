package com.cine.controller;

import com.cine.model.Sala;
import com.cine.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
@CrossOrigin(origins = "*")
public class SalaController {

    @Autowired
    private SalaRepository salaRepository;

    @GetMapping
    public List<Sala> getAllSalas() {
        return salaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createSala(@RequestBody Sala sala) {
        try {
            Sala nuevaSala = salaRepository.save(sala);
            return ResponseEntity.ok(nuevaSala);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error al crear la sala: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSalaById(@PathVariable Long id) {
        return salaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSala(@PathVariable Long id, @RequestBody Sala sala) {
        return salaRepository.findById(id)
                .map(existingSala -> {
                    sala.setId(id);
                    Sala updated = salaRepository.save(sala);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSala(@PathVariable Long id) {
        return salaRepository.findById(id)
                .map(sala -> {
                    salaRepository.delete(sala);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}