package com.example.refugio.entidades;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class CostoTipoCabaña {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IDCostoTipoCabaña;
    private float valorInicial;
    private float valorPorPersona;
    private LocalDateTime fechaHoraAlta;
    private LocalDateTime fechaHoraBaja;

    @ManyToOne
    @JoinColumn (name = "IDTipoCabaña")
    private TipoCabaña tipoCabaña;

}
