package main.java.com.utils;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class FileUtils {
    public FileUtils() {    // Default Constructor
    }

    Logger log = Logger.getLogger(FileUtils.class.getName());

    public List<String> readFile(String filePath) throws IOException { // Method to read all the lines from a File

        List<String> lines;
        try {   // Exception Handling for any errors while reading the file from the given path in console.
            lines = Files.readAllLines(Paths.get(filePath));
        } catch(FileNotFoundException e) {
            throw new FileNotFoundException("Reservation Requests Not Found!");
        } catch(IOException e) {
            throw new IOException("System is currently down. We are not able to accept any reservations. We regret the inconvenience!");
        }
        return lines;
    }

    public void writeFile(Map<String, String> map, String filePath, List<String> s) throws IOException{
        try {   //Exception Handling for any errors while writing to a file using the name given from the console.
            Path inputFilePath = Paths.get(filePath);
            String outFilePath = getOutFilePath(map, s, inputFilePath);
            log.info("Bookings Successful!");
            log.info(outFilePath);
        } catch(IOException e) {
            throw new IOException("System is currently down. We are not able to generate the tickets for users who booked. We regret the inconvenience!");
        }
    }

    private static String getOutFilePath(Map<String, String> map, List<String> s, Path inputFilePath) throws IOException {
        String outFilePath = inputFilePath.toAbsolutePath().toString().replace(inputFilePath.getFileName().toString(), "output.txt");
        try (FileWriter fp = new FileWriter(outFilePath)) {
            for (Map.Entry<String, String> entrySet : map.entrySet()) {
                fp.write(entrySet.getKey() + " " + entrySet.getValue() + "\n"); // Extracting every Key-Value Pair from the map that contains the corresponding reservationId and seatNumbers and writing it to the file.
            }
            for (String s1 : s)
                fp.write(s1 + "\n");
        }
        return outFilePath;
    }
}

