package timer.app;

public class Model {

        long startTime = System.currentTimeMillis();

    public Long time(){
        long elapsedTime = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTime / 1000;
        long secondsDisplay = elapsedSeconds % 60;
        long elapsedMinutes = elapsedSeconds / 60;
        //put here code to format and display the values
        return elapsedSeconds;

    }


}