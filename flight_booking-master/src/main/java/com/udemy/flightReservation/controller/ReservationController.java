package com.udemy.flightReservation.controller;

import com.udemy.flightReservation.Dto.ReservationRequest;
import com.udemy.flightReservation.entity.Flight;
import com.udemy.flightReservation.repository.FlightRepository;
import com.udemy.flightReservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    FlightRepository flightRepository;
    @Autowired
    ReservationService reservationService;

    @PostMapping()
    public String showCompleteReservation(@RequestParam("departureFlightId") long departureFlightId,
                                          @RequestParam(defaultValue = "0") long arrivalFlightId,
                                          Model model) {
        Flight departureFlight = flightRepository.findById(departureFlightId).get();
        model.addAttribute("departureFlight", departureFlight);
        if (arrivalFlightId > 0) {
            Flight arrivalFlight = flightRepository.findById(arrivalFlightId).get();
            model.addAttribute("arrivalFlight", arrivalFlight);
        }
        return "confirmReservation";
    }

    @PostMapping("/complete")
    public String completeReservation(@ModelAttribute ReservationRequest reservationRequest, Model model) {
        String filePath = reservationService.bookFlight(reservationRequest);
        model.addAttribute("msg","Reservation created successfully");
        model.addAttribute("filePath",filePath);
        return "reservationConfirmation";
    }

    @GetMapping("/ticket")
    public ResponseEntity<Resource> openTicket(@RequestParam("fileName") String fileName, HttpServletRequest request) {

        Resource resource;
        String filePath = "src/main/resources/static/assets/pdf/" + fileName;
        Path path = Paths.get(filePath);
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception ex) {
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}
