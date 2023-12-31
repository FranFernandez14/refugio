package com.example.refugio.servicios;

import com.example.refugio.entidades.TipoCabaña;
import com.example.refugio.repositorios.TipoCabañaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoCabañaServicio {
    @Autowired
    TipoCabañaRepositorio tipoCabañaRepositorio;

    public List<TipoCabaña> getTipos(){return tipoCabañaRepositorio.findAll();}

    public Optional<TipoCabaña> getTipoById(Long id){
        return tipoCabañaRepositorio.findById(id);
    }

    public void saveOrUpdate(TipoCabaña tipoCabaña){
        tipoCabañaRepositorio.save(tipoCabaña);
    }

    public void deleteById(Long id){
        tipoCabañaRepositorio.deleteById(id);
    }


}
