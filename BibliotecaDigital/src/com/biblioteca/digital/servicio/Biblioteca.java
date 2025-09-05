import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nombre;
    private List<Libro> libros;
    private List<Usuario> usuarios;
    
    // Constructor
    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.libros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }
    
    public List<Libro> getLibros() {
        return libros;
    }
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    
    // Métodos para gestionar libros
    public void agregarLibro(Libro libro) {
        libros.add(libro);
        System.out.println("Libro agregado: " + libro.getTitulo());
    }
    
    public boolean eliminarLibro(String isbn) {
        for (Libro libro : libros) {
            if (libro.getIsbn().equals(isbn)) {
                if (libro.isDisponible()) {
                    libros.remove(libro);
                    System.out.println("Libro eliminado: " + libro.getTitulo());
                    return true;
                } else {
                    System.out.println("No se puede eliminar el libro. Está prestado.");
                    return false;
                }
            }
        }
        System.out.println("Libro no encontrado con ISBN: " + isbn);
        return false;
    }
    
    public Libro buscarLibroPorTitulo(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                return libro;
            }
        }
        return null;
    }
    
    public Libro buscarLibroPorIsbn(String isbn) {
        for (Libro libro : libros) {
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }
    
    public List<Libro> buscarLibrosPorAutor(String autor) {
        List<Libro> librosEncontrados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                librosEncontrados.add(libro);
            }
        }
        return librosEncontrados;
    }
    
    // Métodos para gestionar usuarios
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuario registrado: " + usuario.getNombre());
    }
    
    public Usuario buscarUsuario(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }
    
    // Métodos para préstamos
    public boolean prestarLibro(String isbnLibro, String idUsuario) {
        Libro libro = buscarLibroPorIsbn(isbnLibro);
        Usuario usuario = buscarUsuario(idUsuario);
        
        if (libro == null) {
            System.out.println("Libro no encontrado");
            return false;
        }
        
        if (usuario == null) {
            System.out.println("Usuario no encontrado");
            return false;
        }
        
        if (!libro.isDisponible()) {
            System.out.println("El libro no está disponible");
            return false;
        }
        
        if (!usuario.puedeTomarPrestado()) {
            System.out.println("El usuario ha alcanzado el límite de préstamos");
            return false;
        }
        
        if (usuario.tomarLibroPrestado(libro)) {
            System.out.println("Préstamo realizado exitosamente");
            System.out.println("Libro: " + libro.getTitulo() + " prestado a: " + usuario.getNombre());
            return true;
        }
        
        return false;
    }
    
    public boolean devolverLibro(String isbnLibro, String idUsuario) {
        Libro libro = buscarLibroPorIsbn(isbnLibro);
        Usuario usuario = buscarUsuario(idUsuario);
        
        if (libro == null || usuario == null) {
            System.out.println("Libro o usuario no encontrado");
            return false;
        }
        
        if (usuario.devolverLibro(libro)) {
            System.out.println("Libro devuelto exitosamente");
            System.out.println("Libro: " + libro.getTitulo() + " devuelto por: " + usuario.getNombre());
            return true;
        } else {
            System.out.println("El usuario no tiene ese libro prestado");
            return false;
        }
    }
    
    // Métodos para mostrar información
    public void mostrarLibrosDisponibles() {
        System.out.println("\n=== LIBROS DISPONIBLES ===");
        boolean hayDisponibles = false;
        for (Libro libro : libros) {
            if (libro.isDisponible()) {
                System.out.println("- " + libro.getTitulo() + " por " + libro.getAutor() + " (ISBN: " + libro.getIsbn() + ")");
                hayDisponibles = true;
            }
        }
        if (!hayDisponibles) {
            System.out.println("No hay libros disponibles");
        }
    }
    
    public void mostrarTodosLosLibros() {
        System.out.println("\n=== TODOS LOS LIBROS ===");
        if (libros.isEmpty()) {
            System.out.println("No hay libros en la biblioteca");
        } else {
            for (Libro libro : libros) {
                System.out.println("- " + libro);
            }
        }
    }
    
    public void mostrarUsuarios() {
        System.out.println("\n=== USUARIOS REGISTRADOS ===");
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println("- " + usuario);
            }
        }
    }
    
    @Override
    public String toString() {
        return "Biblioteca{" +
                "nombre='" + nombre + '\'' +
                ", libros=" + libros.size() +
                ", usuarios=" + usuarios.size() +
                '}';
    }
}