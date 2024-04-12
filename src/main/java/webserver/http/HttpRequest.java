package webserver.http;

import java.util.ArrayList;
import java.util.List;

public class HttpRequest {
    private final StartLine startLine;
    private final Headers headers;
    private final String body;

    public HttpRequest(String request) {
        String[] split = request.split("\n");
        this.startLine = new StartLine(split[0]);

        boolean isHeaders = true;
        List<String> headerList = new ArrayList<>();
        String body = "";
        for (int i = 1; i < split.length; i++) {
            if (split[i].isBlank()) {
                isHeaders = false;
                continue;
            }
            if (isHeaders) {
                headerList.add(split[i]);
                continue;
            }
            body += split[i];
        }

        this.headers = new Headers(headerList);
        this.body = body;
    }

    public String getMethod() {
        return startLine.getMethod();
    }

    public String getPath() {
        return startLine.getPath();
    }

    public Extension getExtension() {
        return startLine.getExtension();
    }

    public boolean hasExtension() {
        return startLine.hasExtension();
    }

    public String getBody() {
        return body;
    }
}
