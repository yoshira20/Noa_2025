package com.yoshira.proyecto.dashboard;

import com.yoshira.proyecto.modelo.Tarea;
import com.yoshira.proyecto.modelo.EstadoTarea;
import com.yoshira.proyecto.servicio.ITareaServicio;
import com.yoshira.proyecto.servicio.TareaServicio;
import com.yoshira.proyecto.vista.IVistaConsola;
import com.yoshira.proyecto.vista.VistaConsola;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Clase principal Dashboard que coordina la interacción entre las diferentes capas del sistema.
 * Aplica el principio de Responsabilidad Única (SRP) - Se encarga únicamente de coordinar
 * las operaciones del sistema de gestión de tareas.
 * 
 * Esta clase actúa como controlador principal y punto de entrada de la aplicación,
 * proporcionando una interfaz de usuario basada en consola para gestionar tareas.
 * 
 * @author Yoshira
 */
public class Dashboard {
    private ITareaServicio tareaServicio;
    private IVistaConsola vista;
    private boolean sistemaActivo;
    
    /**
     * Constructor del Dashboard que inicializa los servicios necesarios.
     * Aplica el principio de Inversión de Dependencias (DIP) - Depende de abstracciones.
     */
    public Dashboard() {
        this.tareaServicio = new TareaServicio();
        this.vista = new VistaConsola();
        this.sistemaActivo = true;
        inicializarTareasFicticias();
    }
    
    /**
     * Método principal que inicia el sistema de gestión de tareas.
     * Muestra el menú principal y gestiona la navegación del usuario.
     * 
     * Este método implementa el bucle principal de la aplicación y delega
     * las operaciones específicas a métodos especializados.
     */
    public void iniciarSistema() {
        vista.mostrarMensajeBienvenida();
        
        while (sistemaActivo) {
            vista.mostrarMenu();
            int opcion = vista.leerOpcion();
            
            switch (opcion) {
                case 1:
                    crearNuevaTarea();
                    break;
                case 2:
                    listarTodasLasTareas();
                    break;
                case 3:
                    buscarTareaPorId();
                    break;
                case 4:
                    actualizarEstadoTarea();
                    break;
                case 5:
                    eliminarTarea();
                    break;
                case 6:
                    mostrarTareasPorEstado();
                    break;
                case 7:
                    vista.mostrarEstadoProyecto();
                    break;
                case 0:
                    cerrarSistema();
                    break;
                default:
                    vista.mostrarMensaje("❌ Opción inválida. Por favor, intenta de nuevo.");
            }
        }
    }
    
    /**
     * Inicializa el sistema con tareas ficticias para demostración.
     * Aplica el principio de Responsabilidad Única (SRP).
     */
    private void inicializarTareasFicticias() {
        // Crear tareas de ejemplo
        Tarea tarea1 = new Tarea(0, "Estudiar POO", 
                                "Revisar conceptos de Programación Orientada a Objetos",
                                LocalDate.now().plusDays(7));
        
        Tarea tarea2 = new Tarea(0, "Implementar Dashboard", 
                                "Desarrollar la clase Dashboard con menú interactivo",
                                LocalDate.now().plusDays(3));
        
        Tarea tarea3 = new Tarea(0, "Documentar código", 
                                "Agregar comentarios Javadoc a todas las clases",
                                LocalDate.now().plusDays(2));
        
        tareaServicio.crearTarea(tarea1);
        tareaServicio.crearTarea(tarea2);
        tareaServicio.crearTarea(tarea3);
        
        // Actualizar estado de algunas tareas
        tarea2.setEstado(EstadoTarea.EN_PROGRESO);
        tareaServicio.actualizarTarea(tarea2);
    }
    
