package com.example.refugio.servicios;

import com.example.refugio.entidades.CostoTipoCabaña;
import com.example.refugio.repositorios.CostoTipoCabañaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostoTipoCabañaServicio {
    @Autowired
    CostoTipoCabañaRepositorio costoTipoCabañaRepositorio;


    public List<CostoTipoCabaña> getCostos(){
        return costoTipoCabañaRepositorio.findAll();
    }

    public Optional<CostoTipoCabaña> getCosto(Long id){
        return costoTipoCabañaRepositorio.findById(id);
    }


    public void saveOrUpdate(CostoTipoCabaña costoTipoCabaña){
        costoTipoCabañaRepositorio.save(costoTipoCabaña);
    }

    public void delete (Long id){
        costoTipoCabañaRepositorio.deleteById(id);
    }
}
