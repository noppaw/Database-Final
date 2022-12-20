package com.udemy.flightReservation.entity;

import com.udemy.flightReservation.entity.common.AbstractEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
//@Table(name = "FLIGHT",schema = "Udemy")
public class Flight extends AbstractEntity {
    private String flightNumber;
    private String operatingAirlines;
    private String departureCity;
    private String arrivalCity;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfDeparture;
    @DateTimeFormat(pattern = "HH:mm")
    private Time estimatedDepartureTime;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date localTimeDeparture;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date localTimeArrival;
    private float price;
    private boolean enabled;

    public Flight() {

    }




    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOperatingAirlines() {
        return operatingAirlines;
    }

    public void setOperatingAirlines(String operatingAirlines) {
        this.operatingAirlines = operatingAirlines;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(Date dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public Time getEstimatedDepartureTime() {
        return estimatedDepartureTime;
    }

    public void setEstimatedDepartureTime(Time estimatedDepartureTime) {
        this.estimatedDepartureTime = estimatedDepartureTime;
    }
    public Date getLocalTimeDeparture() {
        return localTimeDeparture;
    }

    public void setLocalTimeDeparture(Date localTimeDeparture) {
        this.localTimeDeparture = localTimeDeparture;
    }

    public Date getLocalTimeArrival() {
        return localTimeArrival;
    }

    public void setLocalTimeArrival(Date localTimeArrival) {
        this.localTimeArrival = localTimeArrival;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
