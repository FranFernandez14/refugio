package com.example.refugio.controladores;

import com.example.refugio.entidades.Reserva;
import com.example.refugio.servicios.ReservaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/reservas")

public class ReservaControlador {

    @Autowired
    ReservaServicio reservaServicio;


    @GetMapping
    public List<Reserva> getReservas(){
        return reservaServicio.getReservas();
    }

    @PostMapping
    public void saveUpdate (@RequestBody Reserva reserva){
        reservaServicio.saveOrUpdate(reserva);
    }

    @DeleteMapping ("/{Id}")
    public void delete(@PathVariable("Id") Long id){
        reservaServicio.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<Reserva> getById (@PathVariable ("id") Long id){
        return reservaServicio.getReserva(id);
    }




}
