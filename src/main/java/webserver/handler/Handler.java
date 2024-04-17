package webserver.handler;

import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

public interface Handler {
    void handling(HttpRequest httpRequest, HttpResponse httpResponse);
}
