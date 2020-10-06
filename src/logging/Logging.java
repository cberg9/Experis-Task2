package logging;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logging {
    private long startTime;
    
//method that calculates the run time of a sequence.
    public void start() {
         startTime = System.currentTimeMillis();
    }

    public void stop(String message) {
        long timeFrame = System.currentTimeMillis() - startTime;

        try {
            LocalDateTime myObj = LocalDateTime.now(); //Generates the local time
            FileWriter myWriter = new FileWriter("userlog.txt", true);
            myWriter.append(myObj + " " + message+ ". The function took "
                + timeFrame + " ms to execute." + System.getProperty( "line.separator" ));
            myWriter.close();
          } catch (IOException e) {
            System.out.println("Something went wrong with writing to log.");
            e.printStackTrace();
          }
    }
}
