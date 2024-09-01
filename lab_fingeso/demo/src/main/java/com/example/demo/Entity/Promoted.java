package com.example.demo.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Promoted") // Define que esta clase es una entidad que se mapea a una tabla llamada "promocionado"
public class Promoted {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // El campo id es la clave primaria de la tabla "promocionado" y se genera automáticamente
    private long id;

    // Almacena la fecha y hora de la promoción
    private LocalDate date;

    private  boolean disponibilidad;

    @JoinColumn(name = "inmueble_id_1", referencedColumnName = "id")
    // Define la columna que hace referencia a la clave foránea (foreign key) del inmueble asociado
    private long Properti_id_1;

    @JoinColumn(name = "inmueble_id_2", referencedColumnName = "id")
    // Define la columna que hace referencia a la clave foránea (foreign key) del inmueble asociado
    private long Properti_id_2;

    @JoinColumn(name = "inmueble_id_3", referencedColumnName = "id")
    // Define la columna que hace referencia a la clave foránea (foreign key) del inmueble asociado
    private long Properti_id_3;

    @JoinColumn(name = "inmueble_id_4", referencedColumnName = "id")
    // Define la columna que hace referencia a la clave foránea (foreign key) del inmueble asociado
    private long Properti_id_4;

    @JoinColumn(name = "inmueble_id_5", referencedColumnName = "id")
    // Define la columna que hace referencia a la clave foránea (foreign key) del inmueble asociado
    private long Properti_id_5;

    @JoinColumn(name = "inmueble_id_6", referencedColumnName = "id")
    // Define la columna que hace referencia a la clave foránea (foreign key) del inmueble asociado
    private long Properti_id_6;

    @JoinColumn(name = "inmueble_id_7", referencedColumnName = "id")
    // Define la columna que hace referencia a la clave foránea (foreign key) del inmueble asociado
    private long Properti_id_7;
    @JoinColumn(name = "inmueble_id_8", referencedColumnName = "id")
    // Define la columna que hace referencia a la clave foránea (foreign key) del inmueble asociado
    private long Properti_id_8;
    @JoinColumn(name = "inmueble_id_9", referencedColumnName = "id")
    // Define la columna que hace referencia a la clave foránea (foreign key) del inmueble asociado
    private long Properti_id_9;

    @JoinColumn(name = "inmueble_id_10", referencedColumnName = "id")
    // Define la columna que hace referencia a la clave foránea (foreign key) del inmueble asociado
    private long Properti_id_10;

    public Promoted(LocalDate date, boolean disponibilidad, long properti_id_1, long properti_id_2, long properti_id_3, long properti_id_4, long properti_id_5, long properti_id_6, long properti_id_7, long properti_id_8, long properti_id_9, long properti_id_10) {
        this.date = date;
        this.disponibilidad = disponibilidad;
        Properti_id_1 = properti_id_1;
        Properti_id_2 = properti_id_2;
        Properti_id_3 = properti_id_3;
        Properti_id_4 = properti_id_4;
        Properti_id_5 = properti_id_5;
        Properti_id_6 = properti_id_6;
        Properti_id_7 = properti_id_7;
        Properti_id_8 = properti_id_8;
        Properti_id_9 = properti_id_9;
        Properti_id_10 = properti_id_10;
    }

    public Promoted() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public long getProperti_id_1() {
        return Properti_id_1;
    }

    public void setProperti_id_1(long properti_id_1) {
        Properti_id_1 = properti_id_1;
    }

    public long getProperti_id_2() {
        return Properti_id_2;
    }

    public void setProperti_id_2(long properti_id_2) {
        Properti_id_2 = properti_id_2;
    }

    public long getProperti_id_3() {
        return Properti_id_3;
    }

    public void setProperti_id_3(long properti_id_3) {
        Properti_id_3 = properti_id_3;
    }

    public long getProperti_id_4() {
        return Properti_id_4;
    }

    public void setProperti_id_4(long properti_id_4) {
        Properti_id_4 = properti_id_4;
    }

    public long getProperti_id_5() {
        return Properti_id_5;
    }

    public void setProperti_id_5(long properti_id_5) {
        Properti_id_5 = properti_id_5;
    }

    public long getProperti_id_6() {
        return Properti_id_6;
    }

    public void setProperti_id_6(long properti_id_6) {
        Properti_id_6 = properti_id_6;
    }

    public long getProperti_id_7() {
        return Properti_id_7;
    }

    public void setProperti_id_7(long properti_id_7) {
        Properti_id_7 = properti_id_7;
    }

    public long getProperti_id_8() {
        return Properti_id_8;
    }

    public void setProperti_id_8(long properti_id_8) {
        Properti_id_8 = properti_id_8;
    }

    public long getProperti_id_9() {
        return Properti_id_9;
    }

    public void setProperti_id_9(long properti_id_9) {
        Properti_id_9 = properti_id_9;
    }

    public long getProperti_id_10() {
        return Properti_id_10;
    }

    public void setProperti_id_10(long properti_id_10) {
        Properti_id_10 = properti_id_10;
    }
}

