package com.cine.controller;

import com.cine.service.EmailService;
import com.cine.dto.EmailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "*")
@Slf4j
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-confirmation")
    public ResponseEntity<?> sendConfirmationEmail(@RequestBody EmailRequest request) {
        try {
            log.info("Recibiendo solicitud de email: {}", request);
            
            emailService.sendConfirmacionReserva(
                request.getEmail(),
                request.getNombreCliente(),
                request.getPelicula(),
                request.getSala(),
                request.getFecha(),
                request.getHora(),
                request.getCantidadEntradas(),
                request.getTotal()
            );
            return ResponseEntity.ok("Email enviado correctamente");
        } catch (Exception e) {
            log.error("Error al enviar email: ", e);
            return ResponseEntity.badRequest()
                    .body("Error al enviar el email: " + e.getMessage());
        }
    }
}