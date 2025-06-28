/**
 * Este programa calcula el Índice de Masa Corporal (IMC) de una persona.
 * Solicita el peso en kilogramos y la altura en metros, luego calcula y muestra el IMC,
 * así como una categorización básica del resultado.
 **/
package com.mycompany.programacionsemana5; // Agrega esta línea para declarar el paquete

public class ProgramacionSemana5 {

    public static void main(String[] args) {
        // Declaración de variables utilizando diferentes tipos de datos e identificadores en snake_case
        double peso_kg = 0.0;           // Peso de la persona en kilogramos (tipo double para precisión)
        double altura_metros = 0.0;     // Altura de la persona en metros (tipo double para precisión)
        double imc_calculado = 0.0;     // Almacena el resultado del cálculo del IMC (tipo double)
        String categoria_imc = "";      // Cadena para almacenar la categoría del IMC (tipo String)
        boolean datos_validos = true;   // Booleano para verificar si los datos ingresados son válidos

        // --- Simulación de entrada de datos (en un entorno real, usarías Scanner para la entrada del usuario) ---
        // Para este ejemplo, asignaremos valores directamente.
        // Puedes cambiar estos valores para probar diferentes escenarios.
        peso_kg = 70.5;     // Ejemplo: 70.5 kilogramos
        altura_metros = 1.75; // Ejemplo: 1.75 metros

        // Verificación básica de datos (para asegurar que no haya valores negativos o cero)
        if (peso_kg <= 0 || altura_metros <= 0) {
            System.out.println("Error: El peso y la altura deben ser valores positivos.");
            datos_validos = false; // Los datos no son válidos
        }

        // Si los datos son válidos, procedemos con el cálculo
        if (datos_validos) {
            // Cálculo del IMC: peso / (altura * altura)
            imc_calculado = peso_kg / (altura_metros * altura_metros);

            // Determinación de la categoría del IMC
            if (imc_calculado < 18.5) {
                categoria_imc = "Bajo peso";
            } else if (imc_calculado >= 18.5 && imc_calculado < 24.9) {
                categoria_imc = "Peso normal";
            } else if (imc_calculado >= 25.0 && imc_calculado < 29.9) {
                categoria_imc = "Sobrepeso";
            } else {
                categoria_imc = "Obesidad";
            }

            // Impresión de los resultados
            System.out.println("--- Resultado del Cálculo de IMC ---");
            System.out.println("Peso ingresado: " + peso_kg + " kg");
            System.out.println("Altura ingresada: " + altura_metros + " m");
            System.out.printf("Su IMC es: %.2f%n", imc_calculado); // Formateamos a 2 decimales
            System.out.println("Categoría de IMC: " + categoria_imc);
            System.out.println("------------------------------------");
        }
    }
}