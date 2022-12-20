package com.udemy.flightReservation.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.udemy.flightReservation.entity.Reservation;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class PdfGenerator  {

    public void generateItinerary(Reservation reservation, String filePath){
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            document.add(generateTable(reservation));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public PdfPTable generateTable(Reservation reservation){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        DateFormat tf = new SimpleDateFormat("HH:mm");

        PdfPTable table = new PdfPTable(2);
        PdfPCell cell ;
        cell = new PdfPCell(new Phrase("Departure Flight Details"));
        cell.setColspan(2);
        table.addCell(cell);
        table.addCell("Departure city");
        table.addCell(reservation.getDepartureFlight().getDepartureCity());
        table.addCell("Arrival city");
        table.addCell(reservation.getDepartureFlight().getArrivalCity());
        table.addCell("Flight number ");
        table.addCell(reservation.getDepartureFlight().getFlightNumber());
        table.addCell("Departure date");
        String sDateOfDeparture = df.format(reservation.getDepartureFlight().getLocalTimeDeparture());
        table.addCell(sDateOfDeparture);
        table.addCell("Arrival date");
        String sDateOfArrival = df.format(reservation.getDepartureFlight().getLocalTimeArrival());
        table.addCell(sDateOfArrival);
        table.addCell("Estimated time");
        String sEstimated = tf.format(reservation.getDepartureFlight().getEstimatedDepartureTime());
        table.addCell(sEstimated);

        cell = new PdfPCell(new Phrase("Arrival Flight Details"));
        cell.setColspan(2);
        table.addCell(cell);
        table.addCell("Departure city");
        table.addCell(reservation.getArrivalFlight().getDepartureCity());
        table.addCell("Arrival city");
        table.addCell(reservation.getArrivalFlight().getArrivalCity());
        table.addCell("Flight number ");
        table.addCell(reservation.getArrivalFlight().getFlightNumber());
        table.addCell("Departure date");
        sDateOfDeparture = df.format(reservation.getArrivalFlight().getLocalTimeDeparture());
        table.addCell(sDateOfDeparture);
        table.addCell("Arrival date");
        sDateOfArrival = df.format(reservation.getArrivalFlight().getLocalTimeArrival());
        table.addCell(sDateOfArrival);
        table.addCell("Estimated time");
        sEstimated = tf.format(reservation.getArrivalFlight().getEstimatedDepartureTime());
        table.addCell(sEstimated);

        cell = new PdfPCell(new Phrase(""));
        cell.setColspan(2);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Passenger Details"));
        cell.setColspan(2);
        table.addCell(cell);

        table.addCell("First Name");
        table.addCell(reservation.getPassenger().getFirstName());
        table.addCell("Last Name");
        table.addCell(reservation.getPassenger().getLastName());
        table.addCell("Email");
        table.addCell(reservation.getPassenger().getEmail());
        table.addCell("Phone");
        table.addCell(reservation.getPassenger().getPhone());

        return table;

    }

}
