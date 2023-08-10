package com.example.refugio.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Cabaña {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long IDCabaña;
    private int tamaño;
    private LocalDate fechaHoraBajaCabaña;

    @ManyToOne
    @JoinColumn (name = "IDTipocabaña")
    private TipoCabaña tipoCabaña;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cabaña")
    private List<CabañaEstado> estados = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cabaña")
    private List<Reserva> reservas = new ArrayList<>();




}
