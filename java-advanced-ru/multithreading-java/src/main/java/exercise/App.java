package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] nums) {
        MinThread minThread = new MinThread(nums);
        MaxThread maxThread = new MaxThread(nums);
        Map<String, Integer> minMax = new HashMap<>();

        LOGGER.info("Thread " + minThread.getName() + " started");
        minThread.start();

        LOGGER.info("Thread " + maxThread.getName() + " started");
        maxThread.start();

        try {
            minThread.join(); // Ожидание завершения потока
            minMax.put("min", minThread.getMinValue());

            maxThread.join();
            minMax.put("max", maxThread.getMaxValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return minMax;
    }
    // END
}
