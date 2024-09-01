package com.example.demo.Repository;

import com.example.demo.Entity.Promoted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.*;

@Repository
public interface Promoted_Repository extends JpaRepository<Promoted, Long> {
    long count();

    // Método que cuenta cuántas entradas de Promoted tienen la fecha especificada
    Optional<Promoted> findByDate(LocalDate date);
}
