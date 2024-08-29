package com.example.demo.Service;

import com.example.demo.Repository.Promocionado_repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class fecha_servicio {

    @Autowired
    private Promocionado_repositorio promocionadoRepository;

    public List<LocalDate> obtener_dias_disponibles(List<LocalDate> fecha) {
        return fecha.stream()
                .filter(this::isDateAvailable)
                .collect(Collectors.toList());
    }

    private boolean isDateAvailable(LocalDate date) {
        int count = promocionadoRepository.countByfecha(date);
        return count < 10;
    }

}