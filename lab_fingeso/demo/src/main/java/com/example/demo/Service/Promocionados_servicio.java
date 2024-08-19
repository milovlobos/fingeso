package com.example.demo.Service;

import com.example.demo.Entity.Inmueble;
import com.example.demo.Entity.Promocionado;
import com.example.demo.Entity.boleta;
import com.example.demo.Repository.Promocionado_repositorio;
import com.example.demo.Repository.inmueble_repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class Promocionados_servicio {
    @Autowired
    private Promocionado_repositorio promocionadoRepo;

    @Autowired
    private inmueble_repositorio inmuebleRepo;

    public int promocionarInmueble(long inmuebleId, LocalDateTime fecha, String metodoPago) {
        // Verifica si el inmueble existe
        Optional<Inmueble> inmuebleOpt = inmuebleRepo.findById(inmuebleId);
        if (!inmuebleOpt.isPresent()) {
            // Inmueble no encontrado
            return -1; // o lanzar una excepción adecuada
        }

        // Verifica cuántas publicaciones ya están agendadas para la fecha dada
        long count = promocionadoRepo.countByFecha(fecha);

        if (count >= 10) {
            // Ya hay 10 o más publicaciones para esa fecha
            return 1; // o lanzar una excepción adecuada
        }

        // Crea una nueva promoción
        long precio= (long) 5.000;
        Promocionado promocionado = new Promocionado(inmuebleId,precio,fecha);
        String descripcion = "promocion top  10";
        boleta boleta= new boleta(inmuebleId,precio,new Date(),metodoPago,descripcion);


        promocionadoRepo.save(promocionado);

        return 0; // Promoción exitosa
    }
}
