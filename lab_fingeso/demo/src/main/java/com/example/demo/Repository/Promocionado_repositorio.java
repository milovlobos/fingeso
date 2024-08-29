package com.example.demo.Repository;

import com.example.demo.Entity.Promocionado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface Promocionado_repositorio extends JpaRepository<Promocionado, Long> {
    long count();

    // Método que cuenta cuántas entradas de Promocionado tienen la fecha especificada
    int countByfecha(LocalDate fecha);
}
