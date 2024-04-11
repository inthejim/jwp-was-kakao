package webserver.http;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StartLine {
    private final String method;
    private final String uri;
    private final String path;
    private Map<String, String> attributes = new HashMap<>();

    public StartLine(String line) {
        String[] split = line.split(" ");
        this.method = split[0];
        this.uri = split[1];
        this.path = uri.split("\\?")[0];
        if (uri.split("\\?").length == 2) {
            String queryString = uri.split("\\?")[1];
            initAttributes(queryString);
        }
    }

    private void initAttributes(String queryString) {
        this.attributes = Arrays.stream(queryString.split("&"))
                .map(it -> it.split("="))
                .collect(Collectors.toMap(
                        it -> it[0],
                        it -> it[1]
                ));
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getPath() {
        return path;
    }

    public String getAttribute(String key) {
        return attributes.get(key);
    }
}
