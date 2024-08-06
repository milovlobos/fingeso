package com.example.demo.Controller;

import com.example.demo.Entity.Promocionado;

import com.example.demo.Service.Promocionados_servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/promocionado")
public class Promocionado_controlador {
    @Autowired
    private Promocionados_servicio promocionados_servicio;

    @PostMapping("/promocionar")
    public Promocionado promocionarInmueble(@RequestParam long inmuebleId, @RequestParam double precio) {
        return promocionados_servicio.promocionarInmueble(inmuebleId, precio);
    }
}
