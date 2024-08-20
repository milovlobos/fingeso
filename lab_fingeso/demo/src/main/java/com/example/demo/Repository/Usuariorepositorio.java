package com.example.demo.Repository;

import com.example.demo.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Usuariorepositorio extends JpaRepository<Usuario, Long> {

    Usuario findByemail(String email);
}
