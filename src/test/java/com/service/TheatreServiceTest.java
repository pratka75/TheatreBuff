package test.java.com.service;

import main.java.com.driver.TheatreMain;
import main.java.com.service.TheatreService;

import java.util.logging.Logger;

public class TheatreServiceTest {
    TheatreService service = new TheatreService();

    Logger log = Logger.getLogger(TheatreServiceTest.class.getName());
    public void testTheatreService() {
        testSeatCheckNoSeatRequested();
        testSeatCheckLargeQuantitySeats();
        testReserveSeats();
    }
    private void testSeatCheckNoSeatRequested() {
        if(service.seatCheck("R001", 0) == -1)
            log.info("Test bookSeat Method in TheatreService Passes the Test: Invalid Seat Count Requested by R001");
        else
            log.info("Test bookSeat Method in TheatreService Fails the Test when no seats are requested scenario");
    }

    private void testSeatCheckLargeQuantitySeats() {
        if(service.seatCheck("R001", 800) == 0)
            log.info("Test bookSeat Method in TheatreService Passes the Test: No Seats available to reserve for R001");
        else
            log.info("Test bookSeat Method in TheatreService Fails the Test when seats requested are greater than available seats scenario");
    }

    private void testReserveSeats() {
        if(service.reserveSeats("R001", 1) == 1)
            log.info("Test fillSeats Method in TheatreService Passes the Test: 1 Seat reserved for R001");
        else
            log.info("Test fillSeats Method in TheatreService Fails the Test: No Seats reserved for R001");
    }
}
