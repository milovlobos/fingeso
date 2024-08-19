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
import java.util.Optional;

@Service
public class Promocionados_servicio {

    // Inyección de dependencias de los repositorios necesarios para las operaciones de promoción y manejo de inmuebles
    @Autowired
    private Promocionado_repositorio promocionadoRepo;

    @Autowired
    private inmueble_repositorio inmuebleRepo;
    @Autowired
    private com.example.demo.Repository.boleta_repositorio boleta_repositorio;

    // Método para promocionar un inmueble en una fecha específica
    public int promocionarInmueble(long inmuebleId, LocalDateTime fecha, String metodoPago) {
        // Verifica si el inmueble con el ID proporcionado existe en la base de datos
        Optional<Inmueble> inmuebleOpt = inmuebleRepo.findById(inmuebleId);
        if (inmuebleOpt.isEmpty()) {
            // Si el inmueble no es encontrado, retorna -1 (indica error o inmueble no encontrado)
            return 0; // Alternativamente, se podría lanzar una excepción específica
        }

        // Verifica cuántas publicaciones promocionadas ya están agendadas para la fecha dada
        long count = promocionadoRepo.countByFecha(fecha);

        if (count >= 10) {
            // Si ya hay 10 o más publicaciones programadas para esa fecha, retorna 1 (indica límite alcanzado)
            return 0; // Alternativamente, se podría lanzar una excepción específica
        }

        // Establece el precio de la promoción
        long precio = 5000;

        // Crea un nuevo objeto de tipo `Promocionado` con los datos del inmueble y la fecha
        Promocionado promocionado = new Promocionado(inmuebleId, precio, fecha);

        // Descripción para la boleta de pago de la promoción
        String descripcion = "Promoción Top 10";

        // Crea una nueva boleta para el pago de la promoción
        boleta boleta = new boleta(inmuebleId, precio, new Date(), metodoPago, descripcion);

        // Guarda la promoción en la base de datos
        promocionadoRepo.save(promocionado);
        // Guardar la boleta en la base de datos
        boleta_repositorio.save(boleta);

        // Retorna 1 para indicar que la promoción fue exitosa
        return 1;
    }
}
