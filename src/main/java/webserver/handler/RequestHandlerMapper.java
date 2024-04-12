package webserver.handler;

import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

public class RequestHandlerMapper {
    public static Handler mapping(HttpRequest request, HttpResponse response) {
        if (request.hasExtension()) {
            return new ResourceHandler();
        }
        return new ServiceHandler();
    }
}
