import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar operaciones básicas de archivos
 * Permite guardar y leer contenido de archivos de texto
 */
class GestorDeArchivo {
    
    /**
     * Guarda contenido en un archivo de texto
     * @param nombreArchivo Nombre del archivo donde se guardará el contenido
     * @param contenido Texto que se escribirá en el archivo
     * @throws IOException Si ocurre un error durante la escritura del archivo
     */
    public void guardar(String nombreArchivo, String contenido) throws IOException {
        try (FileWriter escritor = new FileWriter(nombreArchivo)) {
            escritor.write(contenido);
            System.out.println("Archivo '" + nombreArchivo + "' guardado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Lee el contenido completo de un archivo línea por línea
     * @param nombreArchivo Nombre del archivo a leer
     * @return Lista de cadenas, cada una representando una línea del archivo
     * @throws IOException Si ocurre un error durante la lectura del archivo
     */
    public List<String> leer(String nombreArchivo) throws IOException {
        List<String> lineas = new ArrayList<>();
        
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas.add(linea);
            }
            System.out.println("Archivo '" + nombreArchivo + "' leído exitosamente.");
            return lineas;
        } catch (FileNotFoundException e) {
            System.err.println("El archivo '" + nombreArchivo + "' no fue encontrado.");
            throw e;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            throw e;
        }
    }
}
