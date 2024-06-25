package main.java.com.driver;

import main.java.com.service.TheatreService;
import main.java.com.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TheatreMain {

    public static void main(String[] args) {
        // String[] args reads all the inputs passed by the user through commandLine.

        // We use FileUtils Class to handle File IO Operations.
        FileUtils fileUtils = new FileUtils();

        Logger log = Logger.getLogger(TheatreMain.class.getName());

        // We use the TheatreService class to perform Seat Management and Seat Booking
        // operations.
        TheatreService service = new TheatreService();

        List<String> reservations;
        try {
            reservations = fileUtils.readFile(args[0]); // Reading all the lines of input file. Each line giving the reservationId and noOfSeatsToBeBooked, ex - (R001 2)
            List<String> s = new ArrayList<>();
            for (String reservation : reservations) {
                if (reservation.isEmpty()) // if we reach the end of the file, then we terminate the execution and publish the results. (i.e. send out confirmed tickets)
                    break;
                String[] splitReservation = reservation.split(" ");
                int theatreResponse = service.seatCheck(splitReservation[0],
                        Integer.parseInt(splitReservation[1].trim())); // Calling the bookSeat function in TheatreService.
                if (theatreResponse == -1) {
                    log.info("Invalid Number of Seats Requested in ReservationID: " + splitReservation[0]);

                    s.add("Invalid Number of Seats Requested in ReservationID: " + splitReservation[0]);
                }
                if (theatreResponse == 0) {
                    log.info("Sorry, No Seats Available for ReservationID: " + splitReservation[0]);
                    s.add("Sorry, No Seats Available for ReservationID: " + splitReservation[0]);
                }
            }
            fileUtils.writeFile(service.getResults(), args[0], s);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        service.bookingsSession(); 	// We print the Booking Session Logs for the current booking to the console.
    }
}

