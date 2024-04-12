package webserver.http;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Headers {
    private Map<String, String> headers;

    public Headers(List<String> headers) {
        this.headers = headers.stream()
                .map(it -> it.split(":", 2))
                .collect(Collectors.toMap(
                        it -> it[0].trim(),
                        it -> it[1].trim()
                ));
    }

    public String get(String key) {
        return headers.get(key);
    }
}
