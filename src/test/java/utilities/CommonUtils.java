package utilities;

import java.util.Random;

public class CommonUtils {
    private static int MILISECOND = 1000;

    public static int generateRandomNumber(int size) {
        Random random = new Random();
        return random.nextInt(size);
    }

    public static void wait(int second) {

        try {
            Thread.sleep(second * MILISECOND);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
