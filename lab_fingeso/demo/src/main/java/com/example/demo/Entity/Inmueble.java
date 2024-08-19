package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "inmueble")
public class Inmueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "id_User")
    private long idUser;

    private String name;

    private String description;

    private long metros2;

    private String type;

    private String direccion;

    private long precio;

    private String fotoUrl;

    private boolean  verificado_admin;

    private boolean verificado;



    public Inmueble() {}

    public Inmueble(long id, boolean verificado, boolean verificado_admin, String fotoUrl, long precio, String direccion, String type, long metros2, String description, String name, long idUser) {
        this.id = id;
        this.verificado = verificado;
        this.verificado_admin = verificado_admin;
        this.fotoUrl = fotoUrl;
        this.precio = precio;
        this.direccion = direccion;
        this.type = type;
        this.metros2 = metros2;
        this.description = description;
        this.name = name;
        this.idUser = idUser;
    }

    // Getters y setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long id_user) {
        this.idUser = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getMetros2() {
        return metros2;
    }

    public void setMetros2(long metroCuadrados) {
        this.metros2 = metroCuadrados;
    }

    public String getType() {
        return type;
    }

    public void setType(String tipo) {
        this.type = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public boolean isVerificado_admin() {
        return verificado_admin;
    }

    public void setVerificado_admin(boolean verificado_admin) {
        this.verificado_admin = verificado_admin;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }
}