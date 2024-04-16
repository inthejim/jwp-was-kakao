package webserver.handler;

import db.DataBase;
import model.User;
import utils.KeyValueParser;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;

import java.util.Map;

public class ServiceHandler implements Handler {
    public static final String CREATE_USER_PATH = "/user/create";

    @Override
    public void handling(HttpRequest httpRequest, HttpResponse httpResponse) {
        httpResponse.setStatusCode(HttpStatus.OK);

        if (httpRequest.getMethod().equals("GET")) {
            doGet(httpRequest, httpResponse);
        }

        if (httpRequest.getMethod().equals("POST")) {
            doPost(httpRequest, httpResponse);
        }
    }

    private void doPost(HttpRequest httpRequest, HttpResponse httpResponse) {
        String body = httpRequest.getBody();
        if (httpRequest.getPath().equals(CREATE_USER_PATH)) {
            UserService.addUser(KeyValueParser.parse(body, "&"));

            httpResponse.setStatusCode(HttpStatus.FOUND);
            httpResponse.addHeader("Location", "/index.html");
        }
    }

    private void doGet(HttpRequest httpRequest, HttpResponse httpResponse) {
        //TODO
    }
}
