package exercise;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return users.stream()
                .sorted(Comparator.comparing(user -> LocalDate.parse(user.get("birthday"), formatter)))
                .filter(user -> user.get("gender").equals("male"))
                .map(x -> x.get("name"))
                .collect(Collectors.toList());
    }
}
// END
