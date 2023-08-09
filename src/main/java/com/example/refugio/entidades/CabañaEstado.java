package com.example.refugio.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class CabañaEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fechaInicioCE;
    private LocalDate fechaFinCE;

    @ManyToOne
    @JoinColumn(name = "IDCabaña")
    private Cabaña cabaña;

    @ManyToOne
    @JoinColumn (name = "IDEC")
    private EstadoCabaña estadoCabaña;
}
