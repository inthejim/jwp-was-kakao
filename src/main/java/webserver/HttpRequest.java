package webserver;

public class HttpRequest {

    private final String header;
    private final String path;

    public HttpRequest(String request) {
        this.header = request.split(" ")[0];
        this.path = request.split(" ")[1];
    }

    public String getHeader() {
        return header;
    }

    public String getPath() {
        return path;
    }
}
