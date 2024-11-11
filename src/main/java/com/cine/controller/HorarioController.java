package com.cine.controller;

import com.cine.model.Horario;
import com.cine.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
@CrossOrigin(origins = "*")
public class HorarioController {

    @Autowired
    private HorarioRepository horarioRepository;

    @GetMapping
    public List<Horario> getAllHorarios() {
        return horarioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createHorario(@RequestBody Horario horario) {
        try {
            Horario nuevoHorario = horarioRepository.save(horario);
            return ResponseEntity.ok(nuevoHorario);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error al crear el horario: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHorarioById(@PathVariable Long id) {
        return horarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHorario(@PathVariable Long id, @RequestBody Horario horario) {
        return horarioRepository.findById(id)
                .map(existingHorario -> {
                    horario.setId(id);
                    Horario updated = horarioRepository.save(horario);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHorario(@PathVariable Long id) {
        return horarioRepository.findById(id)
                .map(horario -> {
                    horarioRepository.delete(horario);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}