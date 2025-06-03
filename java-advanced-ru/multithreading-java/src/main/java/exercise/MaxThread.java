package exercise;

import java.util.Arrays;
import java.util.logging.Logger;

// BEGIN
public class MaxThread extends Thread {
    private int[] numbers;
    private int maxValue;

    MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        maxValue = Arrays.stream(numbers).max().getAsInt();
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int[] getNumbers() {
        return numbers;
    }
}
// END
