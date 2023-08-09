package com.example.refugio.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class EstadoCabaña {
    @Id
    private Long idEC;
    private String nombreEC;
}
