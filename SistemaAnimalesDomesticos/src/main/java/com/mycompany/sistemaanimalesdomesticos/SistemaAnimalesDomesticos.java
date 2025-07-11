package com.mycompany.sistemaanimalesdomesticos;

// Clase principal de Sistema de Animales Domesticos
public class SistemaAnimalesDomesticos {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE ANIMALES DOMÉSTICOS ===\n");
        
        // Demostrando diferentes constructores de la clase Perro
        System.out.println("--- REGISTRO DE PERROS ---");
        
        // Usando constructor por defecto
        Perro perro1 = new Perro();
        System.out.println("Perro 1 (constructor por defecto):");
        perro1.mostrarInfo();
        perro1.ladrar();
        System.out.println();
        
        // Usando constructor parametrizado completo
        Perro perro2 = new Perro("Max", "Golden Retriever", 3, 25.5, "Dorado");
        System.out.println("Perro 2 (constructor parametrizado completo):");
        perro2.mostrarInfo();
        perro2.ladrar();
        System.out.println();
        
        // Usando constructor sobrecargado (nombre y raza)
        Perro perro3 = new Perro("Buddy", "Labrador");
        System.out.println("Perro 3 (constructor sobrecargado - nombre y raza):");
        perro3.mostrarInfo();
        perro3.ladrar();
        System.out.println();
        
        // Usando constructor sobrecargado (nombre, raza y edad)
        Perro perro4 = new Perro("Luna", "Husky", 2);
        System.out.println("Perro 4 (constructor sobrecargado - nombre, raza y edad):");
        perro4.mostrarInfo();
        perro4.ladrar();
        System.out.println();
        
        // Demostrando diferentes constructores de la clase Gato
        System.out.println("--- REGISTRO DE GATOS ---");
        
        // Usando constructor por defecto
        Gato gato1 = new Gato();
        System.out.println("Gato 1 (constructor por defecto):");
        gato1.mostrarInfo();
        gato1.maullar();
        System.out.println();
        
        // Usando constructor parametrizado completo
        Gato gato2 = new Gato("Whiskers", "Siamés", 4, "Blanco", false);
        System.out.println("Gato 2 (constructor parametrizado completo):");
        gato2.mostrarInfo();
        gato2.maullar();
        System.out.println();
        
        // Usando constructor sobrecargado (nombre y raza)
        Gato gato3 = new Gato("Mittens", "Persa");
        System.out.println("Gato 3 (constructor sobrecargado - nombre y raza):");
        gato3.mostrarInfo();
        gato3.maullar();
        System.out.println();
        
        // Usando constructor sobrecargado (nombre, raza y edad)
        Gato gato4 = new Gato("Shadow", "Maine Coon", 5);
        System.out.println("Gato 4 (constructor sobrecargado - nombre, raza y edad):");
        gato4.mostrarInfo();
        gato4.maullar();
        System.out.println();
        
        // Demostrando modificación de datos usando setters
        System.out.println("--- ACTUALIZACIÓN DE DATOS ---");
        
        // Modificando el perro creado con constructor por defecto
        perro1.setNombre("Rocky");
        perro1.setRaza("Bulldog");
        perro1.setEdad(4);
        perro1.setPeso(18.0);
        perro1.setColor("Blanco");
        
        System.out.println("Perro 1 después de actualizar datos:");
        perro1.mostrarInfo();
        perro1.ladrar();
        System.out.println();
        
        // Modificando el gato creado con constructor por defecto
        gato1.setNombre("Garfield");
        gato1.setRaza("Naranja Tabby");
        gato1.setEdad(3);
        gato1.setColor("Naranja");
        gato1.setEsInterior(true);
        
        System.out.println("Gato 1 después de actualizar datos:");
        gato1.mostrarInfo();
        gato1.maullar();
        System.out.println();
        
        // Demostrando interacción entre animales
        System.out.println("--- INTERACCIÓN ENTRE ANIMALES ---");
        System.out.println("Los animales están jugando en el parque:");
        perro2.ladrar();
        gato2.maullar();
        perro3.ladrar();
        gato3.maullar();
        
        System.out.println("\n=== RESUMEN DEL SISTEMA ===");
        System.out.println("Total de perros registrados: 4");
        System.out.println("Total de gatos registrados: 4");
        System.out.println("Sistema funcionando correctamente ✅");
    }
}