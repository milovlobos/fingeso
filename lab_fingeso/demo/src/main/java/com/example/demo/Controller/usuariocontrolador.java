package com.example.demo.Controller;

import com.example.demo.Entity.Usuario;
import com.example.demo.Service.Usuarioservicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/usuario")
public class usuariocontrolador {
    @Autowired
    private Usuarioservicio usuarioservicio;
    @PostMapping("/register")
    // registro usuario
    public Usuario register(@RequestBody Usuario Nuser) {
        return usuarioservicio.register(Nuser.getName(),Nuser.getPassword(),Nuser.getEmail());

    }
    //login usuario
    @PostMapping("/login")
    public int login(@RequestBody Usuario user) {return usuarioservicio.login(user.getEmail(),user.getPassword());

    }
    // buscar por if
    @GetMapping("/Id")
    public Usuario getUsuariobyId(@RequestParam("Id") long id) {
        System.out.printf("ID a buscar: %d\n", id);
        return usuarioservicio.getUsuariobyId(id);
    }
    // pagar premium
    @PostMapping("/premium")
    public int premium(@RequestBody Map<String, Object> body) {
        long id = Long.parseLong(body.get("Id").toString());
        String metodoPago = body.get("metodoPago").toString();
        return usuarioservicio.GetPremium(id, metodoPago);
    }
    @GetMapping("/getusuario")
    public Usuario getUsuariobyemail(@RequestParam("email") String email) {
        return usuarioservicio.getUsuariobyEmail(email);
    }
}
