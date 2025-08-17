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
            
            System.out.println("El archivo '" + nombreArchivo + "' contiene contenido válido.");
            
        } catch (FileNotFoundException e) {
            System.err.println("El archivo '" + nombreArchivo + "' no existe.");
            throw new IOException("Archivo no encontrado: " + nombreArchivo, e);
        }
    }
}
