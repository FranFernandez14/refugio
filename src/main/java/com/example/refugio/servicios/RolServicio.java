package com.example.refugio.servicios;

import com.example.refugio.entidades.ReservaEstado;
import com.example.refugio.entidades.Rol;
import com.example.refugio.repositorios.ReservaEstadoRepositorio;
import com.example.refugio.repositorios.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServicio {

    @Autowired
    RolRepositorio rolRepositorio;


    public List<Rol> getRoles(){
        return rolRepositorio.findAll();
    }

    public Optional<Rol> getRol(Long id){
        return rolRepositorio.findById(id);
    }


    public void saveOrUpdate(Rol rol){rolRepositorio.save(rol);
    }

    public void delete (Long id){
        rolRepositorio.deleteById(id);
    }
}
