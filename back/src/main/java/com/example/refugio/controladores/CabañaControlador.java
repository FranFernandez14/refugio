package com.example.refugio.controladores;

import com.example.refugio.dto.BuscarCabañaDTO;
import com.example.refugio.dto.CabañaDTO;
import com.example.refugio.dto.RegistroDTO;
import com.example.refugio.entidades.Cabaña;
import com.example.refugio.entidades.Rol;
import com.example.refugio.entidades.TipoCabaña;
import com.example.refugio.entidades.Usuario;
import com.example.refugio.repositorios.TipoCabañaRepositorio;
import com.example.refugio.servicios.CabañaServicio;
import com.example.refugio.servicios.TipoCabañaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/cabañas")
public class CabañaControlador {

    @Autowired
    private CabañaServicio cabañaServicio;

    @Autowired
    private TipoCabañaRepositorio tipoCabañaRepositorio;

    @GetMapping
    public List<Cabaña> getCabañas (){
        return cabañaServicio.getCabañas();
    }

    @GetMapping("/{id}")
    public Optional<Cabaña> getById (@PathVariable ("id") Long id){
        return cabañaServicio.getCabaña(id);
    }
    @PostMapping
    public void saveUpdate (@RequestBody Cabaña cabaña){
        cabañaServicio.saveOrUpdate(cabaña);
    }

    @DeleteMapping ("/{Id}")
    public void delete(@PathVariable("Id") Long id){
        cabañaServicio.deleteById(id);
    }

    @PostMapping ("crear")
    public ResponseEntity<String> crear(@RequestBody CabañaDTO cabañaDTO){

        Cabaña cabaña = new Cabaña();

        cabaña.setTamaño(cabañaDTO.getTamaño());

        TipoCabaña tipoCabaña = tipoCabañaRepositorio.findById(cabañaDTO.getIdTipoCabaña()).get();

        cabaña.setTipoCabaña(tipoCabaña);

        tipoCabaña.getCabañas().add(cabaña);

        tipoCabañaRepositorio.save(tipoCabaña);

        cabañaServicio.saveOrUpdate(cabaña);
        return new ResponseEntity<>("Cabaña creada correctamente", HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public List<Cabaña> buscar(@RequestParam("fechaInicio") LocalDate fechaInicio,
                               @RequestParam("fechaFin") LocalDate fechaFin,
                               @RequestParam("cantPersonas") int cantPersonas,
                               @RequestParam("idTipoCabaña") Long idTipoCabaña) {
        BuscarCabañaDTO buscarCabañaDTO = new BuscarCabañaDTO();
        buscarCabañaDTO.setFechaInicio(fechaInicio);
        buscarCabañaDTO.setFechaFin(fechaFin);
        buscarCabañaDTO.setCantPersonas(cantPersonas);
        buscarCabañaDTO.setIdTipoCabaña(idTipoCabaña);

        return cabañaServicio.buscar(buscarCabañaDTO);
    }



}
