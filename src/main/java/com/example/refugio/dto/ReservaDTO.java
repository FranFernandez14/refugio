package com.example.refugio.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservaDTO {
    private Long IDUsuario;
    private Long IDCabaña;
    private int cantPersonas;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private float montoTotal;

}
