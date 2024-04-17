package webserver.http;

public class RequestStartLine {
    private final String method;
    private final Uri uri;
    private final String version;

    public RequestStartLine(String line) {
        String[] split = line.split(" ");
        this.method = split[0];
        this.uri = new Uri(split[1]);
        this.version = split[2];
    }

    public String getMethod() {
        return method;
    }

    public Uri getUri() {
        return uri;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "RequestStartLine{" +
                "method='" + method + '\'' +
                ", uri=" + uri +
                ", version='" + version + '\'' +
                '}';
    }

}
