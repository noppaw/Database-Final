package com.udemy.flightReservation.controller;

import com.udemy.flightReservation.entity.Flight;
import com.udemy.flightReservation.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightRepository  flightRepository;

    @PostMapping()
    public String findFlights(@RequestParam("from") String from,@RequestParam("to") String to,
                              @RequestParam("dateOfDeparture") @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateOfDeparture,
                              @RequestParam("dateOfArrivals") @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateOfArrivals,
                              @RequestParam(defaultValue = "0") long departureFlightId,
                              Model model){

        model.addAttribute("from",from);
        model.addAttribute("to",to);

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String sDateOfDeparture = df.format(dateOfDeparture);
        String sDateOfArrivals = df.format(dateOfArrivals);
        model.addAttribute("dateOfDeparture",sDateOfDeparture);
        model.addAttribute("dateOfArrivals",sDateOfArrivals);

        if (departureFlightId == 0) {
            List<Flight> departureFlights = flightRepository.findFlights(from, to, dateOfDeparture);
            model.addAttribute("flights",departureFlights);

            return "displayDepartureFlights";
        } else {
            Flight departureFlight = flightRepository.findById(departureFlightId).get();
            model.addAttribute("departureFlight",departureFlight);

            List<Flight> arrivalsFlights = flightRepository.findFlights(to, from, dateOfArrivals);
            model.addAttribute("flights",arrivalsFlights);

            return "displayarrivalFlights";
        }
    }

    @PostMapping("findFlights")
    public String findFlightsAdmin(@RequestParam("from") String from,@RequestParam("to") String to,
                                   @RequestParam("dateOfDeparture") @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateOfDeparture,
                                   Model model){

        model.addAttribute("from",from);
        model.addAttribute("to",to);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String sDateOfDeparture = df.format(dateOfDeparture);
        model.addAttribute("dateOfDeparture",sDateOfDeparture);
        if (from == "") {
            List<Flight> flights = flightRepository.findFlightsCity("", to, dateOfDeparture);
            model.addAttribute("flights",flights);
        } else if (to == "") {
            List<Flight> flights = flightRepository.findFlightsCity(from, "", dateOfDeparture);
            model.addAttribute("flights",flights);
        }

        return "backoffice/findFlight";
    }

    @PostMapping("delete")
    public String delete(@RequestParam("id") long id, @RequestParam("from") String from,@RequestParam("to") String to,
                         @RequestParam("dateOfDeparture") @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateOfDeparture,
                         Model model){
        Flight flight = flightRepository.findById(id).get();
        if (flight.getId() == id) {
            flight.setEnabled(false);
            flightRepository.save(flight);
        }
        String page = this.findFlightsAdmin(from, to, dateOfDeparture, model);
        return page;
    }

    @PostMapping("edit")
    public String edit(@RequestParam("id") long id, Model model){
        Flight flight = flightRepository.findById(id).get();
        if (flight.getId() == id) {
            model.addAttribute("Flight",flight);
            return "backoffice/flight";
        }
        return null;
    }

    @PostMapping("add")
    public String add(Model model){
        Flight flight = new Flight();
        model.addAttribute("Flight", flight);
        return "backoffice/flight";
    }

    @PostMapping("save")
    public String add(@ModelAttribute Flight flight,
                      @RequestParam("iDateOfDeparture") @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateOfDeparture,
                      @RequestParam("iLocalDateDeparture") String iLocalDateDeparture,
                      @RequestParam("iLocalTimeDeparture") String iLocalTimeDeparture,
                      @RequestParam("iLocalDateArrival") String iLocalDateArrival,
                      @RequestParam("iLocalTimeArrival") String iLocalTimeArrival,
                      @RequestParam("iEstimatedDepartureTime") String iEstimatedDepartureTime,
                      Model model) {
        flight.setDateOfDeparture(dateOfDeparture);
        SimpleDateFormat fDateTime = new SimpleDateFormat("dd-MM-yyyy;HH:mm");
        SimpleDateFormat fTime = new SimpleDateFormat("HH:mm");
        try {
            Date localTimeDeparture = fDateTime.parse(iLocalDateDeparture.trim() + ";" + iLocalTimeDeparture.trim());
            Date localTimeArrival = fDateTime.parse(iLocalDateArrival.trim() + ";" + iLocalTimeArrival.trim());
            Date dEstimatedDepartureTime = fTime.parse(iEstimatedDepartureTime.trim());
            java.sql.Time estimatedDepartureTime = new java.sql.Time(dEstimatedDepartureTime.getTime());
            flight.setLocalTimeDeparture(localTimeDeparture);
            flight.setLocalTimeArrival(localTimeArrival);
            flight.setEstimatedDepartureTime(estimatedDepartureTime);
        } catch (ParseException parseException) {
            model.addAttribute("msg","Wrong format date or time");
            return "backoffice/flight";
        }
        flightRepository.save(flight);
        return "backoffice/findFlight";
    }
}
