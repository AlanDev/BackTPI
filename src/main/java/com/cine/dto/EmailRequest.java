package com.cine.dto;

public class EmailRequest {
    private String email;
    private String nombreCliente;
    private String pelicula;
    private String sala;
    private String fecha;
    private String hora;
    private int cantidadEntradas;
    private double total;

    // Getters y Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    
    public String getPelicula() { return pelicula; }
    public void setPelicula(String pelicula) { this.pelicula = pelicula; }
    
    public String getSala() { return sala; }
    public void setSala(String sala) { this.sala = sala; }
    
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    
    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
    
    public int getCantidadEntradas() { return cantidadEntradas; }
    public void setCantidadEntradas(int cantidadEntradas) { this.cantidadEntradas = cantidadEntradas; }
    
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return "EmailRequest{" +
                "email='" + email + '\'' +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", pelicula='" + pelicula + '\'' +
                ", sala='" + sala + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", cantidadEntradas=" + cantidadEntradas +
                ", total=" + total +
                '}';
    }
}