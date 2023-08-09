package com.example.refugio.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Reserva {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idReserva;
    private int cantPersonas;
    private float montoTotal;
    private LocalDate fechaReserva;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn (name = "IDUsuario")
    private Usuario usuario;

    @OneToOne (mappedBy = "reserva")
    @JoinColumn (name = "IDReserva")
    private Calificacion calificacion;

    @OneToMany (mappedBy = "reserva")
    private List<ReservaEstado> reservasEstado;

    @ManyToOne
    @JoinColumn (name = "IDCabaña")
    private Cabaña cabaña;
}
