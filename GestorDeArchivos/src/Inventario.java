import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona el inventario de productos con persistencia en archivos
 * Permite agregar, actualizar, eliminar y buscar productos
 */
class Inventario {
    private List<Producto> productos;
    private GestorDeArchivo gestorArchivo;
    private String nombreArchivo;
    private int siguienteId;
    
    /**
     * Constructor del inventario
     * @param nombreArchivo Nombre del archivo donde se almacena el inventario
     */
    public Inventario(String nombreArchivo) {
        this.productos = new ArrayList<>();
        this.gestorArchivo = new GestorDeArchivo();
        this.nombreArchivo = nombreArchivo;
        this.siguienteId = 1;
        
        // Cargar inventario existente al inicializar
        cargarInventario();
    }
    
    /**
     * Carga el inventario desde el archivo
     */
    private void cargarInventario() {
        try {
            this.productos = gestorArchivo.cargarInventario(nombreArchivo);
            
            // Calcular el siguiente ID disponible
            if (!productos.isEmpty()) {
                this.siguienteId = productos.stream()
                    .mapToInt(Producto::getId)
                    .max()
                    .orElse(0) + 1;
            }
            
        } catch (IOException e) {
            System.err.println("✗ Error al cargar inventario inicial: " + e.getMessage());
            System.out.println("ℹ Se iniciará con inventario vacío.");
        }
    }
    
    /**
     * Guarda el inventario actual en el archivo
     * @throws IOException Si ocurre un error al guardar
     */
    private void guardarInventario() throws IOException {
        gestorArchivo.guardarInventario(nombreArchivo, productos);
    }
    
    /**
     * Agrega un nuevo producto al inventario
     * @param nombre Nombre del producto
     * @param precio Precio del producto
     * @param cantidad Cantidad inicial
     * @return true si se agregó exitosamente
     */
    public boolean agregarProducto(String nombre, double precio, int cantidad) {
        try {
            // Validar entrada
            if (nombre == null || nombre.trim().isEmpty()) {
                System.err.println("✗ Error: El nombre del producto no puede estar vacío");
                return false;
            }
            
            if (precio < 0) {
                System.err.println("✗ Error: El precio no puede ser negativo");
                return false;
            }
            
            if (cantidad < 0) {
                System.err.println("✗ Error: La cantidad no puede ser negativa");
                return false;
            }
            
            // Crear producto
            Producto nuevoProducto = new Producto(siguienteId++, nombre.trim(), precio, cantidad);
            productos.add(nuevoProducto);
            
            // Guardar en archivo
            guardarInventario();
            
            System.out.println("✓ Producto agregado exitosamente:");
            System.out.println("  " + nuevoProducto);
            return true;
            
        } catch (IOException e) {
            System.err.println("✗ Error al guardar el producto: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Busca un producto por su ID
     * @param id ID del producto a buscar
     * @return Producto encontrado
     * @throws ProductoNoEncontradoException Si el producto no existe
     */
    public Producto buscarProducto(int id) throws ProductoNoEncontradoException {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        throw new ProductoNoEncontradoException("Producto con ID " + id + " no encontrado");
    }
    
    /**
     * Actualiza un producto existente
     * @param id ID del producto a actualizar
     * @param nuevoNombre Nuevo nombre (null para mantener el actual)
     * @param nuevoPrecio Nuevo precio (-1 para mantener el actual)
     * @param nuevaCantidad Nueva cantidad (-1 para mantener la actual)
     * @return true si se actualizó exitosamente
     */
    public boolean actualizarProducto(int id, String nuevoNombre, double nuevoPrecio, int nuevaCantidad) {
        try {
            Producto producto = buscarProducto(id);
            
            // Actualizar campos si se proporcionan valores válidos
            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                producto.setNombre(nuevoNombre.trim());
            }
            
            if (nuevoPrecio >= 0) {
                producto.setPrecio(nuevoPrecio);
            }
            
            if (nuevaCantidad >= 0) {
                producto.setCantidad(nuevaCantidad);
            }
            
            // Guardar cambios
            guardarInventario();
            
            System.out.println("✓ Producto actualizado exitosamente:");
            System.out.println("  " + producto);
            return true;
            
        } catch (ProductoNoEncontradoException e) {
            System.err.println("✗ " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("✗ Error al guardar los cambios: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Elimina un producto del inventario
     * @param id ID del producto a eliminar
     * @return true si se eliminó exitosamente
     */
    public boolean eliminarProducto(int id) {
        try {
            Producto producto = buscarProducto(id);
            productos.remove(producto);
            
            // Guardar cambios
            guardarInventario();
            
            System.out.println("✓ Producto eliminado exitosamente:");
            System.out.println("  " + producto);
            return true;
            
        } catch (ProductoNoEncontradoException e) {
            System.err.println("✗ " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("✗ Error al guardar los cambios: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Muestra todos los productos en el inventario
     */
    public void mostrarInventario() {
        if (productos.isEmpty()) {
            System.out.println("ℹ El inventario está vacío");
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                           INVENTARIO ACTUAL");
        System.out.println("=".repeat(80));
        
        for (int i = 0; i < productos.size(); i++) {
            System.out.printf("%3d. %s%n", (i + 1), productos.get(i));
        }
        
        System.out.println("=".repeat(80));
        System.out.println("Total de productos: " + productos.size());
        
        // Calcular valor total del inventario
        double valorTotal = productos.stream()
            .mapToDouble(p -> p.getPrecio() * p.getCantidad())
            .sum();
        System.out.printf("Valor total del inventario: $%.2f%n", valorTotal);
        System.out.println("=".repeat(80));
    }
    
    /**
     * Busca productos por nombre (búsqueda parcial)
     * @param nombre Nombre o parte del nombre a buscar
     * @return Lista de productos que coinciden
     */
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> resultados = new ArrayList<>();
        String nombreBusqueda = nombre.toLowerCase().trim();
        
        for (Producto producto : productos) {
            if (producto.getNombre().toLowerCase().contains(nombreBusqueda)) {
                resultados.add(producto);
            }
        }
        
        return resultados;
    }
    
    /**
     * Obtiene estadísticas del inventario
     */
    public void mostrarEstadisticas() {
        if (productos.isEmpty()) {
            System.out.println("ℹ No hay productos para mostrar estadísticas");
            return;
        }
        
        int totalProductos = productos.size();
        int totalUnidades = productos.stream().mapToInt(Producto::getCantidad).sum();
        double valorTotal = productos.stream().mapToDouble(p -> p.getPrecio() * p.getCantidad()).sum();
        double precioPromedio = productos.stream().mapToDouble(Producto::getPrecio).average().orElse(0);
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("            ESTADÍSTICAS DEL INVENTARIO");
        System.out.println("=".repeat(50));
        System.out.println("Total de productos diferentes: " + totalProductos);
        System.out.println("Total de unidades en stock: " + totalUnidades);
        System.out.printf("Valor total del inventario: $%.2f%n", valorTotal);
        System.out.printf("Precio promedio por producto: $%.2f%n", precioPromedio);
        System.out.println("=".repeat(50));
    }
}