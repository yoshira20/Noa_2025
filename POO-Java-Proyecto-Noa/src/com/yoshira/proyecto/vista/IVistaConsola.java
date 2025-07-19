// Archivo: com/yoshira/proyecto/vista/IVistaConsola.java
package com.yoshira.proyecto.vista;

/**
 * Interfaz para la vista de consola.
 * Aplica el principio de Segregación de Interfaces (ISP).
 */
public interface IVistaConsola {
    void mostrarMensaje(String mensaje);
    void mostrarMenu();
    void mostrarMensajeBienvenida();
    void mostrarEstadoProyecto();
    int leerOpcion();
    String leerTexto(String prompt);
}