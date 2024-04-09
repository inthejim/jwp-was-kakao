package webserver;

public class HttpRequest {

    private final String method;
    private final String path;

    public HttpRequest(String request) {
        this.method = request.split(" ")[0];
        this.path = request.split(" ")[1];
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public boolean hasExtension() {
        return path.contains(".");
    }
}
