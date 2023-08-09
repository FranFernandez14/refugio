package com.example.refugio.controladores;

import com.example.refugio.entidades.TipoCabaña;
import com.example.refugio.servicios.TipoCabañaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cabañas/tipos")
public class TipoCabañaControlador {

    @Autowired
    TipoCabañaServicio tipoCabañaServicio;

    @GetMapping
    public List<TipoCabaña> getTiposCabaña(){
        return tipoCabañaServicio.getTiposCabaña();
    }

    @PostMapping
    public void saveUpdate (@RequestBody TipoCabaña tipoCabaña){
        tipoCabañaServicio.saveOrUpdate(tipoCabaña);
    }

    @DeleteMapping ("/{Id}")
    public void delete(@PathVariable("Id") Long id){
        tipoCabañaServicio.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<TipoCabaña> getById (@PathVariable ("id") Long id){
        return tipoCabañaServicio.getTipoCabaña(id);
    }

}
