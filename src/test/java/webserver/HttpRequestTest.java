package webserver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpRequestTest {
    @Test
    void request_resttemplate() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @ParameterizedTest
    @CsvSource(value = {"GET / HTTP/1.1, GET", "POST / HTTP/1.1, POST"})
    void request_get_http_method(String requestRow, String method) {
        HttpRequest request = new HttpRequest(requestRow);

        assertThat(request.getMethod()).isEqualTo(method);
    }

    @ParameterizedTest
    @CsvSource(value = {"GET /index.html HTTP/1.1, /index.html", "GET / HTTP/1.1, /"})
    void request_path(String requestRow, String path) {
        HttpRequest request = new HttpRequest(requestRow);

        assertThat(request.getPath()).isEqualTo(path);
    }

    @ParameterizedTest
    @CsvSource(value = {"GET /index.html HTTP/1.1, true", "GET / HTTP/1.1, false", "GET /menu/favicon.ico HTTP/1.1, true"})
    void has_extension(String requestRow, boolean hasExtension) {
        HttpRequest request = new HttpRequest(requestRow);

        assertThat(request.hasExtension()).isEqualTo(hasExtension);
    }
}
