**Movie Theater Seating Allocation System**

**Program Description:**
This program takes an input text file with space separated reservation identifier and number of seats required via command line, reads line by line and processes the user requests for reserving seats in the movie theatre.

This project is purely using the concepts of Java (No database, Spring features, JUnit)

**Assumptions made for this program:**
- Customers will be satisfied if their entire group gets their seats in the same row. 
- Tickets will be allocated on first come basis from top to bottom.
- Safety precautions is must and customer satisfaction is given priority.
- If there is a row with required number of seats for a group all seats will be together.
- If there are no consecutive seats available after scanning all the rows, seats are allocated where there is a vacancy.
- If available seats are less than requested, the reservation is not completed.

**Assumptions:**
- All seats cost the same.
- Reservation is complete only if available seats are greater than requested.

**Goals of the Problem**

***Customer Satisfaction***

- Customers prefer watching the movie from last row and seating in groups. 
- The first priority is given to sitting together and then better viewing experience.

***Customer Safety***

- There is a three seat gap at all times between two different groups.
- Customers with same reservation id are seated consecutively.

***Theatre Utilization***
- First the customer satisfaction is given preference.
- If no seats are available consecutively, we start filling available seats from top rows.

**Steps for running MovieTheatreDriver Class**
- Open Command Line and go to project path . The code files will be inside the src folder within the corresponding packages.
- Run the following command - javac src/main/java/com/driver/TheatreMain.java . This command will compile the TheatreMain.java file and generate a .class file for the JVM. Similarly, compile the other files too.
- Next, Run the following command - java -cp src main.java.com.driver.TheatreMain ./src/resources/input.txt . The input.txt file contains the space separated reservation id and number of seats pairs. 
- You will get the path of the output.txt file on the console and the output.txt file will contain the seats reserved for the reservation ID's of those corresponding groups.

**Steps for running MovieTheatreDriverTest Class**
- Open Command Line and go to project path. The code files will be inside the src folder within the corresponding packages.
- Run the following command - javac src/test/java/com/driver/TheatreMainTest.java . This command will compile the TheatreMainTest.java file and generate a .class file for the JVM. Similarly, compile the other files too.
- Next, Run the following command - java -cp src test.java.com.driver.TheatreMainTest . This command will call the TheatreServiceTest and return the test results on the console.