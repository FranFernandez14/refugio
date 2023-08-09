package com.example.refugio.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class ReservaEstado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaInicioRE;
    private LocalDate fechafinRE;

    @ManyToOne
    @JoinColumn (name = "IDReserva")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn (name = "IDER")
    private EstadoReserva estadoReserva;
}
