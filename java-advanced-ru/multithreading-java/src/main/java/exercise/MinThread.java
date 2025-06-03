package exercise;

import java.util.Arrays;
import java.util.logging.Logger;

// BEGIN
public class MinThread extends Thread{
    private int[] numbers;
    private int minValue;

    MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {

        minValue = Arrays.stream(numbers).min().getAsInt();
    }

    public int getMinValue() {
        return minValue;
    }

    public int[] getNumbers() {
        return numbers;
    }
}
// END
