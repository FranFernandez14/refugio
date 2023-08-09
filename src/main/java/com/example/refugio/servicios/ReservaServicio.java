package com.example.refugio.servicios;


import com.example.refugio.entidades.Reserva;
import com.example.refugio.repositorios.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServicio {

    @Autowired
    ReservaRepositorio reservaRepositorio;

    public List<Reserva> getReservas(){
        return reservaRepositorio.findAll();
    }

    public Optional<Reserva> getReserva(Long id){
        return reservaRepositorio.findById(id);
    }


    public void saveOrUpdate(Reserva reserva){
        reservaRepositorio.save(reserva);
    }

    public void delete (Long id){
        reservaRepositorio.deleteById(id);
    }

}
