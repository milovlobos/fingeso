package com.example.demo.Repository;

import com.example.demo.Entity.boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface boleta_repositorio extends JpaRepository<boleta, Long> {
}