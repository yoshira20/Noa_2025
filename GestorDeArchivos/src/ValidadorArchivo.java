import java.io.*;

/**
 * Clase encargada de validar el estado de los archivos
 * Verifica si un archivo contiene contenido o está vacío
 */
class ValidadorArchivo {
    
    /**
     * Verifica que un archivo no esté vacío
     * @param nombreArchivo Nombre del archivo a verificar
     * @throws ArchivoVacioException Si el archivo está vacío o no tiene contenido
     * @throws IOException Si ocurre un error al acceder al archivo
     */
    public void verificarNoVacio(String nombreArchivo) throws ArchivoVacioException, IOException {
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String primeraLinea = lector.readLine();
            
            if (primeraLinea == null || primeraLinea.trim().isEmpty()) {
                throw new ArchivoVacioException("El archivo '" + nombreArchivo + "' está vacío o no contiene contenido válido.");
            }
            
            System.out.println("✓ El archivo '" + nombreArchivo + "' contiene contenido válido.");
            
        } catch (FileNotFoundException e) {
            throw new IOException("Archivo no encontrado: " + nombreArchivo, e);
        }
    }
    
    /**
     * Verifica que un archivo de inventario tenga formato válido
     * @param nombreArchivo Nombre del archivo de inventario
     * @return true si el archivo tiene formato válido
     * @throws IOException Si ocurre un error al acceder al archivo
     */
    public boolean verificarFormatoInventario(String nombreArchivo) throws IOException {
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int productosValidos = 0;
            int numeroLinea = 0;
            
            while ((linea = lector.readLine()) != null) {
                numeroLinea++;
                linea = linea.trim();
                
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }
                
                try {
                    Producto.fromFileFormat(linea);
                    productosValidos++;
                } catch (IllegalArgumentException e) {
                    System.err.println("⚠ Error de formato en línea " + numeroLinea + ": " + linea);
                    return false;
                }
            }
            
            System.out.println("✓ Archivo de inventario válido con " + productosValidos + " productos");
            return true;
            
        } catch (FileNotFoundException e) {
            System.out.println("ℹ Archivo de inventario no existe, se considerará válido para creación.");
            return true;
        }
    }
}