package webserver.handler;

import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;

public class ServiceHandler implements Handler {

    @Override
    public void handling(HttpRequest httpRequest, HttpResponse httpResponse) {
        httpResponse.setStatusCode(HttpStatus.OK);
    }
}
