package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyValueParser {
    public static Map<String, String> parse(String input) {
        try {
            return Arrays.stream(input.split("&"))
                    .map(it -> it.split("="))
                    .collect(Collectors.toMap(
                            it -> it[0].trim(),
                            it -> it[1].trim()
                    ));
        } catch (Exception e) {
            return new HashMap<>();
        }
    }
}
