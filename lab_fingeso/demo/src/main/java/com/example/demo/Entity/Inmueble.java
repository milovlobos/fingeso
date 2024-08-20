package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "inmueble")  // Especifica que esta clase se mapeará a una tabla llamada "inmueble" en la base de datos
public class Inmueble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Anotación que indica que este campo es la clave primaria y se generará automáticamente
    private long id;

    @Column(name = "id_User")  // Mapea el campo "idUser" a la columna "id_User" en la tabla
    private long idUser;

    private String name;  // Nombre del inmueble

    private String description;  // Descripción del inmueble

    private long metros2;  // Metros cuadrados del inmueble

    private String type;  // Tipo de inmueble (por ejemplo, casa, departamento, etc.)

    private String direccion;  // Dirección del inmueble

    private long precio;  // Precio del inmueble

    private String fotoUrl;  // URL de la foto del inmueble

    private boolean verificado_admin;  // Indicador de si el inmueble ha sido verificado por un administrador

    private boolean verificado;  // Indicador de si el inmueble ha sido verificado (puede referirse a una verificación general o por el usuario)

    // Constructor sin argumentos requerido por JPA
    public Inmueble() {}

    // Constructor parametrizado para inicializar todos los campos de la clase
    public Inmueble(boolean verificado, boolean verificado_admin, String fotoUrl, long precio, String direccion, String type, long metros2, String description, String name, long idUser) {
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

    // Métodos getters y setters para cada atributo de la clase

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
