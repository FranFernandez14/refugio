package com.example.refugio.repositorios;

import com.example.refugio.entidades.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoReservaRepositorio extends JpaRepository<EstadoReserva, Long> {
}
