package buggyactividad;

import java.util.*;

public class BuggyActividad {
    public static void main(String[] args) {
        System.out.println("=== PROGRAMA DE COLECCIONES CORREGIDO ===\n");
        
        // LISTA de nombres
        List<String> nombres = new ArrayList<>();
        nombres.add("Ana");
        nombres.add("Luis");
        nombres.add("Ana");
        System.out.println("Lista creada: " + nombres);

        // ERROR CORREGIDO: Validación de índice antes de acceder
        int indice = 3;
        System.out.println("\n--- CORRECCIÓN 1: Validación de índices ---");
        if (indice < nombres.size()) {
            System.out.println("Elemento en posición " + indice + ": " + nombres.get(indice));
        } else {
            System.out.println("✓ Error controlado: Índice " + indice + " fuera de rango. La lista tiene " + nombres.size() + " elementos.");
        }

        // ERREGIDO ERROR: Usar equals() para comparar cadenas
        System.out.println("\n--- CORRECCIÓN 2: Comparación de Strings ---");
        String buscado = new String("Ana");
        if (buscado.equals("Ana")) {
            System.out.println("✓ Encontrado correctamente usando equals()");
        }
        // Demostración del error anterior
        if (buscado == "Ana") {
            System.out.println("Encontrado con ==");
        } else {
            System.out.println("✗ Con == no funciona (compara referencias)");
        }

        // MAPA de teléfonos
        System.out.println("\n--- CORRECCIÓN 3 y 4: Manejo de MapAS ---");
        Map<String, String> telefonos = new HashMap<>();
        telefonos.put("Ana", "0991111111");
        telefonos.put("Luis", "0992222222");
        System.out.println("Mapa inicial: " + telefonos);
        
        // EORREGIDO ERROR: Avisar antes de sobrescribir
        String claveExistente = "Ana";
        if (telefonos.containsKey(claveExistente)) {
            System.out.println("⚠ Advertencia: Sobrescribiendo teléfono de " + claveExistente + 
                             " (anterior: " + telefonos.get(claveExistente) + ")");
        }
        telefonos.put("Ana", "0993333333");
        System.out.println("Mapa después de sobrescribir: " + telefonos);

        // CORREGIDO ERROR 4: Validar existencia de clave antes de acceder
        String claveBuscada = "Bea";
        String telefonoBea = telefonos.get(claveBuscada);
        if (telefonoBea != null) {
            System.out.println(claveBuscada + ": " + telefonoBea);
        } else {
            System.out.println("✓ Error controlado: No se encontró teléfono para " + claveBuscada);
        }

        // ERREGIDO ERROR: SET con duplicados lógicos controlados
        System.out.println("\n--- CORRECCIÓN 5: HashSet con equals() y hashCode() ---");
        Set<Alumno> inscritos = new HashSet<>();
        inscritos.add(new Alumno(1, "Ana"));
        inscritos.add(new Alumno(2, "Luis"));
        System.out.println("Set después de agregar 2 alumnos: " + inscritos);
        
        // Ahora detecta duplicado lógico gracias a equals() y hashCode()
        boolean agregado = inscritos.add(new Alumno(1, "Ana"));
        if (!agregado) {
            System.out.println("✓ Duplicado detectado correctamente: No se agregó el alumno repetido");
        }

        System.out.println("Tamaño final del Set: " + inscritos.size());
        System.out.println("Alumnos inscritos: " + inscritos);
        
        System.out.println("\n=== PROGRAMA EJECUTADO EXITOSAMENTE ===");
    }
}

// CORREGIDO: Clase Alumno con equals() y hashCode() implementados
class Alumno {
    int id;
    String nombre;
    
    Alumno(int id, String nombre) { 
        this.id = id; 
        this.nombre = nombre; 
    }
    
    // CORREGIDO: Implementar equals() para comparación lógica
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Misma referencia
        if (obj == null || getClass() != obj.getClass()) return false; // Null o clase diferente
        Alumno alumno = (Alumno) obj;
        return id == alumno.id && Objects.equals(nombre, alumno.nombre); // Comparar campos
    }
    
    // CORREGIDO: Implementar hashCode() para funcionamiento correcto en HashSet
    @Override
    public int hashCode() {
        return Objects.hash(id, nombre); // Hash basado en los campos importantes
    }
    
    @Override
    public String toString() {
        return "Alumno{id=" + id + ", nombre='" + nombre + "'}";
    }
}