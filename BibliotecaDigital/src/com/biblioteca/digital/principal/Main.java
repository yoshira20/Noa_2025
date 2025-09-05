public class Main {
    public static void main(String[] args) {
        ejecutarFlujoDePrueba();
    }
    
    public static void ejecutarFlujoDePrueba() {
        System.out.println("Biblioteca Digital inicializada correctamente");
        System.out.println("================================================");
        System.out.println("   SISTEMA DE GESTIÓN DE BIBLIOTECA DIGITAL   ");
        System.out.println("================================================");
        
        // Crear una instancia de la biblioteca
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");
        
        System.out.println("\n1. Creando biblioteca: " + biblioteca.getNombre());
        
        // Crear algunos libros
        Libro libro1 = new Libro("Cien años de soledad", "Gabriel García Márquez", "978-84-376-0494-7");
        Libro libro2 = new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "978-84-376-0495-4");
        Libro libro3 = new Libro("1984", "George Orwell", "978-84-376-0496-1");
        Libro libro4 = new Libro("El Principito", "Antoine de Saint-Exupéry", "978-84-376-0497-8");
        
        // Agregar libros a la biblioteca
        System.out.println("\n2. Agregando libros a la biblioteca:");
        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);
        biblioteca.agregarLibro(libro3);
        biblioteca.agregarLibro(libro4);
        
        // Crear algunos usuarios
        Usuario usuario1 = new Usuario("Juan Pérez", "U001", "juan.perez@email.com");
        Usuario usuario2 = new Usuario("María García", "U002", "maria.garcia@email.com");
        Usuario usuario3 = new Usuario("Carlos López", "U003", "carlos.lopez@email.com");
        
        // Registrar usuarios
        System.out.println("\n3. Registrando usuarios:");
        biblioteca.agregarUsuario(usuario1);
        biblioteca.agregarUsuario(usuario2);
        biblioteca.agregarUsuario(usuario3);
        
        // Mostrar libros disponibles
        biblioteca.mostrarLibrosDisponibles();
        
        // Realizar algunos préstamos
        System.out.println("\n4. Realizando préstamos:");
        biblioteca.prestarLibro("978-84-376-0494-7", "U001"); // Cien años de soledad a Juan
        biblioteca.prestarLibro("978-84-376-0495-4", "U001"); // Don Quijote a Juan
        biblioteca.prestarLibro("978-84-376-0496-1", "U002"); // 1984 a María
        
        // Mostrar libros prestados por usuario
        System.out.println("\n5. Libros prestados por usuario:");
        usuario1.mostrarLibrosPrestados();
        usuario2.mostrarLibrosPrestados();
        usuario3.mostrarLibrosPrestados();
        
        // Mostrar libros disponibles después de los préstamos
        biblioteca.mostrarLibrosDisponibles();
        
        // Intentar prestar un libro no disponible
        System.out.println("\n6. Intentando prestar un libro ya prestado:");
        biblioteca.prestarLibro("978-84-376-0494-7", "U003"); // Debería fallar
        
        // Devolver un libro
        System.out.println("\n7. Devolviendo un libro:");
        biblioteca.devolverLibro("978-84-376-0494-7", "U001");
        
        // Mostrar estado final
        System.out.println("\n8. Estado final de la biblioteca:");
        biblioteca.mostrarTodosLosLibros();
        
        // Buscar libros
        System.out.println("\n9. Búsquedas de prueba:");
        Libro libroEncontrado = biblioteca.buscarLibroPorTitulo("1984");
        if (libroEncontrado != null) {
            System.out.println("Libro encontrado: " + libroEncontrado);
        }
        
        // Mostrar usuarios registrados
        biblioteca.mostrarUsuarios();
        
        System.out.println("\n================================================");
        System.out.println("       PRUEBA DEL SISTEMA COMPLETADA          ");
        System.out.println("================================================");
    }
}