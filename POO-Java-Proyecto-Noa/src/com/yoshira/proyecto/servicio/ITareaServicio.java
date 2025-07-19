package com.yoshira.proyecto.servicio;

import com.yoshira.proyecto.modelo.EstadoTarea;
import com.yoshira.proyecto.modelo.Tarea;
import java.util.List;

/**
 * Interfaz que define las operaciones disponibles para el servicio de tareas.
 * Aplica el principio de Segregación de Interfaces (ISP) y Inversión de Dependencias (DIP).
 */
public interface ITareaServicio {
    void crearTarea(Tarea tarea);
    List<Tarea> obtenerTodasLasTareas();
    Tarea obtenerTareaPorId(int id);
    void actualizarTarea(Tarea tarea);
    void eliminarTarea(int id);
    List<Tarea> obtenerTareasPorEstado(EstadoTarea estado);
}