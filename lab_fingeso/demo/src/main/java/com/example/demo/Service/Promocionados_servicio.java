package com.example.demo.Service;

import com.example.demo.Entity.Inmueble;
import com.example.demo.Entity.Promocionado;
import com.example.demo.Repository.Promocionado_repositorio;
import com.example.demo.Repository.inmueble_repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class Promocionados_servicio {
    @Autowired
    private Promocionado_repositorio promocionadoRepo;

    @Autowired
    private inmueble_repositorio inmuebleRepo;

    public Promocionado promocionarInmueble(long inmuebleId, double precio) {
        if (promocionadoRepo.count() >= 10) {
            throw new IllegalStateException("Ya existen 10 inmuebles promocionados");
        }

        Inmueble inmueble = inmuebleRepo.findById(inmuebleId)
                .orElseThrow(() -> new NoSuchElementException("Inmueble no encontrado"));

        Promocionado promocionado = new Promocionado(inmueble, precio);
        return promocionadoRepo.save(promocionado);
    }
}
