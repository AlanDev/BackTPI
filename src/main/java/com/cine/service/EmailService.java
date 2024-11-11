package com.cine.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class EmailService {

    private final JavaMailSender emailSender;
    
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${app.frontend.url}")
    private String frontendUrl;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendConfirmacionReserva(
        String email,
        String nombreCliente,
        String pelicula,
        String sala,
        String fecha,
        String hora,
        Integer cantidadEntradas,
        Double total
    ) throws MessagingException {
        if (!StringUtils.hasText(email) || !StringUtils.hasText(nombreCliente)) {
            throw new IllegalArgumentException("El email y nombre del cliente son obligatorios");
        }

        String bookingReference = generateBookingReference();
        
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        
        helper.setFrom(fromEmail);
        helper.setTo(email);
        helper.setSubject("Confirmación de Reserva - CineByte #" + bookingReference);
        
        String htmlContent = String.format("""
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
            </head>
            <body style="font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4;">
                <div style="max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 10px; margin-top: 20px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
                    <div style="text-align: center; padding: 20px 0;">
                        <h1 style="color: #2563eb; margin: 0;">¡Gracias por tu reserva, %s!</h1>
                        <p style="color: #4b5563; margin-top: 10px;">Tu reserva ha sido confirmada con el código: <strong>%s</strong></p>
                    </div>
                    
                    <div style="background-color: #f8fafc; padding: 20px; border-radius: 8px; margin: 20px 0;">
                        <h2 style="color: #2563eb; margin-top: 0;">Detalles de la Reserva</h2>
                        <table style="width: 100%%; border-collapse: collapse;">
                            <tr>
                                <td style="padding: 8px 0; color: #4b5563;"><strong>Película:</strong></td>
                                <td style="padding: 8px 0; color: #1f2937;">%s</td>
                            </tr>
                            <tr>
                                <td style="padding: 8px 0; color: #4b5563;"><strong>Sala:</strong></td>
                                <td style="padding: 8px 0; color: #1f2937;">%s</td>
                            </tr>
                            <tr>
                                <td style="padding: 8px 0; color: #4b5563;"><strong>Fecha:</strong></td>
                                <td style="padding: 8px 0; color: #1f2937;">%s</td>
                            </tr>
                            <tr>
                                <td style="padding: 8px 0; color: #4b5563;"><strong>Hora:</strong></td>
                                <td style="padding: 8px 0; color: #1f2937;">%s</td>
                            </tr>
                            <tr>
                                <td style="padding: 8px 0; color: #4b5563;"><strong>Entradas:</strong></td>
                                <td style="padding: 8px 0; color: #1f2937;">%d</td>
                            </tr>
                            <tr>
                                <td style="padding: 8px 0; color: #4b5563;"><strong>Total:</strong></td>
                                <td style="padding: 8px 0; color: #1f2937;">€%.2f</td>
                            </tr>
                        </table>
                    </div>
                    
                    <div style="background-color: #dbeafe; padding: 15px; border-radius: 8px; margin: 20px 0;">
                        <p style="color: #1e40af; margin: 0;">
                            <strong>Importante:</strong> Por favor, llega 15 minutos antes de la función y presenta este email o el código de reserva en la entrada.
                        </p>
                    </div>

                    <div style="text-align: center; margin: 20px 0;">
                        <a href="%s/verificar-reserva/%s" 
                           style="background-color: #2563eb; color: white; padding: 12px 24px; text-decoration: none; border-radius: 6px; display: inline-block;">
                            Ver Reserva Online
                        </a>
                    </div>
                    
                    <div style="margin-top: 30px; padding-top: 20px; border-top: 1px solid #e5e7eb; text-align: center;">
                        <p style="color: #6b7280; margin: 0;">Atentamente,</p>
                        <p style="color: #4b5563; font-weight: bold; margin: 5px 0;">Equipo CineByte</p>
                        <div style="margin-top: 10px;">
                            <a href="%s/contacto" style="color: #2563eb; text-decoration: none; margin: 0 10px;">Contacto</a>
                            <a href="%s/terminos" style="color: #2563eb; text-decoration: none; margin: 0 10px;">Términos y Condiciones</a>
                        </div>
                    </div>
                </div>
            </body>
            </html>
            """,
            nombreCliente,
            bookingReference,
            pelicula,
            sala,
            fecha,
            hora,
            cantidadEntradas,
            total,
            frontendUrl,
            bookingReference,
            frontendUrl,
            frontendUrl
        );
        
        helper.setText(htmlContent, true);
        
        try {
            emailSender.send(message);
        } catch (Exception e) {
            throw new MessagingException("Error al enviar el email de confirmación: " + e.getMessage(), e);
        }
    }

    private String generateBookingReference() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}