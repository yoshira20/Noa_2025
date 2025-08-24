/**
 * Clase principal del Sistema de Gestión de Inventarios
 * Punto de entrada del programa
 */
public class Main {
    
    /**
     * Método main - Punto de entrada del programa
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        try {
            // Mostrar información del sistema
            mostrarInformacionSistema();
            
            // Crear e iniciar el sistema de inventario
            SistemaInventario sistema = new SistemaInventario();
            sistema.iniciar();
            
        } catch (Exception e) {
            System.err.println("❌ Error crítico del sistema: " + e.getMessage());
            e.printStackTrace();
            System.out.println("\n🔄 Por favor, reinicie el programa.");
        }
    }
    
    /**
     * Muestra información del sistema al inicio
     */
    private static void mostrarInformacionSistema() {
        System.out.println("💻 Sistema de Gestión de Inventarios v2.0");
        System.out.println("📁 Archivo de datos: inventario.txt");
        System.out.println("🔧 Manejo de excepciones: Activado");
        System.out.println("💾 Persistencia en archivos: Activado");
    }
}