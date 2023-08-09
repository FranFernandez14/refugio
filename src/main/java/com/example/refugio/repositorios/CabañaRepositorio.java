package com.example.refugio.repositorios;

import com.example.refugio.entidades.Cabaña;
import com.example.refugio.entidades.TipoCabaña;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CabañaRepositorio extends JpaRepository<Cabaña, Long> {

    @Query("SELECT c FROM Cabaña c LEFT JOIN c.estados ce WHERE c.tipoCabaña.IDTipoCabaña = :IDTipoCabaña AND (ce.fechaInicioCE > :fechaFin OR ce.fechaFinCE < :fechaInicio)")
    List<Cabaña> buscarCabañasPorTipoYDisponibilidad(
            @Param("IDTipoCabaña") Long IDTipoCabaña,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);
    @Query("SELECT c FROM Cabaña c")
    Optional<List<Cabaña>> buscar();
}





