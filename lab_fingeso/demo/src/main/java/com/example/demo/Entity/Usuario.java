package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="usuario")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //atributos
    private long ID;
    private String name;
    private String password;
    private String email;
    private String roll;
    private boolean premium;

    public Usuario(String name, String password, String email, String roll, boolean premium) {
        this.ID = ID;
        this.name = name;
        this.password = password;
        this.email = email;
        this.roll = roll;
        this.premium = premium;
    }

    public Usuario() {

    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }


    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}


