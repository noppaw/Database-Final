package com.udemy.flightReservation.entity;

import com.udemy.flightReservation.entity.common.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;

@Entity
//@Table(name = "PASSENGER",schema = "Udemy")
public class Payment extends AbstractEntity {
    @ManyToOne
    private Passenger passengerID;
    private String cardName;
    private String cardNumber;
    private String cardExpire;
    private String cvv;

    public Payment() {
    }

    public Passenger getPassengerID() {
        return passengerID;
    }
    public void setPassengerID(Passenger passengerID) {
        this.passengerID = passengerID;
    }
    public String getCardName() {
        return cardName;
    }
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public String getCardExpire() {
        return cardExpire;
    }
    public void setCardExpire(String cardExpire) {
        this.cardExpire = cardExpire;
    }
    public String getCvv() {
        return cvv;
    }
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
