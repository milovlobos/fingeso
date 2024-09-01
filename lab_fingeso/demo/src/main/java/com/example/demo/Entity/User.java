package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="\"user\"") // Define que esta clase es una entidad que se mapea a una tabla llamada "usuario"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // El campo ID es la clave primaria de la tabla "usuario" y se genera automáticamente
    private long ID;

    // Atributos de la entidad Usuario
    private String username;       // Nombre del usuario
    private String userPassword;   // Contraseña del usuario
    private String userEmail;      // Correo electrónico del usuario
    private boolean userPremium;   // Indica si el usuario tiene una cuenta premium
    private boolean userAdmin;      //Indica si el usuario es administrador
    private boolean userAsist;      //Indica si el usuario es asistente legal

    // Constructor parametrizado para inicializar un nuevo Usuario con todos sus atributos
    public User(String Username, String UserPassword, String UserEmail, boolean UserPremium, boolean UserAdmin, boolean UserAsist) {

        this.username = Username;
        this.userPassword = UserPassword;
        this.userEmail = UserEmail;
        this.userPremium = UserPremium;
        this.userAdmin = UserAdmin;
        this.userAsist = UserAsist;
    }

    // Constructor por defecto necesario para JPA
    public User() {
    }

    // Métodos getters y setters para acceder y modificar los atributos del Usuario

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String password) {
        this.userPassword = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
    }

    public boolean isUserPremium() {
        return userPremium;
    }

    public void setUserPremium(boolean premium) {
        this.userPremium = premium;
    }

    public boolean isUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(boolean admin) {
        this.userAdmin = admin;
    }

    public boolean isUserAsist() {
        return userAsist;
    }

    public void setUserAsist(boolean assistant) {
        this.userAsist = assistant;
    }
}
