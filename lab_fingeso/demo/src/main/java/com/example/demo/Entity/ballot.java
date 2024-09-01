package com.example.demo.Entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ballot")
public class ballot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private Long ballotIduser;

    @Column(name = "inmueble_id")
    private Long ballotIdproperty;

    private long ballotPrice;
    private Date ballotDate;
    private String ballotMethod_Payment;
    private String ballotDescription;

    public ballot(long id_user, long ballotPrice, Date date, String ballotMethod_Payment, String ballotDescription) {
        this.ballotIduser = id_user;
        this.ballotPrice = ballotPrice;
        this.ballotDate = date;
        this.ballotMethod_Payment = ballotMethod_Payment;
        this.ballotDescription = ballotDescription;

    }

    public ballot(long id_usuario, long id_inmueble, long ballotPrice, Date ballotDate, String ballotMethod_Payment, String ballotDescription) {
        this.ballotIduser = id_usuario;
        this.ballotIdproperty = id_inmueble;
        this.ballotPrice = ballotPrice;
        this.ballotDate = ballotDate;
        this.ballotMethod_Payment = ballotMethod_Payment;
        this.ballotDescription = ballotDescription;

    }

    public ballot() {

    }

    // Getters y setters

    public String getBallotDescription() {
        return ballotDescription;
    }

    public void setBallotDescription(String descripcion) {
        this.ballotDescription = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_user() {
        return ballotIduser;
    }

    public void setId_user(long id_user) {
        this.ballotIduser = id_user;
    }

    public long getId_inmueble() {
        return ballotIdproperty;
    }

    public void setId_inmueble(long id_inmueble) {
        this.ballotIdproperty = id_inmueble;
    }

    public long getBallotPrice() {
        return ballotPrice;
    }

    public void setBallotPrice(long precio) {
        this.ballotPrice = precio;
    }

    public Date getBallotDate() {
        return ballotDate;
    }

    public void setBallotDate(Date fecha) {
        this.ballotDate = fecha;
    }

    public String getBallotMethod_Payment() {
        return ballotMethod_Payment;
    }

    public void setBallotMethod_Payment(String metodoPago) {
        this.ballotMethod_Payment = metodoPago;
    }
}