package webserver;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import utils.Encoder;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class HttpResponseTest {
    @ParameterizedTest
    @EnumSource(HttpStatus.class)
    void statusCode(HttpStatus httpStatus) {
        HttpResponse response = new HttpResponse();
        response.setStatusCode(httpStatus);

        assertThat(response.getStatusCode()).isEqualTo(httpStatus);

    }

    @ParameterizedTest
    @EnumSource(HttpStatus.class)
    void startLine(HttpStatus httpStatus) {
        HttpResponse response = new HttpResponse();
        response.setStatusCode(httpStatus);

        assertThat(response.getStartLine()).isEqualTo("HTTP/1.1 " + httpStatus.getValue() + " " + httpStatus.getMessage());
    }

    @ParameterizedTest
    @CsvSource(value = {"key, value"})
    void headers(String key, String value) {
        HttpResponse response = new HttpResponse();
        Map<String, String> headers = new HashMap<>();
        headers.put(key, value);

        response.setHeaders(headers);

        assertThat(response.getHeaders()).containsEntry(key, value);
    }

    @ParameterizedTest
    @CsvSource(value = {"안녕하세요", "바디입니다"})
    void body(String rawString) {
        byte[] body = Encoder.toBytes(rawString);
        HttpResponse response = new HttpResponse();
        response.setBody(body);

        assertThat(response.getBody()).isEqualTo(body);
    }
}
