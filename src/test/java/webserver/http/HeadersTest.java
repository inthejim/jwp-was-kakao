package webserver.http;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HeadersTest {
    @Test
    void header() {
        List<String> headerList = List.of("Host: localhost:8080", "Connection: keep-alive", "Accept: */*");
        Headers headers = new Headers(headerList);

        assertAll(
                () -> assertThat(headers.get("Host")).isEqualTo("localhost:8080"),
                () -> assertThat(headers.get("Connection")).isEqualTo("keep-alive"),
                () -> assertThat(headers.get("Accept")).isEqualTo("*/*")
        );
    }
}
