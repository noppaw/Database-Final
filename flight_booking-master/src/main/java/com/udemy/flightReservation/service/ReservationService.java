package com.udemy.flightReservation.service;

import com.udemy.flightReservation.Dto.ReservationRequest;
import com.udemy.flightReservation.entity.Flight;
import com.udemy.flightReservation.entity.Passenger;
import com.udemy.flightReservation.entity.Payment;
import com.udemy.flightReservation.entity.Reservation;
import com.udemy.flightReservation.repository.FlightRepository;
import com.udemy.flightReservation.repository.PassengerRepository;
import com.udemy.flightReservation.repository.PaymentRepository;
import com.udemy.flightReservation.repository.ReservationRepository;
import com.udemy.flightReservation.util.EmailUtil;
import com.udemy.flightReservation.util.PdfGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    PdfGenerator pdfGenerator;
    @Autowired
    EmailUtil emailUtil;

    public String bookFlight(ReservationRequest reservationRequest){
       try {
           List<Passenger> passengers = passengerRepository.findPassenger(reservationRequest.getpFirstName(),
                   reservationRequest.getpLastName(), reservationRequest.getpBirthdate());
           Passenger passenger = null;
           if (passengers.size() > 0) {
               passenger = passengers.get(0);
           } else {
               passenger = new Passenger();
           }
           passenger.setFirstName(reservationRequest.getpFirstName());
           passenger.setLastName(reservationRequest.getpLastName());
           passenger.setEmail(reservationRequest.getpEmail());
           passenger.setPhone(reservationRequest.getpPhone());
           passenger.setBirthdate(reservationRequest.getpBirthdate());
           Passenger savedPassenger = passengerRepository.save(passenger);

           List<Payment> payments = paymentRepository.findPayment(reservationRequest.getCardNumber(),
                   reservationRequest.getExpiryDate(), reservationRequest.getCvvNumber());
           Payment payment = null;
           if (payments.size() > 0) {
               payment = payments.get(0);
           } else {
               payment = new Payment();
           }
           payment.setPassengerID(savedPassenger);
           payment.setCardName(reservationRequest.getNameOnCard());
           payment.setCardNumber(reservationRequest.getCardNumber());
           payment.setCardExpire(reservationRequest.getExpiryDate());
           payment.setCvv(reservationRequest.getCvvNumber());
           Payment savedPayment = paymentRepository.save(payment);

           Flight departureFlight = flightRepository.findById(reservationRequest.getDepartureFlightId()).get();
           Flight arrivalFlight = flightRepository.findById(reservationRequest.getArrivalFlightId()).get();

           Reservation reservation = new Reservation();
           reservation.setCheckedIn(false);
           reservation.setPassenger(savedPassenger);
           reservation.setDepartureFlight(departureFlight);
           reservation.setArrivalFlight(arrivalFlight);
           reservation.setPayment(savedPayment);
           Reservation savedReservation = reservationRepository.save(reservation);
           String fileName = "booking_no_" + savedReservation.getId() + ".pdf";
           String filePath = "src/main/resources/static/assets/pdf/" + fileName;

           pdfGenerator.generateItinerary(savedReservation,filePath);
           return "ticket?fileName=" + fileName;
       }catch (Exception e){
           e.printStackTrace();
       }

        return null;

    }

    public void sendItinerary(){
      // TODO document why this method is empty
    }

}
