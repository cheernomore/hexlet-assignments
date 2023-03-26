package exercise;

import java.util.*;

// BEGIN
class App {
    public static boolean scrabble(String symbolString, String targetWord) {
        if (symbolString.length() == 0) {
            return false;
        } else if (symbolString.length() < targetWord.length()) {
            return false;
        }

        List<String> symbolList = new ArrayList<>(Arrays.asList(symbolString.split("")));
        List<String> targetWordList = new ArrayList<>(Arrays.asList(targetWord.toLowerCase().split("")));

        for (String symbol: symbolList) {
            targetWordList.remove(symbol);
        }

        return targetWordList.isEmpty();
    }
}
//END
