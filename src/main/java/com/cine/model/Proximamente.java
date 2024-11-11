package com.cine.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "proximamente")
public class Proximamente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(nullable = false)
    private Integer duracion;
    
    private String clasificacion;
    private String genero;
    private String estado;
    
    @Column(columnDefinition = "TEXT")
    private String sinopsis;
    
    @Column(columnDefinition = "DECIMAL(3,1)")
    private Double calificacion;
    
    @Column(name = "fecha_estreno", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaEstreno;
    
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Proximamente() {
    }

    public Proximamente(String titulo, Integer duracion, String clasificacion, 
                       String genero, String estado, String sinopsis, 
                       Double calificacion, Date fechaEstreno) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
        this.genero = genero;
        this.estado = estado;
        this.sinopsis = sinopsis;
        this.calificacion = calificacion;
        this.fechaEstreno = fechaEstreno;
        this.createdAt = new Date();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public Date getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(Date fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Proximamente{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", duracion=" + duracion +
                ", clasificacion='" + clasificacion + '\'' +
                ", genero='" + genero + '\'' +
                ", estado='" + estado + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                ", calificacion=" + calificacion +
                ", fechaEstreno=" + fechaEstreno +
                ", createdAt=" + createdAt +
                '}';
    }
} 