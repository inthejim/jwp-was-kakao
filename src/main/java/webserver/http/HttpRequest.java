package webserver.http;

import java.util.ArrayList;
import java.util.List;

public class HttpRequest {
    private final RequestStartLine requestStartLine;
    private final Headers headers;
    private Body body;

    public HttpRequest(String request) {
        String[] split = request.split("\n");
        this.requestStartLine = new RequestStartLine(split[0]);

        boolean isHeaders = true;
        List<String> headerList = new ArrayList<>();

        StringBuilder body = new StringBuilder();
        for (int i = 1; i < split.length; i++) {
            if (split[i].isBlank()) {
                isHeaders = false;
                continue;
            }
            if (isHeaders) {
                headerList.add(split[i]);
                continue;
            }
            body.append(split[i]);
        }

        this.headers = new Headers(headerList);
        this.body = new Body(body.toString());
    }

    public String getMethod() {
        return requestStartLine.getMethod();
    }

    public String getPath() {
        return requestStartLine.getUri().getPath();
    }

    public Extension getExtension() {
        return requestStartLine.getUri().getExtension();
    }

    public boolean hasExtension() {
        return requestStartLine.getUri().getExtension()!=null;
    }

    public String getBody() {
        return body.getBody();
    }

    public void setBody(String body) {
        this.body = new Body(body);
    }

    public String getAttribute(String key) {
        return headers.get(key);
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "startLine=" + requestStartLine +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}
