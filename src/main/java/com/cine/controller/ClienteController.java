package com.cine.controller;

import com.cine.model.Cliente;
import com.cine.repository.ClienteRepository;
import com.cine.repository.PeliculaRepository;
import com.cine.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
        try {
            
            if (!peliculaRepository.existsById(cliente.getPelicula().getId())) {
                return ResponseEntity.badRequest()
                        .body("La película seleccionada no existe");
            }

            if (!horarioRepository.existsById(cliente.getHorario().getId())) {
                return ResponseEntity.badRequest()
                        .body("El horario seleccionado no existe");
            }

            Cliente nuevoCliente = clienteRepository.save(cliente);
            return ResponseEntity.ok(nuevoCliente);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error al crear el cliente: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteRepository.findById(id)
                .map(existingCliente -> {
                    if (!peliculaRepository.existsById(cliente.getPelicula().getId())) {
                        return ResponseEntity.badRequest()
                                .body("La película seleccionada no existe");
                    }

                    if (!horarioRepository.existsById(cliente.getHorario().getId())) {
                        return ResponseEntity.badRequest()
                                .body("El horario seleccionado no existe");
                    }

                    cliente.setId(id);
                    Cliente updated = clienteRepository.save(cliente);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    clienteRepository.delete(cliente);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}