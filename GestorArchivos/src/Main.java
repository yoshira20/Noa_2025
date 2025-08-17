import java.io.IOException;
import java.util.List;

/**
 * Clase principal que demuestra el uso del sistema de gestión de archivos
 * Incluye manejo completo de excepciones y casos de prueba
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GestorDeArchivo gestor = new GestorDeArchivo();
        ValidadorArchivo validador = new ValidadorArchivo();
        
        String archivoConContenido = "archivo_con_contenido.txt";
        String archivoVacio = "archivo_vacio.txt";
        
        System.out.println("=== SISTEMA DE GESTIÓN DE ARCHIVOS ===\n");
        
        // Caso 1: Archivo con contenido
        System.out.println("1. PROBANDO ARCHIVO CON CONTENIDO:");
        System.out.println("----------------------------------------");
        
        try {
            String contenido = "Línea 1: Bienvenidos, soy NOA\n" +
                             "Línea 2: Este es un archivo de prueba\n" +
                             "Línea 3: Desarrollado en el lenguaje Java\n" +
                             "Línea 4: Con manejo de excepciones";
            
            gestor.guardar(archivoConContenido, contenido);
            
            List<String> lineasLeidas = gestor.leer(archivoConContenido);
            System.out.println("Contenido leído (" + lineasLeidas.size() + " líneas):");
            for (int i = 0; i < lineasLeidas.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + lineasLeidas.get(i));
            }
            
            validador.verificarNoVacio(archivoConContenido);
            
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        } catch (ArchivoVacioException e) {
            System.err.println("Error de validación: " + e.getMessage());
        }
        
        System.out.println();
        
        // Caso 2: Archivo vacío
        System.out.println("2. PROBANDO ARCHIVO VACÍO:");
        System.out.println("----------------------------------------");
        
        try {
            gestor.guardar(archivoVacio, "");
            
            List<String> lineasVacias = gestor.leer(archivoVacio);
            System.out.println("Líneas leídas del archivo vacío: " + lineasVacias.size());
            
            validador.verificarNoVacio(archivoVacio);
            
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        } catch (ArchivoVacioException e) {
            System.err.println("Excepción capturada correctamente: " + e.getMessage());
        }
        
        System.out.println("\n=== FIN DE LAS PRUEBAS ===");
    }
}
