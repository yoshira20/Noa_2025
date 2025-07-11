package com.mycompany.sistemaanimalesdomesticos;

// Primera clase creada: Perro
public class Perro {
    // Atributos privados para demostrar encapsulamiento
    private String nombre;
    private String raza;
    private int edad;
    private double peso;
    private String color;
    
    // Constructor por defecto
    // Crea un perro con valores predeterminados
    public Perro() {
        this.nombre = "Sin nombre";
        this.raza = "Mestizo";
        this.edad = 1;
        this.peso = 5.0;
        this.color = "Café";
    }
    
    // Constructor parametrizado completo
    // Permite crear un perro con todos los datos específicos
    public Perro(String nombre, String raza, int edad, double peso, String color) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.peso = peso;
        this.color = color;
    }
    
    // Constructor sobrecargado - solo nombre y raza
    // Útil para registro básico de perros
    public Perro(String nombre, String raza) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = 1;
        this.peso = 5.0;
        this.color = "Café";
    }
    
    // Constructor sobrecargado - nombre, raza y edad
    // Para casos donde se conoce información básica del perro
    public Perro(String nombre, String raza, int edad) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.peso = 5.0;
        this.color = "Café";
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
    
    public double getPeso() {
        return peso;
    }
    
    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    // Método para mostrar información del perro
    public void mostrarInfo() {
        System.out.println("🐕 Perro - Nombre: " + nombre + ", Raza: " + raza + 
                          ", Edad: " + edad + " años, Peso: " + peso + " kg, Color: " + color);
    }
    
    // Método específico para perros
    public void ladrar() {
        System.out.println(nombre + " dice: ¡Guau! ¡Guau!");
    }
}