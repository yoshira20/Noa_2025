// Main.java
package com.example.vehiclesystem; // Debe estar en el mismo paquete que Vehicle y Car

/**
 
 * Demuestra la creación de objetos, el uso de métodos y, específicamente,
 * resalta la herencia, el encapsulamiento y el polimorfismo.
 */
public class Main {
    public static void main(String[] args) {
        // --- Demostración de Encapsulamiento ---
        // Creando una instancia de Vehicle
        System.out.println("--- Demostrando Encapsulamiento ---");
        Vehicle myVehicle = new Vehicle("Generic Motors", "Cruiser", 2020);
        // Accediendo a los atributos usando métodos getter públicos (encapsulamiento)
        System.out.println("Marca del Vehículo (usando getter): " + myVehicle.getBrand());
        myVehicle.setYear(2021); // Modificando un atributo usando un método setter público
        System.out.println("Año del Vehículo (después del setter): " + myVehicle.getYear());
        System.out.println("----------------------------------\n");


        // --- Demostración de Herencia ---
        System.out.println("--- Demostrando Herencia ---");
        // Creando una instancia de Car (clase derivada)
        // Car hereda brand, model, year, start(), stop() de Vehicle
        Car myCar = new Car("Toyota", "Camry", 2023, 4);

        // Usando métodos heredados de la clase Vehicle
        System.out.println("Marca del Coche (heredada): " + myCar.getBrand());
        myCar.start(); // Método heredado
        myCar.stop();  // Método heredado
        System.out.println("----------------------------------\n");


        // --- Demostración de Polimorfismo ---
        System.out.println("--- Demostrando Polimorfismo ---");
        // Polimorfismo a través de la sobrescritura de métodos:
        // Llamando a displayInfo() en myVehicle (tipo Vehicle)
        myVehicle.displayInfo(); // Llama a displayInfo() de Vehicle

        System.out.println(); // Para una mejor legibilidad

        // Llamando a displayInfo() en myCar (tipo Car)
        // Esto llama al método displayInfo() sobrescrito de Car
        myCar.displayInfo(); // Llama a displayInfo() de Car (¡Polimorfismo en acción!)

        System.out.println();

        // Polimorfismo a través de una referencia de supertipo común:
        // Una referencia de Vehicle puede apuntar a un objeto Car porque Car ES-UN Vehicle.
        Vehicle anotherVehicle = new Car("Honda", "Civic", 2024, 5);
        System.out.println("Llamando a displayInfo en una referencia de Vehicle que apunta a un objeto Car:");
        // Aunque 'anotherVehicle' es de tipo Vehicle, el método real llamado
        // es displayInfo() de Car porque el objeto al que apunta es un Car.
        anotherVehicle.displayInfo();
        // Nota: No puedes llamar directamente a métodos específicos de coche como honk() en 'anotherVehicle'
        // sin hacer un casting, porque el tipo de referencia es Vehicle.
        // ((Car) anotherVehicle).honk(); // Esto funcionaría si lo casteas de nuevo a Car

        System.out.println("----------------------------------\n");

        // --- Usando un método específico de Car ---
        myCar.honk();
    }
}