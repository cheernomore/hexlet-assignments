package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage db) {
        Map<String, String> map = new HashMap<>(db.toMap());
        KeyValueStorage newDb = new InMemoryKV(new HashMap<>());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            db.unset(entry.getKey());
            db.set(entry.getValue(), entry.getKey());
        }
//        // заменяем db на новый объект
//        db.toMap().clear();
//        db.toMap().putAll(newDb.toMap());
    }
}
// END
