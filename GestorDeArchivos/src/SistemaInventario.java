import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Sistema de interfaz de usuario para la gestión del inventario
 * Proporciona un menú interactivo en consola
 */
class SistemaInventario {
    private Inventario inventario;
    private Scanner scanner;
    private ValidadorArchivo validador;
    
    /**
     * Constructor del sistema de inventario
     */
    public SistemaInventario() {
        this.inventario = new Inventario("inventario.txt");
        this.scanner = new Scanner(System.in);
        this.validador = new ValidadorArchivo();
    }
    
    /**
     * Inicia el sistema de inventario con menú interactivo
     */
    public void iniciar() {
        mostrarBienvenida();
        
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    agregarProductoInteractivo();
                    break;
                case 2:
                    mostrarInventarioCompleto();
                    break;
                case 3:
                    buscarProductoInteractivo();
                    break;
                case 4:
                    actualizarProductoInteractivo();
                    break;
                case 5:
                    eliminarProductoInteractivo();
                    break;
                case 6:
                    buscarPorNombreInteractivo();
                    break;
                case 7:
                    mostrarEstadisticasInventario();
                    break;
                case 8:
                    verificarArchivoInventario();
                    break;
                case 0:
                    continuar = false;
                    mostrarDespedida();
                    break;
                default:
                    System.out.println("✗ Opción no válida. Por favor, seleccione una opción del 0 al 8.");
            }
            
