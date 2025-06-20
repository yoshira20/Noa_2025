package com.mycompany.ejemplosmundoreal_poo; 

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Clase Mesa
class Mesa {
    private int numero;
    private int capacidad;
    private boolean estaOcupada; // Correcto: estaOcupada

    public Mesa(int numero, int capacidad) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.estaOcupada = false;
    }

    public int getNumero() {
        return numero;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean estaOcupada() {
        return estaOcupada;
    }

    public void ocupar() {
        this.estaOcupada = true;
    }

    public void liberar() {
        this.estaOcupada = false; // ¡Corrección aquí! De estaOestaOcupada a estaOcupada
    }

    @Override
    public String toString() {
        return "Mesa [Número: " + numero + ", Capacidad: " + capacidad + ", Ocupada: " + (estaOcupada ? "Sí" : "No") + "]";
    }
}

// Clase Reserva
class Reserva {
    private static int contadorId = 0;
    private int id;
    private String nombreCliente;
    private int numeroPersonas;
    private LocalDateTime fechaHora;
    private Mesa mesaAsignada;

    public Reserva(String nombreCliente, int numeroPersonas, LocalDateTime fechaHora) {
        this.id = ++contadorId;
        this.nombreCliente = nombreCliente;
        this.numeroPersonas = numeroPersonas;
        this.fechaHora = fechaHora;
        this.mesaAsignada = null;
    }

    public int getId() {
        return id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Mesa getMesaAsignada() {
        return mesaAsignada;
    }

    public void setMesaAsignada(Mesa mesaAsignada) {
        this.mesaAsignada = mesaAsignada;
        if (mesaAsignada != null) {
            mesaAsignada.ocupar();
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String fechaHoraFormateada = fechaHora.format(formatter);
        String infoMesa = (mesaAsignada != null) ? "Mesa #" + mesaAsignada.getNumero() : "No asignada";
        return "Reserva [ID: " + id + ", Cliente: " + nombreCliente +
               ", Personas: " + numeroPersonas + ", Fecha/Hora: " + fechaHoraFormateada +
               ", " + infoMesa + "]";
    }
}

// Clase Restaurante
class Restaurante {
    private String nombre;
    private List<Mesa> mesas;
    private List<Reserva> reservas;

    public Restaurante(String nombre) {
        this.nombre = nombre;
        this.mesas = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarMesa(Mesa mesa) {
        this.mesas.add(mesa);
    }

    public List<Mesa> getMesasDisponibles(int numeroPersonas) {
        List<Mesa> disponibles = new ArrayList<>();
        for (Mesa mesa : mesas) {
            if (!mesa.estaOcupada() && mesa.getCapacidad() >= numeroPersonas) {
                disponibles.add(mesa);
            }
        }
        return disponibles;
    }

    public Optional<Reserva> hacerReserva(String nombreCliente, int numeroPersonas, LocalDateTime fechaHora) {
        List<Mesa> mesasDisponibles = getMesasDisponibles(numeroPersonas);

        if (!mesasDisponibles.isEmpty()) {
            Mesa mesaParaAsignar = mesasDisponibles.get(0);
            Reserva nuevaReserva = new Reserva(nombreCliente, numeroPersonas, fechaHora);
            nuevaReserva.setMesaAsignada(mesaParaAsignar);
            reservas.add(nuevaReserva);
            System.out.println("Reserva exitosa para " + nombreCliente + " en la " + mesaParaAsignar.toString());
            return Optional.of(nuevaReserva);
        } else {
            System.out.println("Lo sentimos, no hay mesas disponibles para " + numeroPersonas + " personas en este momento.");
            return Optional.empty();
        }
    }

    public boolean cancelarReserva(int idReserva) {
        Optional<Reserva> reservaACancelar = reservas.stream()
                                                    .filter(r -> r.getId() == idReserva)
                                                    .findFirst();

        if (reservaACancelar.isPresent()) {
            Reserva reserva = reservaACancelar.get();
            if (reserva.getMesaAsignada() != null) {
                reserva.getMesaAsignada().liberar();
            }
            reservas.remove(reserva);
            System.out.println("Reserva con ID " + idReserva + " cancelada exitosamente.");
            return true;
        } else {
            System.out.println("No se encontró ninguna reserva con ID " + idReserva + ".");
            return false;
        }
    }

    public void mostrarTodasLasReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas en " + nombre + ".");
            return;
        }
        System.out.println("\n--- Reservas en " + nombre + " ---");
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
        System.out.println("--------------------------------\n");
    }

    public void mostrarEstadoMesas() {
        System.out.println("\n--- Estado de las Mesas en " + nombre + " ---");
        for (Mesa mesa : mesas) {
            System.out.println(mesa);
        }
        System.out.println("----------------------------------\n");
    }
}

// Clase Principal (Main)
public class SistemaReservasRestaurante {
    public static void main(String[] args) {
        // 1. Crear un restaurante
        Restaurante miRestaurante = new Restaurante("El Sabor Programador");

        // 2. Agregar mesas al restaurante
        miRestaurante.agregarMesa(new Mesa(1, 2));
        miRestaurante.agregarMesa(new Mesa(2, 4));
        miRestaurante.agregarMesa(new Mesa(3, 2));
        miRestaurante.agregarMesa(new Mesa(4, 6));

        System.out.println("Mesas iniciales del restaurante:");
        miRestaurante.mostrarEstadoMesas();

        // 3. Realizar algunas reservas
        LocalDateTime fechaHora1 = LocalDateTime.of(2025, 7, 20, 19, 0);
        miRestaurante.hacerReserva("Ana López", 3, fechaHora1);

        LocalDateTime fechaHora2 = LocalDateTime.of(2025, 7, 20, 20, 30);
        miRestaurante.hacerReserva("Carlos Gómez", 2, fechaHora2);

        LocalDateTime fechaHora3 = LocalDateTime.of(2025, 7, 21, 18, 0);
        miRestaurante.hacerReserva("María Fernández", 5, fechaHora3);

        // Intentar una reserva para la que no hay mesas disponibles con suficiente capacidad
        LocalDateTime fechaHora4 = LocalDateTime.of(2025, 7, 20, 21, 0);
        miRestaurante.hacerReserva("Pedro Pérez", 7, fechaHora4);

        // Intentar una reserva para la que no hay mesas disponibles (todas ocupadas para ese tamaño)
        LocalDateTime fechaHora5 = LocalDateTime.of(2025, 7, 20, 19, 0);
        miRestaurante.hacerReserva("Laura García", 3, fechaHora5);

        // 4. Mostrar todas las reservas actuales
        miRestaurante.mostrarTodasLasReservas();

        // 5. Mostrar el estado actualizado de las mesas
        miRestaurante.mostrarEstadoMesas();

        // 6. Cancelar una reserva
        miRestaurante.cancelarReserva(2);

        // 7. Mostrar reservas después de la cancelación
        miRestaurante.mostrarTodasLasReservas();

        // 8. Mostrar estado de mesas después de la cancelación
        miRestaurante.mostrarEstadoMesas();

        // 9. Intentar una nueva reserva para una mesa que ahora está libre
        LocalDateTime fechaHora6 = LocalDateTime.of(2025, 7, 20, 20, 30);
        miRestaurante.hacerReserva("Roberto Solís", 2, fechaHora6);

        miRestaurante.mostrarTodasLasReservas();
        miRestaurante.mostrarEstadoMesas();
    }
}