package com.example.demo.Service;

import com.example.demo.Entity.Usuario;
import com.example.demo.Repository.Usuariorepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Usuarioservicio {
    @Autowired
    private Usuariorepositorio userRepo;

    // logica de servicio
    public Usuario register(String name, String password, String email, String rol) {
        Usuario usuario = new Usuario(name, password, email, rol);
        Usuario existe = userRepo.findByemail(usuario.getEmail());
        if (existe != null) {
            return null;
        }
        return userRepo.save(usuario);

    }

    public int login(String email, String password) {
        Usuario user = userRepo.findByemail(email);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                if (user.getRoll().equals("usuario")) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public Usuario getUsuariobyId(long id) {
        return userRepo.findById(id).get();

    }
}
