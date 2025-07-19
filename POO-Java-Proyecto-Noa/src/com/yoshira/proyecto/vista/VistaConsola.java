package com.yoshira.proyecto.vista;

import java.util.Scanner;

/**
 * Implementación de la vista de consola.
 * Aplica el principio de Responsabilidad Única (SRP) - Solo se encarga de la interfaz de usuario.
 */
public class VistaConsola implements IVistaConsola {
    private Scanner scanner;
    
    public VistaConsola() {
        this.scanner = new Scanner(System.in);
    }
    
    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    @Override
    public void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GESTIÓN DE TAREAS ===");
        System.out.println("1. Crear nueva tarea");
        System.out.println("2. Listar todas las tareas");
        System.out.println("3. Buscar tarea por ID");
        System.out.println("4. Actualizar estado de tarea");
        System.out.println("5. Eliminar tarea");
        System.out.println("6. Mostrar tareas por estado");
        System.out.println("7. Mostrar estado del proyecto");
        System.out.println("0. Salir");
        System.out.print("Selecciona una opción: ");
    }
    
    @Override
    public void mostrarMensajeBienvenida() {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                 BIENVENIDO AL SISTEMA DE TAREAS             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Este sistema te permite gestionar tus tareas de manera     ║");
        System.out.println("║  organizada y eficiente.                                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Desarrollado con principios de POO y buenas prácticas.     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
    
    @Override
    public void mostrarEstadoProyecto() {
        System.out.println("\n=== ESTADO DEL PROYECTO ===");
        System.out.println("✓ Sistema de gestión de tareas activo");
        System.out.println("✓ Aplicando principios SOLID");
        System.out.println("✓ Arquitectura por capas implementada");
        System.out.println("✓ Documentación con Javadoc incluida");
        System.out.println("✓ Proyecto listo para producción");
    }
    
    @Override
    public int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    @Override
    public String leerTexto(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
