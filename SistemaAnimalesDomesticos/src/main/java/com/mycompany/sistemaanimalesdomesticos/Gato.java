package com.mycompany.sistemaanimalesdomesticos;

// Segunda clase creada: Gato
public class Gato {
    // Atributos privados para demostrar encapsulamiento
    private String nombre;
    private String raza;
    private int edad;
    private String color;
    private boolean esInterior;
    
    // Constructor por defecto
    // Crea un gato con valores predeterminados
    public Gato() {
        this.nombre = "Sin nombre";
        this.raza = "Común";
        this.edad = 1;
        this.color = "Negro";
        this.esInterior = true;
    }
    
    // Constructor parametrizado completo
    // Permite crear un gato con todos los datos específicos
    public Gato(String nombre, String raza, int edad, String color, boolean esInterior) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.color = color;
        this.esInterior = esInterior;
    }
    
    // Constructor sobrecargado - solo nombre y raza
    // Útil para registro básico de gatos
    public Gato(String nombre, String raza) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = 1;
        this.color = "Negro";
        this.esInterior = true;
    }
    
    // Constructor sobrecargado - nombre, raza y edad
    // Para casos donde se conoce información básica del gato
    public Gato(String nombre, String raza, int edad) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.color = "Negro";
        this.esInterior = true;
    }
    
    // Métodos getter y setter para encapsulamiento
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getRaza() {
        return raza;
    }
    
    public void setRaza(String raza) {
        this.raza = raza;
    }
    
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public boolean isEsInterior() {
        return esInterior;
    }
    
    public void setEsInterior(boolean esInterior) {
        this.esInterior = esInterior;
    }
    
    // Método para mostrar información del gato
    public void mostrarInfo() {
        String tipoGato = esInterior ? "Interior" : "Exterior";
        System.out.println("🐱 Gato - Nombre: " + nombre + ", Raza: " + raza + 
                          ", Edad: " + edad + " años, Color: " + color + ", Tipo: " + tipoGato);
    }
    
    // Método específico para gatos
    public void maullar() {
        System.out.println(nombre + " dice: ¡Miau! ¡Miau!");
    }
}