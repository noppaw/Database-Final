package com.udemy.flightReservation.Dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ReservationRequest {

    private Long departureFlightId;
    private Long arrivalFlightId;
    private String pFirstName;
    private String pLastName;
    private String pEmail;
    private String pPhone;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date pBirthdate;
    private String nameOnCard;
    private String cardNumber;
    private String expiryDate;
    private String cvvNumber;

    public Long getDepartureFlightId() {
        return departureFlightId;
    }

    public void setDepartureFlightId(Long departureFlightId) {
        this.departureFlightId = departureFlightId;
    }

    public Long getArrivalFlightId() {
        return arrivalFlightId;
    }

    public void setArrivalFlightId(Long arrivalFlightId) {
        this.arrivalFlightId = arrivalFlightId;
    }

    public String getpFirstName() {
        return pFirstName;
    }

    public void setpFirstName(String pFirstName) {
        this.pFirstName = pFirstName;
    }

    public String getpLastName() {
        return pLastName;
    }

    public void setpLastName(String pLastName) {
        this.pLastName = pLastName;
    }

    public String getpEmail() {
        return pEmail;
    }

    public void setpEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    public String getpPhone() {
        return pPhone;
    }

    public void setpPhone(String pPhone) {
        this.pPhone = pPhone;
    }

    public Date getpBirthdate() {
        return pBirthdate;
    }

    public void setpBirthdate(Date pBirthdate) {
        this.pBirthdate = pBirthdate;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(String cvvNumber) {
        this.cvvNumber = cvvNumber;
    }
}
