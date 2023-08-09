package com.example.refugio.controladores;

import com.example.refugio.entidades.Cabaña;
import com.example.refugio.entidades.TipoCabaña;
import com.example.refugio.entidades.Usuario;
import com.example.refugio.servicios.CabañaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cabañas")
public class CabañaControlador {

    @Autowired
    private CabañaServicio cabañaServicio;

    @GetMapping
    public List<Cabaña> getCabaña (){
        return cabañaServicio.getCabañas();
    }
    @PostMapping
    public void saveUpdate (@RequestBody Cabaña cabaña){
        cabañaServicio.saveOrUpdate(cabaña);
    }

    @DeleteMapping ("/{Id}")
    public void delete(@PathVariable("Id") Long id){
        cabañaServicio.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<Cabaña> getById (@PathVariable ("id") Long id){
        return cabañaServicio.getCabaña(id);
    }


    @GetMapping("/buscar")
    public Optional<List<Cabaña>> buscarCabañasPorTipoYDisponibilidad(
            @RequestParam Long IDTipoCabaña,
            @RequestParam LocalDate fechaInicio,
            @RequestParam  LocalDate fechaFin) {

        Optional<List<Cabaña>> cabañas = cabañaServicio.buscarCabañasPorTipoYDisponibilidad(IDTipoCabaña, fechaInicio, fechaFin);

        return cabañas;
    }
}
