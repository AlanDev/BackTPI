package com.cine.controller;

import com.cine.model.Pelicula;
import com.cine.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin(origins = "*")
public class PeliculaController {
    
    @Autowired
    private PeliculaRepository peliculaRepository;
    
    @GetMapping
    public List<Pelicula> getAllPeliculas() {
        return peliculaRepository.findAll();
    }
    
    @PostMapping
    public ResponseEntity<?> createPelicula(@RequestBody Pelicula pelicula) {
        try {
            Pelicula nuevaPelicula = peliculaRepository.save(pelicula);
            return ResponseEntity.ok(nuevaPelicula);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error al crear la película: " + e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getPeliculaById(@PathVariable Long id) {
        return peliculaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePelicula(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        return peliculaRepository.findById(id)
                .map(existingPelicula -> {
                    pelicula.setId(id);
                    Pelicula updated = peliculaRepository.save(pelicula);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePelicula(@PathVariable Long id) {
        return peliculaRepository.findById(id)
                .map(pelicula -> {
                    peliculaRepository.delete(pelicula);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
} 