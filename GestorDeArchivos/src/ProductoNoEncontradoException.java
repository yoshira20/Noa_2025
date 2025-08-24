/**
 * Excepción personalizada que se lanza cuando un producto no es encontrado en el inventario
 */
class ProductoNoEncontradoException extends Exception {
    
    /**
     * Constructor por defecto de la excepción
     */
    public ProductoNoEncontradoException() {
        super("Producto no encontrado en el inventario");
    }
    
    /**
     * Constructor que permite personalizar el mensaje de error
     * @param mensaje Mensaje personalizado para la excepción
     */
    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
