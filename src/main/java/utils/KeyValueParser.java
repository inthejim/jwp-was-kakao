package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyValueParser {

    public static final int KEY_INDEX = 0;
    public static final int VALUE_INDEX = 1;

    public static Map<String, String> parse(String input, String delimiter) {
        try {
            return Arrays.stream(input.split(delimiter))
                    .map(it -> it.split("="))
                    .collect(Collectors.toMap(
                            it -> it[KEY_INDEX].trim(),
                            it -> it[VALUE_INDEX].trim()
                    ));
        } catch (Exception e) {
            return new HashMap<>();
        }
    }
}
