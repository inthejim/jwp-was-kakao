package webserver.http;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class requestStartLineTest {
    @Test
    void method() {
        RequestStartLine requestStartLine = new RequestStartLine("GET / HTTP/1.1");

        assertThat(requestStartLine.getMethod()).isEqualTo("GET");
    }

    @Test
    void version() {
        RequestStartLine requestStartLine = new RequestStartLine("GET / HTTP/1.1");

        assertThat(requestStartLine.getVersion()).isEqualTo("HTTP/1.1");
    }

    @Test
    void uri() {
        RequestStartLine requestStartLine = new RequestStartLine("GET /users?username=jenna&password=1234 HTTP/1.1");

        Uri result = new Uri("/users?username=jenna&password=1234");
        assertThat(requestStartLine.getUri().equals(result)).isTrue();
    }
}