    private void crearNuevaTarea() {
        vista.mostrarMensaje("\n=== CREAR NUEVA TAREA ===");
        
        String titulo = vista.leerTexto("Título de la tarea: ");
        String descripcion = vista.leerTexto("Descripción: ");
        
        LocalDate fechaVencimiento = null;
        while (fechaVencimiento == null) {
            String fechaStr = vista.leerTexto("Fecha de vencimiento (yyyy-MM-dd): ");
            try {
                fechaVencimiento = LocalDate.parse(fechaStr, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                vista.mostrarMensaje("❌ Formato de fecha inválido. Use yyyy-MM-dd");
            }
        }
        
        Tarea nuevaTarea = new Tarea(0, titulo, descripcion, fechaVencimiento);
        tareaServicio.crearTarea(nuevaTarea);
        
        vista.mostrarMensaje("✅ Tarea creada exitosamente con ID: " + nuevaTarea.getId());
    }
    
    private void listarTodasLasTareas() {
        vista.mostrarMensaje("\n=== TODAS LAS TAREAS ===");
        List<Tarea> tareas = tareaServicio.obtenerTodasLasTareas();
        
        if (tareas.isEmpty()) {
            vista.mostrarMensaje("📝 No hay tareas registradas.");
        } else {
            for (Tarea tarea : tareas) {
                vista.mostrarMensaje(tarea.toString());
            }
        }
    }
    
    private void buscarTareaPorId() {
        vista.mostrarMensaje("\n=== BUSCAR TAREA POR ID ===");
        String idStr = vista.leerTexto("Ingresa el ID de la tarea: ");
        
        try {
            int id = Integer.parseInt(idStr);
            Tarea tarea = tareaServicio.obtenerTareaPorId(id);
            
            if (tarea != null) {
                vista.mostrarMensaje("✅ Tarea encontrada:");
                vista.mostrarMensaje(tarea.toString());
            } else {
                vista.mostrarMensaje("❌ No se encontró una tarea con ID: " + id);
            }
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("❌ ID inválido. Debe ser un número.");
        }
    }
    
    private void actualizarEstadoTarea() {
        vista.mostrarMensaje("\n=== ACTUALIZAR ESTADO DE TAREA ===");
        String idStr = vista.leerTexto("Ingresa el ID de la tarea: ");
        
        try {
            int id = Integer.parseInt(idStr);
            Tarea tarea = tareaServicio.obtenerTareaPorId(id);
            
            if (tarea != null) {
                vista.mostrarMensaje("Tarea actual: " + tarea.toString());
                vista.mostrarMensaje("\nEstados disponibles:");
                vista.mostrarMensaje("1. Pendiente");
                vista.mostrarMensaje("2. En Progreso");
                vista.mostrarMensaje("3. Completada");
                vista.mostrarMensaje("4. Cancelada");
                
                int opcionEstado = vista.leerOpcion();
                EstadoTarea nuevoEstado = null;
                
                switch (opcionEstado) {
                    case 1: nuevoEstado = EstadoTarea.PENDIENTE; break;
                    case 2: nuevoEstado = EstadoTarea.EN_PROGRESO; break;
                    case 3: nuevoEstado = EstadoTarea.COMPLETADA; break;
                    case 4: nuevoEstado = EstadoTarea.CANCELADA; break;
                    default: vista.mostrarMensaje("❌ Opción inválida."); return;
                }
                
                tarea.setEstado(nuevoEstado);
                tareaServicio.actualizarTarea(tarea);
                vista.mostrarMensaje("✅ Estado actualizado exitosamente.");
            } else {
                vista.mostrarMensaje("❌ No se encontró una tarea con ID: " + id);
            }
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("❌ ID inválido. Debe ser un número.");
        }
    }
    
    private void eliminarTarea() {
        vista.mostrarMensaje("\n=== ELIMINAR TAREA ===");
        String idStr = vista.leerTexto("Ingresa el ID de la tarea a eliminar: ");
        
        try {
            int id = Integer.parseInt(idStr);
            Tarea tarea = tareaServicio.obtenerTareaPorId(id);
            
            if (tarea != null) {
                vista.mostrarMensaje("Tarea a eliminar: " + tarea.toString());
                String confirmacion = vista.leerTexto("¿Estás seguro? (s/n): ");
                
                if (confirmacion.toLowerCase().equals("s")) {
                    tareaServicio.eliminarTarea(id);
                    vista.mostrarMensaje("✅ Tarea eliminada exitosamente.");
                } else {
                    vista.mostrarMensaje("❌ Eliminación cancelada.");
                }
            } else {
                vista.mostrarMensaje("❌ No se encontró una tarea con ID: " + id);
            }
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("❌ ID inválido. Debe ser un número.");
        }
    }
    
    private void mostrarTareasPorEstado() {
        vista.mostrarMensaje("\n=== TAREAS POR ESTADO ===");
        vista.mostrarMensaje("1. Pendientes");
        vista.mostrarMensaje("2. En Progreso");
        vista.mostrarMensaje("3. Completadas");
        vista.mostrarMensaje("4. Canceladas");
        
        int opcion = vista.leerOpcion();
        EstadoTarea estado = null;
        
        switch (opcion) {
            case 1: estado = EstadoTarea.PENDIENTE; break;
            case 2: estado = EstadoTarea.EN_PROGRESO; break;
            case 3: estado = EstadoTarea.COMPLETADA; break;
            case 4: estado = EstadoTarea.CANCELADA; break;
            default: vista.mostrarMensaje("❌ Opción inválida."); return;
        }
        
        List<Tarea> tareas = tareaServicio.obtenerTareasPorEstado(estado);
        
        if (tareas.isEmpty()) {
            vista.mostrarMensaje("📝 No hay tareas con estado: " + estado);
        } else {
            vista.mostrarMensaje("\n=== TAREAS " + estado.toString().toUpperCase() + " ===");
            for (Tarea tarea : tareas) {
                vista.mostrarMensaje(tarea.toString());
            }
        }
    }
    
    private void cerrarSistema() {
        vista.mostrarMensaje("\n¡Gracias por usar el Sistema de Gestión de Tareas!");
        vista.mostrarMensaje("Desarrollado con principios SOLID y buenas prácticas de POO.");
        sistemaActivo = false;
    }
    
    /**
     * Método principal de la aplicación.
     * Punto de entrada del programa.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        Dashboard dashboard = new Dashboard();
        dashboard.iniciarSistema();
    }
}