package main.java.com.service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class TheatreService {
    public TheatreService() {
    }

    Logger log = Logger.getLogger(TheatreService.class.getName());

    public static int rows = 10;    // This field indicates the row in the theatre.
    public static int columns = 20; // This field indicates the seat number in the particular row of the theatre.
    public static int totalSeats = rows * columns;    // This field the total number of seats in a theatre.
    public static Map<String, String> bookings = new LinkedHashMap<>();  // We use a LinkedHashMap to store the key-value pair, key being the reservationId from the input and value being the seat numbers that we allocate for that particular booking.
    public static int[][] seats = new int[rows][columns];   // Assigning all seats in the theatre with a default 0 value.
    public static int[] remainingSeats = Arrays.stream(new int[rows]).map(a -> a = 20).toArray();    // Indicates remaining seats in each row. Initially each row has 20 seats, so we set all the fields in the array to 20.
    public static int customerSatisfied = 0; // This field indicates the customer satisfaction. We use it to calculate how many groups were accommodated the way they wanted to.
    public static int totalCustomers = 0; // This field denotes the total number of customers who had booked a ticket successfully and secured a seat.

    public int seatCheck(String reservationID, int noOfSeats) {    // This method is used to check if the user requested a valid number of seats and if we can accommodate the group of people in the theatre.

        if (noOfSeats > 0) {
            if (totalSeats >= noOfSeats) {
                totalCustomers += noOfSeats;
                return reserveSeats(reservationID, noOfSeats);
            }
            else
                return 0;
        }
        return -1;
    }

    public int reserveSeats(String rid, int noOfSeatsToBook) { // This method does the actual seat reservation. If a seat has value 0, then it is not reserved and can be occupied, but if a seat has value 1, then it is reserved by a previous user.
        boolean singleRowAccommodation = false;  // This field is used to check if we are able to accommodate the entire group in a reservation in a single row or not.
        int currentRow = 0;
        for(int i = rows - 1; i > 0; i--) {
            if(remainingSeats[i] >= noOfSeatsToBook) {    // Whenever remaining seats in a row are more than no of seats to book, we can accommodate the entire group in a single row.
                currentRow = i;
                singleRowAccommodation = true;
                break;
            }
        }
        if(!singleRowAccommodation)      // If we cannot accommodate the group of people in a single row, then we start assigning seats from the last row.
            currentRow = rows - 1;

        while(currentRow >= 0 && noOfSeatsToBook > 0) {    // Checking vacancies in each row and also checking if we have more tickets to confirm for the group after every iteration.
            int currentColumn = 0;
            while(currentColumn < 20 && noOfSeatsToBook > 0) {    // Checking individual seats in the current row that is selected and also checking if we have more tickets to confirm for the group after every iteration.
                if(seats[currentRow][currentColumn] == 0) {  // If there is a vacancy, then reserve the seat
                    seats[currentRow][currentColumn] = 1;
                    if(bookings.containsKey(rid))
                        bookings.put(rid, bookings.get(rid) + "," + convertToString(currentRow, currentColumn));   // Add the seat number against the Reservation ID if already present
                    else
                        bookings.put(rid, convertToString(currentRow, currentColumn));    //Add the Reservation ID and Seat Number.
                    remainingSeats[currentRow] -= 1; // Reducing the remaining seats in this row by one as we have booked a seat successfully in this row.
                    totalSeats -= 1;  // Reducing the total seats in the theatre by one as we have booked a seat successfully.
                    noOfSeatsToBook -= 1;   // Reducing the number of seats to book for the reservationID as we have booked a seat successfully in this row.
                    customerSatisfied = singleRowAccommodation && noOfSeatsToBook <= 20 ? customerSatisfied + 1 : customerSatisfied;   // A customer is satisfied if he gets to sit with his group and all of them sit in the same row.
                }
                currentColumn++;
            }
            for (int i = 0; i < 3; i++) {   // For Customer Safety, we are leaving 3 seats as a buffer after every group booking in the row. We set the seat value to -1 indicating it is not available for reservation.
                if (currentColumn + i < 20) {
                    seats[currentRow][currentColumn + i] = -1;
                    totalSeats -= 1;
                    remainingSeats[currentRow] -= 1;
                }
            }
            currentRow--;
        }
        return 1;
    }

    private String convertToString (int currRow, int currCol) { // Framing the string for the seatNumber and sending it to the corresponding reservationID
        return ((char)(currRow + 65)) + String.valueOf(currCol + 1);
    }

   public void bookingsSession() { // We print the following booking session statistics
        log.info("Booking Session Stats: ");
        log.info("Total Number of Groups: " + bookings.size());    // number of groups that tried to book tickets at the theatre.
        log.info("Total Customers : " + totalCustomers);    // total number of customers that were able to secure a seat
        log.info("Total Number of Satisfied Customers: " + customerSatisfied); // Number of customers that were satisfied with the seats they got.
        log.info("Percentage of Satisfied Customers : " + (totalCustomers != 0 ? (customerSatisfied * 100) / totalCustomers : 0));
    }

    public Map<String, String> getResults() {   // Returns the final booking logs with key as the reservationID and value as the seats booked
        return bookings;
    }
}

