package com.yoshira.proyecto.servicio;

import com.yoshira.proyecto.modelo.Tarea;
import com.yoshira.proyecto.modelo.EstadoTarea;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de tareas.
 * Aplica el principio de Responsabilidad Única (SRP) - Solo se encarga de la lógica de negocio de tareas.
 */
public class TareaServicio implements ITareaServicio {
    private List<Tarea> tareas;
    private int siguienteId;
    
    public TareaServicio() {
        this.tareas = new ArrayList<>();
        this.siguienteId = 1;
    }
    
    @Override
    public void crearTarea(Tarea tarea) {
        tarea.setId(siguienteId++);
        tareas.add(tarea);
    }
    
    @Override
    public List<Tarea> obtenerTodasLasTareas() {
        return new ArrayList<>(tareas);
    }
    
    @Override
    public Tarea obtenerTareaPorId(int id) {
        return tareas.stream()
                    .filter(tarea -> tarea.getId() == id)
                    .findFirst()
                    .orElse(null);
    }
    
    @Override
    public void actualizarTarea(Tarea tarea) {
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getId() == tarea.getId()) {
                tareas.set(i, tarea);
                break;
            }
        }
    }
    
    @Override
    public void eliminarTarea(int id) {
        tareas.removeIf(tarea -> tarea.getId() == id);
    }
    
    @Override
    public List<Tarea> obtenerTareasPorEstado(EstadoTarea estado) {
        return tareas.stream()
                    .filter(tarea -> tarea.getEstado() == estado)
                    .collect(Collectors.toList());
    }
}
