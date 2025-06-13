package com.mycompany.semana2;

class Vehiculo {
    protected String modelo;
    protected int velocidad;
    protected int potencia;
    protected int blindaje;
    protected int resistencia;

    public Vehiculo(String modelo, int velocidad, int potencia, int blindaje, int resistencia) {
        this.modelo = modelo;
        this.velocidad = velocidad;
        this.potencia = potencia;
        this.blindaje = blindaje;
        this.resistencia = resistencia;
    }

    public void mostrarAtributos() {
        System.out.println(modelo + ":");
        System.out.println("·Velocidad: " + velocidad);
        System.out.println("·Potencia: " + potencia);
        System.out.println("·Blindaje: " + blindaje);
        System.out.println("·Resistencia: " + resistencia);
    }

    public void mejorarVehiculo(int velocidad, int potencia, int blindaje) {
        this.velocidad += velocidad;
        this.potencia += potencia;
        this.blindaje += blindaje;
    }

    public boolean sigueEnCarrera() {
        return resistencia > 0;
    }

    public void destruir() {
        resistencia = 0;
        System.out.println(modelo + " ha sido destruido");
    }

    public int calcularDaño(Vehiculo enemigo) {
        int daño = potencia - enemigo.blindaje;
        return Math.max(daño, 0);
    }

    public void atacar(Vehiculo enemigo) {
        int daño = calcularDaño(enemigo);
        enemigo.resistencia = Math.max(enemigo.resistencia - daño, 0);
        System.out.println(modelo + " causó " + daño + " de daño a " + enemigo.modelo);
        if (!enemigo.sigueEnCarrera()) {
            enemigo.destruir();
        } else {
            System.out.println("Resistencia de " + enemigo.modelo + ": " + enemigo.resistencia);
        }
    }
}

class AutoRapido extends Vehiculo {
    private int turbo;

    public AutoRapido(String modelo, int velocidad, int potencia, int blindaje, int resistencia, int turbo) {
        super(modelo, velocidad, potencia, blindaje, resistencia);
        this.turbo = turbo;
    }

    public void activarTurbo() {
        turbo += 2;
        System.out.println(modelo + " ha activado turbo. Nivel actual: " + turbo);
    }

    @Override
    public void mostrarAtributos() {
        super.mostrarAtributos();
        System.out.println("·Turbo: " + turbo);
    }

    @Override
    public int calcularDaño(Vehiculo enemigo) {
        int daño = (potencia + turbo) - enemigo.blindaje;
        return Math.max(daño, 0);
    }
}

class TanquePesado extends Vehiculo {
    private int canon;

    public TanquePesado(String modelo, int velocidad, int potencia, int blindaje, int resistencia, int canon) {
        super(modelo, velocidad, potencia, blindaje, resistencia);
        this.canon = canon;
    }

    @Override
    public void mostrarAtributos() {
        super.mostrarAtributos();
        System.out.println("·Cañón: " + canon);
    }

    @Override
    public int calcularDaño(Vehiculo enemigo) {
        int daño = potencia * canon - enemigo.blindaje;
        return Math.max(daño, 0);
    }
}

public class Semana2 {
    public static void main(String[] args) {
        AutoRapido auto = new AutoRapido("VelozX", 100, 20, 5, 80, 3);
        TanquePesado tanque = new TanquePesado("DestructorZ", 40, 25, 10, 120, 2);

        auto.mostrarAtributos();
        tanque.mostrarAtributos();

        competencia(auto, tanque);
    }

    public static void competencia(Vehiculo v1, Vehiculo v2) {
        int turno = 1;
        while (v1.sigueEnCarrera() && v2.sigueEnCarrera()) {
            System.out.println("\nTurno " + turno);
            System.out.println(">>> Ataque de " + v1.modelo + ":");
            v1.atacar(v2);
            if (!v2.sigueEnCarrera()) break;

            System.out.println(">>> Ataque de " + v2.modelo + ":");
            v2.atacar(v1);
            turno++;
        }

        if (v1.sigueEnCarrera()) {
            System.out.println("\n" + v1.modelo + " ha ganado la competencia");
        } else if (v2.sigueEnCarrera()) {
            System.out.println("\n" + v2.modelo + " ha ganado la competencia");
        } else {
            System.out.println("\nAmbos vehículos han sido destruidos. Empate.");
}
}
}