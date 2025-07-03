// Vehicle.java (Clase Base)
package com.example.vehiclesystem; 

/**
 * La clase Vehicle sirve como clase base para todo tipo de vehículos.
 * Demuestra el encapsulamiento manteniendo los atributos privados y
 * proporcionando métodos públicos para su acceso.
 */
public class Vehicle {
    // Atributos encapsulados: privados para restringir el acceso directo
    private String brand;
    private String model;
    private int year;

    /**
     * Constructor de la clase Vehicle.
     * @param brand La marca del vehículo.
     * @param model El modelo del vehículo.
     * @param year El año de fabricación del vehículo.
     */
    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    // --- Encapsulamiento: Métodos Getter Públicos ---
    /**
     * Obtiene la marca del vehículo.
     * @return La marca del vehículo.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Obtiene el modelo del vehículo.
     * @return El modelo del vehículo.
     */
    public String getModel() {
        return model;
    }

    /**
     * Obtiene el año de fabricación del vehículo.
     * @return El año de fabricación del vehículo.
     */
    public int getYear() {
        return year;
    }

    // --- Encapsulamiento: Métodos Setter Públicos (opcional, depende de las necesidades de mutabilidad) ---
    /**
     * Establece la marca del vehículo.
     * @param brand La nueva marca para el vehículo.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Establece el modelo del vehículo.
     * @param model El nuevo modelo para el vehículo.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Establece el año de fabricación del vehículo.
     * @param year El nuevo año de fabricación para el vehículo.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Muestra información general sobre el vehículo.
     * Este método será sobrescrito en las clases derivadas para mostrar polimorfismo.
     */
    public void displayInfo() {
        System.out.println("--- Información del Vehículo ---");
        System.out.println("Marca: " + brand);
        System.out.println("Modelo: " + model);
        System.out.println("Año: " + year);
    }

    /**
     * Simula el arranque del vehículo.
     */
    public void start() {
        System.out.println(brand + " " + model + " está arrancando...");
    }

    /**
     * Simula la detención del vehículo.
     */
    public void stop() {
        System.out.println(brand + " " + model + " se está deteniendo.");
    }
}