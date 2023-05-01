package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    Map<String, String> initialValue;
    Map<String, String> res;

    public InMemoryKV(Map<String, String> initialValue) {
        this.initialValue = new HashMap<>(initialValue);
        this.res = new HashMap<>(initialValue);
    }
    @Override
    public void set(String key, String value) {
        res.put(key, value);
    }

    @Override
    public void unset(String key) {
        res.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        String value = res.get(key);
        return value != null ? value : defaultValue;
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(res);
    }
}
// END
