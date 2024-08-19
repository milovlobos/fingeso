package com.example.demo.Repository;

import com.example.demo.Entity.Promocionado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface Promocionado_repositorio extends JpaRepository<Promocionado, Long> {
    long count();
    // Método para contar las publicaciones por fecha
    @Query("SELECT COUNT(p) FROM Promocionado p WHERE p.fecha = :fecha")
    long countByFecha(@Param("fecha") LocalDateTime fecha);
}
