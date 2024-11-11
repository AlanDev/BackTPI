package com.cine.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "horarios")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalTime hora;
    
    @Column(nullable = false)
    private String tipo; 
    
    @Column(nullable = false)
    private String dia; 
    
    private String descripcion;
    
    @Column(nullable = false)
    private String estado; 

    public Horario() {
    }

    public Horario(LocalTime hora, String tipo, String dia, String descripcion, String estado) {
        this.hora = hora;
        this.tipo = tipo;
        this.dia = dia;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "id=" + id +
                ", hora=" + hora +
                ", tipo='" + tipo + '\'' +
                ", dia='" + dia + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}