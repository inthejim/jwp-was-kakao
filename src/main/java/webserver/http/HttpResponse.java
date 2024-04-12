package webserver.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpResponse {
    private HttpStatus statusCode;
    private Map<String, String> headers = new HashMap<>();
    private byte[] body;

    public HttpResponse() {
    }

    private HttpResponse(HttpStatus statusCode, Map<String, String> headers, byte[] body) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }

    public static HttpResponse error() {
        return new HttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, new HashMap<>(), new byte[0]);
    }

    public static HttpResponse ok(byte[] body) {
        return new HttpResponse(HttpStatus.OK, new HashMap<>(), body);
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setBody(byte[] body) {
        headers.put("Content-Length", String.valueOf(body.length));
        this.body = body;
    }

    public String getStartLine() {
        return "HTTP/1.1 " + statusCode.getValue() + " " + statusCode.getMessage();
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    // TODO: View 쪽으로 넘기기
    public List<String> getHeaders() {
        return headers.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue() + "\r\n")
                .collect(Collectors.toList());
    }

    public void setHeader(String key, String value) {
        headers.put(key, value);
    }

    public byte[] getBody() {
        return body;
    }
}
