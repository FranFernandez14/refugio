package com.example.refugio.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class TipoCabaña {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long IDTipoCabaña;
    private String nombre;

    @OneToMany (mappedBy = "tipoCabaña")
    private List<Cabaña> cabañas;

    @OneToMany (mappedBy = "tipoCabaña")
    private List<CostoTipoCabaña> costos;

    @ManyToMany
    @JoinTable(name = "TipoCabaña_Caracteristica",
            joinColumns = @JoinColumn(name = "IDTipoCabaña"),
            inverseJoinColumns = @JoinColumn(name = "IDCaracteristica"))
    private List<Caracteristica> caracteristicas = new ArrayList<>();

}
