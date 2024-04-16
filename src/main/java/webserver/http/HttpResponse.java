package webserver.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpResponse {

    private ResponseStatusLine responseStatusLine;
    private Map<String, String> headers = new HashMap<>();
    private byte[] body = new byte[0];

    public HttpResponse() {
    }

    private HttpResponse(HttpStatus statusCode, Map<String, String> headers, byte[] body) {
        this.responseStatusLine = new ResponseStatusLine(statusCode);
        this.headers = headers;
        this.body = body;
    }

    public static HttpResponse error() {
        return new HttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, new HashMap<>(), new byte[0]);
    }

    public static HttpResponse ok(byte[] body) {
        return new HttpResponse(HttpStatus.OK, new HashMap<>(), body);
    }


    public String getStatusLine() {
        return responseStatusLine.getVersion() + " " + responseStatusLine.getStatusCode() + " " + responseStatusLine.getStatusMessage();
    }

    public HttpStatus getStatusCode() {
        return responseStatusLine.getStatus();
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.responseStatusLine = new ResponseStatusLine(statusCode);
    }

    // TODO: View 쪽으로 넘기기
    public List<String> getHeaders() {
        return headers.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue() + "\r\n")
                .collect(Collectors.toList());
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        headers.put("Content-Length", String.valueOf(body.length));
        this.body = body;
    }
}
