package webserver.http;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class requestStartLineTest {
    @Test
    void method() {
        RequestStartLine requestStartLine = new RequestStartLine("GET / HTTP/1.1");

        assertThat(requestStartLine.getMethod()).isEqualTo("GET");
    }

    @Test
    void uri() {
        RequestStartLine requestStartLine = new RequestStartLine("GET /users HTTP/1.1");

        assertThat(requestStartLine.getUri()).isEqualTo("/users");
    }

    @Test
    void uri2() {
        RequestStartLine requestStartLine = new RequestStartLine("GET /users?name=mark HTTP/1.1");

        assertThat(requestStartLine.getUri()).isEqualTo("/users?name=mark");
    }

    @Test
    void path() {
        RequestStartLine requestStartLine = new RequestStartLine("GET /users HTTP/1.1");

        assertThat(requestStartLine.getPath()).isEqualTo("/users");
    }

    @Test
    void path2() {
        RequestStartLine requestStartLine = new RequestStartLine("GET /index.html HTTP/1.1");

        assertThat(requestStartLine.getPath()).isEqualTo("/index.html");
    }

    @Test
    void path3() {
        RequestStartLine requestStartLine = new RequestStartLine("GET /users?name=mark HTTP/1.1");

        assertThat(requestStartLine.getPath()).isEqualTo("/users");
    }

    @Test
    void attribute() {
        RequestStartLine requestStartLine = new RequestStartLine("GET /users?name=mark HTTP/1.1");

        assertThat(requestStartLine.getAttribute("name")).isEqualTo("mark");
    }

    @Test
    void attributes() {
        RequestStartLine requestStartLine = new RequestStartLine("GET /users?name=mark&password=1234 HTTP/1.1");

        assertAll(
                () -> assertThat(requestStartLine.getAttribute("name")).isEqualTo("mark"),
                () -> assertThat(requestStartLine.getAttribute("password")).isEqualTo("1234")
        );
    }

    @Test
    void extension() {
        RequestStartLine requestStartLine = new RequestStartLine("GET /index.html HTTP/1.1");

        assertThat(requestStartLine.getExtension().getValue()).isEqualTo(".html");
    }
}
