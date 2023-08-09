package com.example.refugio.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class EstadoReserva {
    @Id
    private Long idER;
    private String nombreER;

}
