package com.example.refugio.servicios;

import com.example.refugio.entidades.Cabaña;
import com.example.refugio.entidades.TipoCabaña;
import com.example.refugio.entidades.Usuario;
import com.example.refugio.repositorios.CabañaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CabañaServicio {

    @Autowired
    CabañaRepositorio cabañaRepositorio;

    public List<Cabaña> getCabañas(){
        return cabañaRepositorio.findAll();
    }


    public Optional<Cabaña> getCabaña(Long id){
        return cabañaRepositorio.findById(id);
    }


    public void saveOrUpdate(Cabaña cabaña){
       cabañaRepositorio.save(cabaña);
    }

    public void delete (Long id){
        cabañaRepositorio.deleteById(id);
    }



    public Optional<List<Cabaña>> buscarCabañasPorTipoYDisponibilidad(
            Long IDTipoCabaña, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Cabaña> cabañas = cabañaRepositorio.buscarCabañasPorTipoYDisponibilidad(IDTipoCabaña, fechaInicio, fechaFin);
        return Optional.ofNullable(cabañas.isEmpty() ? null : cabañas);
    }

    public Optional<List<Cabaña>> buscar(){
        return cabañaRepositorio.buscar();
    }



}
