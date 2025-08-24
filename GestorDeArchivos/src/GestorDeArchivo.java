import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar operaciones básicas de archivos
 * Permite guardar y leer contenido de archivos de texto
 * Especializada para el manejo de inventarios
 */
class GestorDeArchivo {
    
    /**
     * Guarda una lista de productos en un archivo
     * @param nombreArchivo Nombre del archivo donde se guardará el inventario
     * @param productos Lista de productos a guardar
     * @throws IOException Si ocurre un error durante la escritura del archivo
     */
    public void guardarInventario(String nombreArchivo, List<Producto> productos) throws IOException {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(nombreArchivo))) {
            // Escribir encabezado
            escritor.println("# Inventario - Formato: ID,Nombre,Precio,Cantidad");
            
            // Escribir cada producto
            for (Producto producto : productos) {
                escritor.println(producto.toFileFormat());
            }
            
            System.out.println("✓ Inventario guardado exitosamente en '" + nombreArchivo + "' (" + productos.size() + " productos)");
            
        } catch (IOException e) {
            System.err.println("✗ Error al guardar el inventario: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Carga la lista de productos desde un archivo
     * @param nombreArchivo Nombre del archivo de inventario
     * @return Lista de productos cargados desde el archivo
     * @throws IOException Si ocurre un error durante la lectura del archivo
     */
    public List<Producto> cargarInventario(String nombreArchivo) throws IOException {
        List<Producto> productos = new ArrayList<>();
        
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int numeroLinea = 0;
            int productosLeidos = 0;
            
            while ((linea = lector.readLine()) != null) {
                numeroLinea++;
                linea = linea.trim();
                
                // Saltar líneas vacías y comentarios
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }
                
                try {
                    Producto producto = Producto.fromFileFormat(linea);
                    productos.add(producto);
                    productosLeidos++;
                } catch (IllegalArgumentException e) {
                    System.err.println("⚠ Advertencia: Error en línea " + numeroLinea + ": " + e.getMessage());
                }
            }
            
            System.out.println("✓ Inventario cargado exitosamente desde '" + nombreArchivo + "' (" + productosLeidos + " productos)");
            return productos;
            
        } catch (FileNotFoundException e) {
            System.out.println("ℹ El archivo '" + nombreArchivo + "' no existe. Se creará uno nuevo.");
            return new ArrayList<>(); // Retorna lista vacía si el archivo no existe
        } catch (IOException e) {
            System.err.println("✗ Error al cargar el inventario: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Guarda contenido genérico en un archivo de texto
     * @param nombreArchivo Nombre del archivo donde se guardará el contenido
     * @param contenido Texto que se escribirá en el archivo
     * @throws IOException Si ocurre un error durante la escritura del archivo
     */
    public void guardar(String nombreArchivo, String contenido) throws IOException {
        try (FileWriter escritor = new FileWriter(nombreArchivo)) {
            escritor.write(contenido);
            System.out.println("✓ Archivo '" + nombreArchivo + "' guardado exitosamente.");
        } catch (IOException e) {
            System.err.println("✗ Error al guardar el archivo: " + e.getMessage());
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
            System.out.println("✓ Archivo '" + nombreArchivo + "' leído exitosamente.");
            return lineas;
        } catch (FileNotFoundException e) {
            System.err.println("✗ El archivo '" + nombreArchivo + "' no fue encontrado.");
            throw e;
        } catch (IOException e) {
            System.err.println("✗ Error al leer el archivo: " + e.getMessage());
            throw e;
        }
    }
}