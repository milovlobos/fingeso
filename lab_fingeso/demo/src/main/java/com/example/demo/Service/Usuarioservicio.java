package com.example.demo.Service;

import com.example.demo.Entity.Usuario;
import com.example.demo.Entity.boleta;
import com.example.demo.Repository.Usuariorepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class Usuarioservicio {
    @Autowired
    private Usuariorepositorio userRepo;
    @Autowired
    private com.example.demo.Repository.boleta_repositorio boleta_repositorio;

    // logica de servicio
    public Usuario register(String name, String password, String email, String rol) {
        Usuario usuario = new Usuario(name, password, email, rol,false);
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
    public int GetPremium(long id,String metodoPago) {
        Usuario user = userRepo.findById(id).get();
        if (user == null) {
            return 0;
        }
        if (user.isPremium() == true) {
            System.out.println("Usuario ya es premium Premium");
        }
        String descripcion="Pago membresia premium";
        long precio= (long) 5.000;
        user.setPremium(true);
        userRepo.save(user);
        boleta boleta = new boleta(user.getID(),precio, new Date(), metodoPago, descripcion);
        boleta_repositorio.save(boleta);
        return 1;

    }
}
