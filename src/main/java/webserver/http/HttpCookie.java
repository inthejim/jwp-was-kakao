package webserver.http;

public class HttpCookie {

    String uuid;
    String path = "/";

    public HttpCookie(String uuid) {
        this.uuid = uuid;
    }

    public void setCookie(HttpResponse response) {
        response.addHeader("Set-Cookie", getUUID());
    }

    public String getCookie() {
        return "JSESSIONID=" + uuid + "; path=" + path;
    }

    public String getUUID() {
        return "JSESSIONID=" + uuid;
    }

}
