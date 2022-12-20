package com.udemy.flightReservation.entity;

import com.udemy.flightReservation.entity.common.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Table(name = "RESERVATION",schema = "Udemy")
public class Reservation extends AbstractEntity{
    private Boolean checkedIn;
    private Integer numberOfBags;
    @ManyToOne
    private Passenger passenger;
    @ManyToOne
    private Flight departureFlight;
    @ManyToOne
    private Flight arrivalFlight;
    @ManyToOne
    private Payment payment;

    public Reservation() {
    }

    public Boolean getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(Boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public Integer getNumberOfBags() {
        return numberOfBags;
    }

    public void setNumberOfBags(Integer numberOfBags) {
        this.numberOfBags = numberOfBags;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getDepartureFlight() {
        return departureFlight;
    }

    public void setDepartureFlight(Flight departureFlight) {this.departureFlight = departureFlight;}

    public Flight getArrivalFlight() {
        return arrivalFlight;
    }

    public void setArrivalFlight(Flight arrivalFlight) {this.arrivalFlight = arrivalFlight;}

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {this.payment = payment;}
}
