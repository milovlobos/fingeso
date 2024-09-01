package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Service.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin()
@RequestMapping("/api/user")
public class User_controller {
    @Autowired
    private User_Service userService;
    @PostMapping("/register")
    // registro usuario
    public User register(@RequestBody User Nuser) {
        return userService.register(Nuser.getUsername(),Nuser.getUserPassword(),Nuser.getUserEmail());

    }
    //login usuario
    @PostMapping("/login")
    public int login(@RequestBody User user) {return userService.login(user.getUserEmail(),user.getUserPassword());

    }
    // buscar por if
    @GetMapping("/Id")
    public User getUserbyId(@RequestParam("Id") long id) {
        System.out.printf("ID a buscar: %d\n", id);
        return userService.getUserbyId(id);
    }
    // pagar premium
    @PostMapping("/premium")
    public int premium(@RequestBody Map<String, Object> body) {
        long id = Long.parseLong(body.get("Id").toString());
        String metodoPago = body.get("metodoPago").toString();
        return userService.GetPremium(id, metodoPago);
    }
    @GetMapping("/getuser")
    public User getUserbyUserEmail(@RequestParam("UserEmail") String email) {
        return userService.getUserbyEmail(email);
    }
}
