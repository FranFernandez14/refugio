package com.example.refugio.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class TipoCabaña {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IDTipoCabaña;
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoCabaña")
    @JsonIgnoreProperties("tipoCabaña")
    private List<Cabaña> cabañas = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoCabaña")
    private List<CostoTipoCabaña> costos = new ArrayList<>();


}
