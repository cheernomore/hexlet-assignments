package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// BEGIN
class App {
    public static HashMap<String, Integer> getWordCount(String sentence) {
        HashMap<String, Integer> wordsCount = new HashMap<>();
        String[] wordsForCounting = sentence.split(" ");

        if (sentence.length() == 0) {
            return wordsCount;
        }

        for (String key: wordsForCounting) {
            wordsCount.putIfAbsent(key, 0);
        }

        Set<String> countSet = getMapKeySet(wordsCount);

        for (String word: countSet) {
            for (String wordS: wordsForCounting) {
                if (wordS.equals(word)) {
                    int vars = wordsCount.get(word);
                    wordsCount.put(word, ++vars);
                }
            }
        }

        return wordsCount;
    }

    public static String toString(Map<String, Integer> map) {

        if (map.isEmpty()) {
            return "{}";
        }

        String formattedHashMap = "{\n";

        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            formattedHashMap = formattedHashMap + "  " + entry.getKey() + ": " + entry.getValue() + "\n";
        }

        formattedHashMap = formattedHashMap + "}";

        return formattedHashMap;
    }

    public static Set<String> getMapKeySet(HashMap map) {
        return map.keySet();
    }
}
//END
