package com.example.refugio.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn (name = "IDTipocabaña")
    @JsonIgnoreProperties("cabañas")
    private TipoCabaña tipoCabaña;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cabaña")
    private List<CabañaEstado> estados = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cabaña")
    @JsonIgnoreProperties("cabaña")
    private List<Reserva> reservas = new ArrayList<>();




}