            if (continuar) {
                pausar();
            }
        }
    }
    
    /**
     * Muestra el mensaje de bienvenida
     */
    private void mostrarBienvenida() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                    SISTEMA DE GESTIÓN DE INVENTARIOS");
        System.out.println("                         Con Persistencia en Archivos");
        System.out.println("=".repeat(80));
    }
    
    /**
     * Muestra el menú principal
     */
    private void mostrarMenu() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("                        MENÚ PRINCIPAL");
        System.out.println("═".repeat(60));
        System.out.println("1. 📦 Agregar Producto");
        System.out.println("2. 📋 Mostrar Inventario Completo");
        System.out.println("3. 🔍 Buscar Producto por ID");
        System.out.println("4. ✏️  Actualizar Producto");
        System.out.println("5. 🗑️  Eliminar Producto");
        System.out.println("6. 🔎 Buscar Productos por Nombre");
        System.out.println("7. 📊 Mostrar Estadísticas");
        System.out.println("8. 🔧 Verificar Archivo de Inventario");
        System.out.println("0. 🚪 Salir del Sistema");
        System.out.println("═".repeat(60));
        System.out.print("Seleccione una opción: ");
    }
    
    /**
     * Lee la opción seleccionada por el usuario
     */
    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Proceso interactivo para agregar un producto
     */
    private void agregarProductoInteractivo() {
        System.out.println("\n📦 AGREGAR NUEVO PRODUCTO");
        System.out.println("-".repeat(40));
        
        try {
            System.out.print("Nombre del producto: ");
            String nombre = scanner.nextLine().trim();
            
            System.out.print("Precio del producto: $");
            double precio = Double.parseDouble(scanner.nextLine().trim());
            
            System.out.print("Cantidad inicial: ");
            int cantidad = Integer.parseInt(scanner.nextLine().trim());
            
            if (inventario.agregarProducto(nombre, precio, cantidad)) {
                System.out.println("\n🎉 ¡Producto agregado exitosamente al inventario y guardado en archivo!");
            } else {
                System.out.println("\n❌ No se pudo agregar el producto. Verifique los datos ingresados.");
            }
            
        } catch (NumberFormatException e) {
            System.err.println("✗ Error: Ingrese valores numéricos válidos para precio y cantidad.");
        }
    }
    
    /**
     * Muestra el inventario completo
     */
    private void mostrarInventarioCompleto() {
        inventario.mostrarInventario();
    }
    
    /**
     * Proceso interactivo para buscar un producto por ID
     */
    private void buscarProductoInteractivo() {
        System.out.println("\n🔍 BUSCAR PRODUCTO POR ID");
        System.out.println("-".repeat(40));
        
        try {
            System.out.print("Ingrese el ID del producto: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            Producto producto = inventario.buscarProducto(id);
            
            System.out.println("\n✓ Producto encontrado:");
            System.out.println("  " + producto);
            
        } catch (NumberFormatException e) {
            System.err.println("✗ Error: Ingrese un ID numérico válido.");
        } catch (ProductoNoEncontradoException e) {
            System.err.println("✗ " + e.getMessage());
        }
    }
    
    /**
     * Proceso interactivo para actualizar un producto
     */
    private void actualizarProductoInteractivo() {
        System.out.println("\n✏️ ACTUALIZAR PRODUCTO");
        System.out.println("-".repeat(40));
        
        try {
            System.out.print("Ingrese el ID del producto a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            Producto productoActual = inventario.buscarProducto(id);
            System.out.println("\nProducto actual:");
            System.out.println("  " + productoActual);
            
            System.out.println("\nIngrese los nuevos valores (presione Enter para mantener el valor actual):");
            
            System.out.print("Nuevo nombre [" + productoActual.getNombre() + "]: ");
            String nuevoNombre = scanner.nextLine().trim();
            if (nuevoNombre.isEmpty()) {
                nuevoNombre = null;
            }
            
            System.out.print("Nuevo precio [" + productoActual.getPrecio() + "]: $");
            String precioStr = scanner.nextLine().trim();
            double nuevoPrecio = precioStr.isEmpty() ? -1 : Double.parseDouble(precioStr);
            
            System.out.print("Nueva cantidad [" + productoActual.getCantidad() + "]: ");
            String cantidadStr = scanner.nextLine().trim();
            int nuevaCantidad = cantidadStr.isEmpty() ? -1 : Integer.parseInt(cantidadStr);
            
            if (inventario.actualizarProducto(id, nuevoNombre, nuevoPrecio, nuevaCantidad)) {
                System.out.println("\n🎉 ¡Producto actualizado exitosamente!");
            }
            
        } catch (NumberFormatException e) {
            System.err.println("✗ Error: Ingrese valores numéricos válidos.");
        } catch (ProductoNoEncontradoException e) {
            System.err.println("✗ " + e.getMessage());
        }
    }
    
    /**
     * Proceso interactivo para eliminar un producto
     */
    private void eliminarProductoInteractivo() {
        System.out.println("\n🗑️ ELIMINAR PRODUCTO");
        System.out.println("-".repeat(40));
        
        try {
            System.out.print("Ingrese el ID del producto a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            Producto producto = inventario.buscarProducto(id);
            System.out.println("\nProducto a eliminar:");
            System.out.println("  " + producto);
            
            System.out.print("\n¿Está seguro de que desea eliminar este producto? (s/N): ");
            String confirmacion = scanner.nextLine().trim().toLowerCase();
            
            if (confirmacion.equals("s") || confirmacion.equals("si")) {
                if (inventario.eliminarProducto(id)) {
                    System.out.println("\n🎉 ¡Producto eliminado exitosamente!");
                }
            } else {
                System.out.println("❌ Eliminación cancelada.");
            }
            
        } catch (NumberFormatException e) {
            System.err.println("✗ Error: Ingrese un ID numérico válido.");
        } catch (ProductoNoEncontradoException e) {
            System.err.println("✗ " + e.getMessage());
        }
    }
    
    /**
     * Proceso interactivo para buscar productos por nombre
     */
    private void buscarPorNombreInteractivo() {
        System.out.println("\n🔎 BUSCAR PRODUCTOS POR NOMBRE");
        System.out.println("-".repeat(40));
        
        System.out.print("Ingrese el nombre o parte del nombre a buscar: ");
        String nombre = scanner.nextLine().trim();
        
        if (nombre.isEmpty()) {
            System.err.println("✗ Error: Debe ingresar un nombre para buscar.");
            return;
        }
        
        List<Producto> resultados = inventario.buscarPorNombre(nombre);
        
        if (resultados.isEmpty()) {
            System.out.println("❌ No se encontraron productos que coincidan con: " + nombre);
        } else {
            System.out.println("\n✓ Se encontraron " + resultados.size() + " producto(s):");
            System.out.println("-".repeat(40));
            for (int i = 0; i < resultados.size(); i++) {
                System.out.printf("%3d. %s%n", (i + 1), resultados.get(i));
            }
        }
    }
    
    /**
     * Muestra las estadísticas del inventario
     */
    private void mostrarEstadisticasInventario() {
        inventario.mostrarEstadisticas();
    }
    
    /**
     * Verifica la integridad del archivo de inventario
     */
    private void verificarArchivoInventario() {
        System.out.println("\n🔧 VERIFICAR ARCHIVO DE INVENTARIO");
        System.out.println("-".repeat(40));
        
        try {
            validador.verificarNoVacio("inventario.txt");
            
            if (validador.verificarFormatoInventario("inventario.txt")) {
                System.out.println("✅ El archivo de inventario está en perfecto estado.");
            } else {
                System.out.println("⚠️ El archivo de inventario tiene problemas de formato.");
            }
            
        } catch (ArchivoVacioException e) {
            System.out.println("ℹ️ " + e.getMessage());
        } catch (IOException e) {
            System.err.println("✗ Error al verificar el archivo: " + e.getMessage());
        }
    }
    
    /**
     * Pausa la ejecución hasta que el usuario presione Enter
     */
    private void pausar() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
    
    /**
     * Muestra el mensaje de despedida
     */
    private void mostrarDespedida() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                  ¡GRACIAS POR USAR EL SISTEMA!");
        System.out.println("                   Todos los cambios fueron guardados");
        System.out.println("=".repeat(80));
        System.out.println("🔒 Sistema cerrado correctamente.");
    }
}