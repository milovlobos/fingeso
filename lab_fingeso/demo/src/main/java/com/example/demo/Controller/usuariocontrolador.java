package com.example.demo.Controller;

import com.example.demo.Entity.Usuario;
import com.example.demo.Service.Usuarioservicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/usuario")
public class usuariocontrolador {
    @Autowired
    private Usuarioservicio usuarioservicio;
    @PostMapping("/register")
    // registro usuario
    public Usuario register(@RequestBody Usuario Nuser) {
        return usuarioservicio.register(Nuser.getName(),Nuser.getPassword(),Nuser.getEmail(),"Usuario");

    }
    //login usuario
    @PostMapping("/login")
    public int login(@RequestBody Usuario user) {return usuarioservicio.login(user.getEmail(),user.getPassword());

    }
    // buscar por if
    @GetMapping("/Id")
    public Usuario getUsuariobyId(@RequestParam("Id") String id) {
        System.out.printf("ID a buscar: %d\n", id);
        return usuarioservicio.getUsuariobyId(Integer.parseInt(id));
    }
    // pagar premium
    @PutMapping("/premium")
    public int premium(@RequestParam("Id") String id,@RequestParam String metodoPago) {
         return usuarioservicio.GetPremium(Integer.parseInt(id),metodoPago);

    }

}
