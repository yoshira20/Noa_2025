package com.mycompany.ClimaPOO.java ;
import java.util.Scanner;

class DiaClima {
    private double temperatura;

    public DiaClima(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }
}

class SemanaClima {
    private final DiaClima[] dias;

    public SemanaClima() {
        dias = new DiaClima[7];
    }

    public void ingresarTemperaturas() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 7; i++) {
            System.out.print("Ingrese la temperatura del día " + (i + 1) + ": ");
            double temp = scanner.nextDouble();
            dias[i] = new DiaClima(temp);
        }
    }

    public double calcularPromedioSemanal() {
        double suma = 0;
        for (DiaClima dia : dias) {
            suma += dia.getTemperatura();
        }
        return suma / dias.length;
    }
}

public class ClimaPOO {
    public static void main(String[] args) {
        SemanaClima semana = new SemanaClima();
        semana.ingresarTemperaturas();
        double promedio = semana.calcularPromedioSemanal();
        System.out.println("El promedio semanal de temperaturas es: " + promedio + "°C");
    }
}
