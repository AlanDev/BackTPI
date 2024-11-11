package com.cine.controller;

import com.cine.model.Promocion;
import com.cine.repository.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promociones")
@CrossOrigin(origins = "*")
public class PromocionController {

    @Autowired
    private PromocionRepository promocionRepository;

    @GetMapping
    public List<Promocion> getAllPromociones() {
        return promocionRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createPromocion(@RequestBody Promocion promocion) {
        try {
            Promocion nuevaPromocion = promocionRepository.save(promocion);
            return ResponseEntity.ok(nuevaPromocion);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error al crear la promoción: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPromocionById(@PathVariable Long id) {
        return promocionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePromocion(@PathVariable Long id, 
                                           @RequestBody Promocion promocion) {
        return promocionRepository.findById(id)
                .map(existingPromocion -> {
                    promocion.setId(id);
                    Promocion updated = promocionRepository.save(promocion);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePromocion(@PathVariable Long id) {
        return promocionRepository.findById(id)
                .map(promocion -> {
                    promocionRepository.delete(promocion);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}