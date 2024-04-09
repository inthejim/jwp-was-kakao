package webserver;

import java.util.Map;

public class HttpResponse {
    private HttpStatus statusCode;
    private Map<String, String> headers;
    private byte[] body;

    public HttpResponse() {
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public String getStartLine() {
        return "HTTP/1.1 " + statusCode.getValue() + " " + statusCode.getMessage();
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public byte[] getBody() {
        return body;
    }
}
