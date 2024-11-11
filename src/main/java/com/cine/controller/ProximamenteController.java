package com.cine.controller;

import com.cine.model.Proximamente;
import com.cine.repository.ProximamenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proximamente")
@CrossOrigin(origins = "*")
public class ProximamenteController {

    @Autowired
    private ProximamenteRepository proximamenteRepository;

    @GetMapping
    public List<Proximamente> getAllProximamente() {
        return proximamenteRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createProximamente(@RequestBody Proximamente proximamente) {
        try {
            Proximamente nuevaPelicula = proximamenteRepository.save(proximamente);
            return ResponseEntity.ok(nuevaPelicula);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error al crear la película: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProximamenteById(@PathVariable Long id) {
        return proximamenteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProximamente(@PathVariable Long id, 
                                              @RequestBody Proximamente proximamente) {
        return proximamenteRepository.findById(id)
                .map(existingPelicula -> {
                    proximamente.setId(id);
                    Proximamente updated = proximamenteRepository.save(proximamente);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProximamente(@PathVariable Long id) {
        return proximamenteRepository.findById(id)
                .map(pelicula -> {
                    proximamenteRepository.delete(pelicula);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
