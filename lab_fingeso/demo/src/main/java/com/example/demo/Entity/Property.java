package com.example.demo.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Property")  // Especifica que esta clase se mapeará a una tabla llamada "inmueble" en la base de datos
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Anotación que indica que este campo es la clave primaria y se generará automáticamente
    private long id;


    private long propertyIdUser;

    private String propertyName;  // Nombre del inmueble

    private String propertyDescription;  // Descripción del inmueble

    private long propertyMeter2;  // Metros cuadrados del inmueble

    private String propertyType;  // Tipo de inmueble (por ejemplo, casa, departamento, etc.)

    private String propertyDress;  // Dirección del inmueble

    private long propertyPrice;  // Precio del inmueble

    private String propertyPhotoURL;  // URL de la foto del inmueble

    private boolean propertyCheck_Admin;  // Indicador de si el inmueble ha sido verificado por un administrador

    private boolean propertyCheck;  // Indicador de si el inmueble ha sido verificado (puede referirse a una verificación general o por el usuario)

    private LocalDate propertyEnd_Date;

    // Constructor sin argumentos requerido por JPA
    public Property() {}

    // Constructor parametrizado para inicializar todos los campos de la clase
    public Property(boolean PropertyCheck, boolean PropertyCheck_Admin, String PropertyPhotoURL, long PropertyPrice, String PropertyDress, String PropertyType, long PropertyMeter2, String PropertyDescription, String PropertyName, long PropertyIdUser, LocalDate PropertyEnd_Date) {
        this.propertyCheck = PropertyCheck;
        this.propertyCheck_Admin = PropertyCheck_Admin;
        this.propertyPhotoURL = PropertyPhotoURL;
        this.propertyPrice = PropertyPrice;
        this.propertyDress = PropertyDress;
        this.propertyType = PropertyType;
        this.propertyMeter2 = PropertyMeter2;
        this.propertyDescription = PropertyDescription;
        this.propertyName = PropertyName;
        this.propertyIdUser = PropertyIdUser;
        this.propertyEnd_Date = PropertyEnd_Date;
    }

    // Métodos getters y setters para cada atributo de la clase

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPropertyIdUser() {
        return propertyIdUser;
    }

    public void setPropertyIdUser(long id_user) {
        this.propertyIdUser = id_user;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String name) {
        this.propertyName = name;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String description) {
        this.propertyDescription = description;
    }

    public long getPropertyMeter2() {
        return propertyMeter2;
    }

    public void setPropertyMeter2(long metroCuadrados) {
        this.propertyMeter2 = metroCuadrados;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String tipo) {
        this.propertyType = tipo;
    }

    public String getPropertyDress() {
        return propertyDress;
    }

    public void setPropertyDress(String direccion) {
        this.propertyDress = direccion;
    }

    public long getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(long precio) {
        this.propertyPrice = precio;
    }

    public String getPropertyPhotoURL() {
        return propertyPhotoURL;
    }

    public void setPropertyPhotoURL(String fotoUrl) {
        this.propertyPhotoURL = fotoUrl;
    }

    public boolean isPropertyCheck_Admin() {
        return propertyCheck_Admin;
    }

    public void setPropertyCheck_Admin(boolean verificado_admin) {
        this.propertyCheck_Admin = verificado_admin;
    }

    public boolean isPropertyCheck() {
        return propertyCheck;
    }

    public void setPropertyCheck(boolean verificado) {
        this.propertyCheck = verificado;
    }

    public LocalDate getPropertyEnd_Date() {
        return propertyEnd_Date;
    }

    public void setPropertyEnd_Date(LocalDate fecha_termino) {
        this.propertyEnd_Date = fecha_termino;
    }
}
