package webserver.http;

public class ResponseStatusLine {
    public static final String DEFAULT_PROTOCOL_VERSION = "HTTP/1.1";
    private String version;
    private HttpStatus status;

    public ResponseStatusLine(HttpStatus status){
        this.version = DEFAULT_PROTOCOL_VERSION;
        this.status = status;
    }

    public HttpStatus getStatus(){
        return status;
    }

    public String getVersion(){
        return version;
    }

    public String getStatusCode(){
        return String.valueOf(status.getValue());
    }

    public String getStatusMessage(){
        return status.getMessage();
    }

}
