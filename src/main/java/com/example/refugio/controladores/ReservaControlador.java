package com.example.refugio.controladores;

import com.example.refugio.dto.ReservaDTO;
import com.example.refugio.entidades.*;
import com.example.refugio.repositorios.EstadoCabañaRepositorio;
import com.example.refugio.repositorios.EstadoReservaRepositorio;
import com.example.refugio.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/reservas")

public class ReservaControlador {

    @Autowired
    private ReservaServicio reservaServicio;

    @Autowired
    private CabañaServicio cabañaServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ReservaEstadoServicio reservaEstadoServicio;

    @Autowired
    private EstadoReservaRepositorio estadoReservaRepositorio;

    @Autowired
    private EstadoCabañaRepositorio estadoCabañaRepositorio;

    @Autowired
    private CabañaEstadoServicio cabañaEstadoServicio;

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

    @PostMapping ("/reservar")
    public ResponseEntity<String> reservar(ReservaDTO reservaDTO){

        if(reservaDTO.getCantPersonas() == cabañaServicio.getCabaña(reservaDTO.getIDCabaña()).get().getTamaño()){
            return new ResponseEntity<>("La cabaña no admite tantas personas", HttpStatus.BAD_REQUEST);
        }

        List<CabañaEstado> estados = cabañaServicio.getCabaña(reservaDTO.getIDCabaña()).get().getEstados();
        for (CabañaEstado estado: estados) {
            if(((reservaDTO.getFechaInicio().isBefore(estado.getFechaFinCE()) && reservaDTO.getFechaInicio().isAfter(estado.getFechaInicioCE())) ||
                    (reservaDTO.getFechaFin().isBefore(estado.getFechaFinCE()) && reservaDTO.getFechaFin().isAfter(estado.getFechaInicioCE())) ||
                    (reservaDTO.getFechaInicio().isBefore(estado.getFechaInicioCE()) && reservaDTO.getFechaFin().isBefore(estado.getFechaFinCE()))||
                    reservaDTO.getFechaInicio().isEqual((estado.getFechaInicioCE())))){
                return new ResponseEntity<>("Ya hay una reserva en esas fechas", HttpStatus.BAD_REQUEST);
            }
        }

        Reserva reserva = new Reserva();
        reserva.setMontoTotal(reservaDTO.getMontoTotal());
        reserva.setFechaInicio(reservaDTO.getFechaInicio());
        reserva.setFechaFin(reservaDTO.getFechaFin());
        reserva.setCantPersonas(reservaDTO.getCantPersonas());

        Usuario usuario = usuarioServicio.getUsuario(reservaDTO.getIDUsuario()).get();

        reserva.setUsuario(usuario);

        Cabaña cabaña = cabañaServicio.getCabaña(reservaDTO.getIDCabaña()).get();
        reserva.setCabaña(cabaña);

        ReservaEstado reservaEstado = new ReservaEstado();
        reservaEstado.setEstadoReserva(estadoReservaRepositorio.findByNombreER("Reservada").get());
        reserva.setReservasEstado(Collections.singletonList(reservaEstado));

        reserva.setFechaReserva(LocalDateTime.now());

        reservaServicio.saveOrUpdate(reserva);

        usuario.getReservas().add(reserva);
        usuarioServicio.saveOrUpdate(usuario);

        reservaEstado.setFechaInicioRE(LocalDateTime.now());
        reservaEstadoServicio.saveOrUpdate(reservaEstado);

        cabaña.getReservas().add(reserva);

        CabañaEstado cabañaEstado = new CabañaEstado();
        cabañaEstado.setEstadoCabaña(estadoCabañaRepositorio.findByNombreEC("Ocupada").get());
        cabañaEstado.setFechaInicioCE(reserva.getFechaInicio());
        cabañaEstado.setFechaFinCE(reserva.getFechaFin());
        cabañaEstado.setCabaña(cabaña);
        cabaña.getEstados().add(cabañaEstado);
        cabañaEstadoServicio.saveOrUpdate(cabañaEstado);

        cabañaServicio.saveOrUpdate(cabaña);

        return new ResponseEntity<>("Reserva realizada correctamente", HttpStatus.OK);
    }



}
