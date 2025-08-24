/**
 * Clase que representa un producto en el inventario
 * Contiene información básica como ID, nombre, precio y cantidad
 */
class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;
    
    /**
     * Constructor para crear un nuevo producto
     * @param id Identificador único del producto
     * @param nombre Nombre del producto
     * @param precio Precio unitario del producto
     * @param cantidad Cantidad disponible en inventario
     */
    public Producto(int id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    
    // Métodos getter
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    
    // Métodos setter
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    
    /**
     * Convierte el producto a formato de texto para almacenar en archivo
     * Formato: ID,Nombre,Precio,Cantidad
     * @return Representación en texto del producto
     */
    public String toFileFormat() {
        return id + "," + nombre + "," + precio + "," + cantidad;
    }
    
    /**
     * Crea un producto desde una línea de texto del archivo
     * @param linea Línea en formato: ID,Nombre,Precio,Cantidad
     * @return Objeto Producto creado desde la línea
     * @throws IllegalArgumentException Si el formato es incorrecto
     */
    public static Producto fromFileFormat(String linea) throws IllegalArgumentException {
        try {
            String[] partes = linea.split(",");
            if (partes.length != 4) {
                throw new IllegalArgumentException("Formato de línea incorrecto: " + linea);
            }
            
            int id = Integer.parseInt(partes[0].trim());
            String nombre = partes[1].trim();
            double precio = Double.parseDouble(partes[2].trim());
            int cantidad = Integer.parseInt(partes[3].trim());
            
            return new Producto(id, nombre, precio, cantidad);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error al convertir números en la línea: " + linea, e);
        }
    }
    
    /**
     * Representación en string del producto para mostrar al usuario
     */
    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %s | Precio: $%.2f | Cantidad: %d", 
                           id, nombre, precio, cantidad);
    }
}