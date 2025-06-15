package com.mycompany.semana3programaciontradicionalclimas ; 
import java.util.Scanner;

public class Semana3programaciontradicionalclimas {

    public static void main(String[] args) {
        double[] temperaturas = ingresarTemperaturas();
        double promedio = calcularPromedio(temperaturas);
        System.out.println("El promedio semanal de temperaturas es: " + promedio + "°C");
    }

    public static double[] ingresarTemperaturas() {
        Scanner scanner = new Scanner(System.in);
        double[] temps = new double[7];
        for (int i = 0; i < 7; i++) {
            System.out.print("Ingrese la temperatura del día " + (i + 1) + ": ");
            temps[i] = scanner.nextDouble();
        }
        return temps;
    }

    public static double calcularPromedio(double[] temperaturas) {
        double suma = 0;
        for (double temp : temperaturas) {
            suma += temp;
        }
        return suma / temperaturas.length;
    }
}
