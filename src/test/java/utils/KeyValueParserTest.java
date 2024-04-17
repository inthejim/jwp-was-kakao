package utils;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class KeyValueParserTest {
    @Test
    void key_value() {
        String input = "key=value";
        Map<String, String> parsed = KeyValueParser.parse(input, "&");

        assertThat(parsed.get("key")).isEqualTo("value");
    }

    @Test
    void key_values() {
        String input = "key=value&key2=value2";
        Map<String, String> parsed = KeyValueParser.parse(input, "&");

        assertAll(
                () -> assertThat(parsed.get("key")).isEqualTo("value"),
                () -> assertThat(parsed.get("key2")).isEqualTo("value2")
        );
    }

    @Test
    void empty() {
        String input = "";
        Map<String, String> parsed = KeyValueParser.parse(input, "&");
    }
}
