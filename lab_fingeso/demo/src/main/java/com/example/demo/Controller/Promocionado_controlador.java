package com.example.demo.Controller;

import com.example.demo.Service.Promocionados_servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin
@RequestMapping("/api/promocionado")
public class Promocionado_controlador {
    @Autowired
    private Promocionados_servicio promocionados_servicio;

    @PostMapping("/promocionar")
    public int promocionarInmueble(@RequestParam long inmuebleId, @RequestParam LocalDate fecha, @RequestParam String metodoPago) {
        return promocionados_servicio.promocionarInmueble(inmuebleId,fecha,metodoPago);
    }
}
