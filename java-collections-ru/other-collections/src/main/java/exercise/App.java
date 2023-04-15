package exercise;

import java.util.*;

// BEGIN
class App {
    public static LinkedHashMap genDiff(Map<String, Object> dictOne, Map<String, Object> dictTwo) {
        Set<String> keys = new HashSet<>(dictOne.keySet());
        LinkedHashMap<String, String> result = new LinkedHashMap<>();

        keys.addAll(dictTwo.keySet());

        for (String key: keys) {
            if (!dictOne.containsKey(key) && dictTwo.containsKey(key)) {
                result.put(key, "added");
            }

            if (dictOne.containsKey(key) && !dictTwo.containsKey(key)) {
                result.put(key, "deleted");
            }

            if (dictOne.containsKey(key) && dictTwo.containsKey(key)) {
                if (!dictOne.get(key).equals(dictTwo.get(key))) {
                    result.put(key, "changed");
                }
            }

            if (dictOne.containsKey(key) && dictTwo.containsKey(key)) {
                if (dictOne.get(key).equals(dictTwo.get(key))) {
                    result.put(key, "unchanged");
                }
            }

        }

        return result;
    }
}
//END
