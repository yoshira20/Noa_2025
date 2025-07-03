// Car.java (Clase Derivada)
package com.example.vehiclesystem; // Debe estar en el mismo paquete que Vehicle

/**
 * La clase Car extiende la clase Vehicle, demostrando la herencia.
 * Añade atributos específicos de un coche como numberOfDoors y un comportamiento
 * específico de coche para mostrar información.
 */
public class Car extends Vehicle {
    private int numberOfDoors;

    /**
     * Constructor de la clase Car.
     * Llama al constructor de la superclase (Vehicle) usando super().
     * @param brand La marca del coche.
     * @param model El modelo del coche.
     * @param year El año de fabricación del coche.
     * @param numberOfDoors El número de puertas que tiene el coche.
     */
    public Car(String brand, String model, int year, int numberOfDoors) {
        super(brand, model, year); // Llama al constructor de la clase base (Vehicle)
        this.numberOfDoors = numberOfDoors;
    }

    /**
     * Obtiene el número de puertas del coche.
     * @return El número de puertas.
     */
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    /**
     * Establece el número de puertas del coche.
     * @param numberOfDoors El nuevo número de puertas.
     */
    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    /**
     * Sobrescribe el método displayInfo de la clase Vehicle, demostrando polimorfismo.
     * Proporciona información específica del coche además de la información general del vehículo.
     */
    @Override // Anotación que indica que este método sobrescribe un método en la superclase
    public void displayInfo() {
        super.displayInfo(); // Llama al método displayInfo de la clase base (Vehicle)
        System.out.println("Número de Puertas: " + numberOfDoors);
        System.out.println("Tipo: Coche");
    }

    /**
     * Simula el coche tocando la bocina.
     * Este es un comportamiento específico del coche.
     */
    public void honk() {
        System.out.println(getBrand() + " " + getModel() + " está tocando la bocina: ¡Bip bip!");
    }
}