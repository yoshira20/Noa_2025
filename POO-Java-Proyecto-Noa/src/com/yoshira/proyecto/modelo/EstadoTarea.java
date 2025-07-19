package com.yoshira.proyecto.modelo;

/**
 * Enumeración que representa los posibles estados de una tarea.
 */
public enum EstadoTarea {
    PENDIENTE("Pendiente"),
    EN_PROGRESO("En Progreso"),
    COMPLETADA("Completada"),
    CANCELADA("Cancelada");
    
    private final String descripcion;
    
    EstadoTarea(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    @Override
    public String toString() {
        return descripcion;
    }
}