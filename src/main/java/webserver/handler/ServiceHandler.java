package webserver.handler;

import db.DataBase;
import model.User;
import utils.KeyValueParser;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;

import java.util.Map;

public class ServiceHandler implements Handler {

    @Override
    public void handling(HttpRequest httpRequest, HttpResponse httpResponse) {
        httpResponse.setStatusCode(HttpStatus.OK);

        if (httpRequest.getMethod().equals("GET")) {

            if (httpRequest.getPath().equals("/user/create")) {
                String userId = httpRequest.getAttribute("userId");
                String name = httpRequest.getAttribute("name");
                String password = httpRequest.getAttribute("password");
                String email = httpRequest.getAttribute("email");

                DataBase.addUser(new User(userId, password, name, email));

                httpResponse.setStatusCode(HttpStatus.FOUND);
                httpResponse.addHeader("Location", "/index.html");
            }
        }

        if (httpRequest.getMethod().equals("POST")) {
            if (httpRequest.getPath().equals("/user/create")) {
                String body = httpRequest.getBody();
                Map<String, String> userParameters = KeyValueParser.parse(body);
                String userId = userParameters.get("userId");
                String name = userParameters.get("name");
                String password = userParameters.get("password");
                String email = userParameters.get("email");

                DataBase.addUser(new User(userId, password, name, email));

                httpResponse.setStatusCode(HttpStatus.FOUND);
                httpResponse.addHeader("Location", "/index.html");
            }
        }
    }
}
