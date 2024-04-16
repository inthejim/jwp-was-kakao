package webserver.http;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UriTest {

    @ParameterizedTest
    @CsvSource(value = {"/users, /users", "/users?name=mark, /users"})
    void getPath(String uriString, String path) {
        Uri uri = new Uri(uriString);

        assertThat(uri.getPath()).isEqualTo(path);
    }

    @ParameterizedTest
    @CsvSource(value = {"/index.html, HTML", "/menu/favicon.ico, ICO"})
    void getExtension(String uriString, String extension) {
        Uri uri = new Uri(uriString);

        assertThat(uri.getExtension()).isEqualTo(Extension.valueOf(extension));
    }

    @Test
    void getAttribute() {
        Uri uri = new Uri("/users?name=mark&password=1234");

        assertAll(
                () -> assertThat(uri.getAttribute("name")).isEqualTo("mark"),
                () -> assertThat(uri.getAttribute("password")).isEqualTo("1234")
        );
    }
}