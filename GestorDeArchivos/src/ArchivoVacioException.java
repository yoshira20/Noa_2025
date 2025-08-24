/**
 * Excepción personalizada que se lanza cuando un archivo está vacío
 * Extiende de Exception para crear una excepción verificada
 */
class ArchivoVacioException extends Exception {
    
    /**
     * Constructor por defecto de la excepción
     */
    public ArchivoVacioException() {
        super("El archivo está vacío");
    }
    
    /**
     * Constructor que permite personalizar el mensaje de error
     * @param mensaje Mensaje personalizado para la excepción
     */
    public ArchivoVacioException(String mensaje) {
        super(mensaje);
    }
}