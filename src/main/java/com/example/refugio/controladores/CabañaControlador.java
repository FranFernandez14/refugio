package com.example.refugio.controladores;

import com.example.refugio.dto.AuthResponseDTO;
import com.example.refugio.dto.LoginDTO;
import com.example.refugio.dto.NuevaCabañaDTO;
import com.example.refugio.dto.RegistroDTO;
import com.example.refugio.entidades.Cabaña;
import com.example.refugio.entidades.Rol;
import com.example.refugio.entidades.TipoCabaña;
import com.example.refugio.entidades.Usuario;
import com.example.refugio.repositorios.CabañaRepositorio;
import com.example.refugio.repositorios.TipoCabañaRepositorio;
import com.example.refugio.servicios.CabañaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cabañas")
public class CabañaControlador {

    @Autowired
    private CabañaServicio cabañaServicio;

    @Autowired
    private TipoCabañaRepositorio tipoCabañaRepositorio;

    @Autowired
    private CabañaRepositorio cabañaRepositorio;


    @PostMapping
    public void saveUpdate (@RequestBody Cabaña cabaña){
        cabañaServicio.saveOrUpdate(cabaña);
    }

    @GetMapping("buscar")
    public Optional<List<Cabaña>> buscar(){return cabañaServicio.buscar();}

    @PostMapping ("crear")
    public ResponseEntity<String> crear(@RequestBody NuevaCabañaDTO nuevaCabañaDTO){
        try {
            Cabaña cabaña = new Cabaña();

            cabaña.setTamaño(nuevaCabañaDTO.getTamaño());
            TipoCabaña tipoCabaña = tipoCabañaRepositorio.findById(nuevaCabañaDTO.getIdTipoCabaña()).
                    orElseThrow(() -> new RuntimeException("Tipo de cabaña no encontrado"));

            cabaña.setTipoCabaña(tipoCabaña);

            cabañaRepositorio.save(cabaña);

            return new ResponseEntity<>("Cabaña registrada correctamente", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al registrar la cabaña", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    @DeleteMapping ("/{Id}")
    public void delete(@PathVariable("Id") Long id){
        cabañaServicio.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<Cabaña> getById (@PathVariable ("id") Long id){
        return cabañaServicio.getCabaña(id);
    }


    @GetMapping("/buscarlibres  ")
    public Optional<List<Cabaña>> buscarCabañasPorTipoYDisponibilidad(
            @RequestParam Long IDTipoCabaña,
            @RequestParam LocalDate fechaInicio,
            @RequestParam  LocalDate fechaFin) {

        Optional<List<Cabaña>> cabañas = cabañaServicio.buscarCabañasPorTipoYDisponibilidad(IDTipoCabaña, fechaInicio, fechaFin);

        return cabañas;
    }
}
