package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="usuario") // Define que esta clase es una entidad que se mapea a una tabla llamada "usuario"
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // El campo ID es la clave primaria de la tabla "usuario" y se genera automáticamente
    private long ID;

    // Atributos de la entidad Usuario
    private String name;       // Nombre del usuario
    private String password;   // Contraseña del usuario
    private String email;      // Correo electrónico del usuario
    private String roll;       // Rol del usuario (ej. "Vendedor", "Comprador", etc.)
    private boolean premium;   // Indica si el usuario tiene una cuenta premium

    // Constructor parametrizado para inicializar un nuevo Usuario con todos sus atributos
    public Usuario(String name, String password, String email, String roll, boolean premium) {
        this.ID = ID;  // Aunque este atributo se inicializa, su valor se gestiona automáticamente por la base de datos.
        this.name = name;
        this.password = password;
        this.email = email;
        this.roll = roll;
        this.premium = premium;
    }

    // Constructor por defecto necesario para JPA
    public Usuario() {
    }

    // Métodos getters y setters para acceder y modificar los atributos del Usuario

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
