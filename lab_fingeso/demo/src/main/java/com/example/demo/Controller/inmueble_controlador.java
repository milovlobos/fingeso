package com.example.demo.Controller;

import com.example.demo.Entity.boleta;
import com.example.demo.Entity.Inmueble;
import com.example.demo.Service.Inmueble_servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/inmueble")
public class inmueble_controlador {

    @Autowired
    private Inmueble_servicio inmuebleServicio;

    // Crear un nuevo inmueble
    @PostMapping("/create")
    public Inmueble createInmueble(@RequestBody Inmueble inmueble) {
        // Los atributos verificado_admin y verificado se establecerán por defecto como false
        return inmuebleServicio.createInmueble(
                inmueble.getIdUser(),
                inmueble.getName(),
                inmueble.getDescription(),
                inmueble.getMetros2(),
                inmueble.getType(),
                inmueble.getDireccion(),
                inmueble.getPrecio(),
                inmueble.getFotoUrl(),
                inmueble.getFecha_termino()
        );
    }

    // Actualizar un inmueble existente
    @PutMapping("/update/{id}")
    public Inmueble updateInmueble(@PathVariable long id, @RequestBody Inmueble inmueble) {
        return inmuebleServicio.updateInmueble(
                id,
                inmueble.getName(),
                inmueble.getDescription(),
                inmueble.getMetros2(),
                inmueble.getType(),
                inmueble.getDireccion(),
                inmueble.getPrecio(),
                inmueble.getFotoUrl(),
                inmueble.isVerificado_admin(),  // Nuevo parámetro
                inmueble.isVerificado()        // Nuevo parámetro
        );
    }

    // Eliminar un inmueble
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInmueble(@PathVariable long id, @RequestParam long id_user) {
        inmuebleServicio.deleteInmueble(id, id_user);
        return ResponseEntity.noContent().build();
    }

    // Obtener un inmueble por su ID
    @GetMapping("/getById")
    public Inmueble getInmuebleById(@RequestParam("id") long id) {
        return inmuebleServicio.getInmuebleById(id);
    }


    // Obtener todos los inmuebles de un usuario
    @GetMapping("/usuario/{idUser}")
    public List<Inmueble> getInmueblesByUserId(@PathVariable Long idUser) {
        return inmuebleServicio.findByIdUser(idUser);
    }

    // Buscar inmuebles por criterios
    @GetMapping("/search")
    public List<Inmueble> searchInmuebles(
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "precioMin", required = false) Long precioMin,
            @RequestParam(value = "precioMax", required = false) Long precioMax,
            @RequestParam(value = "direccion", required = false) String direccion) {
        return inmuebleServicio.searchInmuebles(tipo, precioMin, precioMax, direccion);
    }

    // Paginación de inmuebles
    @GetMapping("/page")
    public Page<Inmueble> getInmuebles(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize) {
        return (Page<Inmueble>) inmuebleServicio.getInmuebles(pageNumber, pageSize);
    }

    // Comprar un inmueble
    @PostMapping("/comprar/{inmuebleId}")
    public boleta comprarInmueble(@PathVariable long inmuebleId, @RequestParam long userId, @RequestParam String metodoPago) {
        return inmuebleServicio.comprarInmueble(inmuebleId, userId, metodoPago);
    }
}