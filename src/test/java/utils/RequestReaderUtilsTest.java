package utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

class RequestReaderUtilsTest {
    @ParameterizedTest
    @ValueSource(strings = {"GET", "POST", "Hello,\nWorld"})
    void read(String string) throws IOException {
        String request = RequestReaderUtils.read(toReader(string));

        assertThat(request).isEqualTo(string);
    }

    private BufferedReader toReader(String string) {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(string.getBytes())));
    }
}
