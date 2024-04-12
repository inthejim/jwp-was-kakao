package webserver.http;

import java.util.Arrays;

public enum HttpStatus {
    OK(200, "OK"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int value;
    private final String message;

    HttpStatus(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public static HttpStatus valueOf(int statusCode) {
        return Arrays.stream(HttpStatus.values())
                .filter(it -> it.value == statusCode)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치 하는 상태 코드가 없습니다."));
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
