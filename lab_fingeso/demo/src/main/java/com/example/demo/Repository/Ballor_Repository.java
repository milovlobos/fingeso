package com.example.demo.Repository;

import com.example.demo.Entity.ballot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Ballor_Repository extends JpaRepository<ballot, Long> {
}