package com.cine.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "salas")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Integer numero;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private Integer capacidad;
    
    @Column(nullable = false)
    private String tipo;
    
    @Column(name = "horario_apertura")
    private LocalTime horarioApertura;
    
    @Column(name = "horario_cierre")
    private LocalTime horarioCierre;
    
    private String estado;

    public Sala() {
    }

    public Sala(Integer numero, String nombre, Integer capacidad, String tipo, 
                LocalTime horarioApertura, LocalTime horarioCierre, String estado) {
        this.numero = numero;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalTime getHorarioApertura() {
        return horarioApertura;
    }

    public void setHorarioApertura(LocalTime horarioApertura) {
        this.horarioApertura = horarioApertura;
    }

    public LocalTime getHorarioCierre() {
        return horarioCierre;
    }

    public void setHorarioCierre(LocalTime horarioCierre) {
        this.horarioCierre = horarioCierre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "id=" + id +
                ", numero=" + numero +
                ", nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                ", tipo='" + tipo + '\'' +
                ", horarioApertura=" + horarioApertura +
                ", horarioCierre=" + horarioCierre +
                ", estado='" + estado + '\'' +
                '}';
    }
}