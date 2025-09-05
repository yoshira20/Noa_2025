import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private String id;
    private String email;
    private List<Libro> librosPrestados;
    private int limitePrestamos;
    
    // Constructor
    public Usuario(String nombre, String id, String email) {
        this.nombre = nombre;
        this.id = id;
        this.email = email;
        this.librosPrestados = new ArrayList<>();
        this.limitePrestamos = 3; // Máximo 3 libros por usuario
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }
    
    public String getId() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public List<Libro> getLibrosPrestados() {
        return librosPrestados;
    }
    
    public int getLimitePrestamos() {
        return limitePrestamos;
    }
    
    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setLimitePrestamos(int limitePrestamos) {
        this.limitePrestamos = limitePrestamos;
    }
    
    // Método para verificar si puede tomar más libros prestados
    public boolean puedeTomarPrestado() {
        return librosPrestados.size() < limitePrestamos;
    }
    
    // Método para agregar un libro prestado
    public boolean tomarLibroPrestado(Libro libro) {
        if (puedeTomarPrestado() && libro.isDisponible()) {
            if (libro.prestar()) {
                librosPrestados.add(libro);
                return true;
            }
        }
        return false;
    }
    
    // Método para devolver un libro
    public boolean devolverLibro(Libro libro) {
        if (librosPrestados.contains(libro)) {
            libro.devolver();
            librosPrestados.remove(libro);
            return true;
        }
        return false;
    }
    
    // Método para mostrar libros prestados
    public void mostrarLibrosPrestados() {
        System.out.println("Libros prestados a " + nombre + ":");
        if (librosPrestados.isEmpty()) {
            System.out.println("  No tiene libros prestados");
        } else {
            for (int i = 0; i < librosPrestados.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + librosPrestados.get(i).getTitulo());
            }
        }
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", libros prestados=" + librosPrestados.size() +
                '}';
    }
}