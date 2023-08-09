package com.example.refugio.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "cabañas")
public class Cabaña {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCabaña;
    private int tamaño;
    private LocalDateTime fechaHoraBajaCabaña;

    @OneToMany (mappedBy = "cabaña")
    private List<Reserva> reservas;

    @ManyToOne
    @JoinColumn (name = "IDTipoCabaña")
    private TipoCabaña tipoCabaña;

    @OneToMany (mappedBy = "cabaña")
    private List<CabañaEstado> estados;





}
