package com.yoshira.proyecto.modelo;

import java.time.LocalDate;

/**
 * Clase que representa una tarea en el sistema de gestión.
 */
public class Tarea {
    private int id;
    private String titulo;
    private String descripcion;
    private EstadoTarea estado;
    private LocalDate fechaCreacion;
    private LocalDate fechaVencimiento;
    
    public Tarea(int id, String titulo, String descripcion, LocalDate fechaVencimiento) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = EstadoTarea.PENDIENTE;
        this.fechaCreacion = LocalDate.now();
        this.fechaVencimiento = fechaVencimiento;
    }
    
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public EstadoTarea getEstado() { return estado; }
    public void setEstado(EstadoTarea estado) { this.estado = estado; }
    
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    
    @Override
    public String toString() {
        return String.format("[ID: %d] %s - %s (Estado: %s, Vence: %s)", 
                           id, titulo, descripcion, estado, fechaVencimiento);
    }
}