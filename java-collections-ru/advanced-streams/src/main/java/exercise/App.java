package exercise;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {

    public static String getForwardedVariables(String content) {

        return Arrays.stream(content.split("\n"))
                .filter(line -> line.startsWith("environment="))
                .map(line -> line.replace("\"", ""))
                .map(line -> line.split("environment=")[1])
                .flatMap(env -> Arrays.stream(env.split(",")))
                .filter(env -> env.startsWith("X_FORWARDED_"))
                .map(env -> env.replace("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
    }

}
//END
