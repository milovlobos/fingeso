package com.example.demo.Controller;

import com.example.demo.Service.Inmueble_servicio;
import com.example.demo.Service.fecha_servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/api/fechas")
public class fechas_controlador {

    @Autowired
    private fecha_servicio fecha_servicio;


    @GetMapping("/available-dates")
    public List<LocalDate> getAvailableDates(@RequestParam List<LocalDate> dates) {
        return fecha_servicio.obtener_dias_disponibles(dates);
    }
}
