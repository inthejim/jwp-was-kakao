package webserver.handler;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import db.DataBase;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.KeyValueParser;
import webserver.WebApplicationServer;
import webserver.http.HttpCookie;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ServiceHandler implements Handler {
    private static final Logger logger = LoggerFactory.getLogger(WebApplicationServer.class);
    public static final String CREATE_USER_PATH = "/user/create";
    public static final String LOGIN_PATH = "/user/login";
    public static final String LOGIN_LIST = "/user/list";

    SessionManager sessionManager = new SessionManager();

    @Override
    public void handling(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        httpResponse.setStatusCode(HttpStatus.OK);

        if (httpRequest.getMethod().equals("GET")) {
            doGet(httpRequest, httpResponse);
        }

        if (httpRequest.getMethod().equals("POST")) {
            doPost(httpRequest, httpResponse);
        }
    }

    private void doPost(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        String body = httpRequest.getBody();
        if (httpRequest.getPath().equals(CREATE_USER_PATH)) {
            UserService.addUser(KeyValueParser.parse(body, "&"));

            httpResponse.setStatusCode(HttpStatus.FOUND);
            httpResponse.addHeader("Location", "/index.html");
            return;
        }

        if (httpRequest.getPath().equals(LOGIN_PATH)) {
            Map<String, String> userParameters = KeyValueParser.parse(body, "&");
            String userId = userParameters.get("userId");
            String password = userParameters.get("password");
            User logginedUser = LoginService.login(userId, password);

            //로그인 실패 경우
            if (logginedUser == null) {
                httpResponse.setStatusCode(HttpStatus.FOUND);
                httpResponse.addHeader("Location", "/user/login_failed.html");
                return;
            }

            String JsessionId = getJsessionId(httpRequest);
            //새로 로그인하는 경우
            if (JsessionId == null || sessionManager.findSession(JsessionId) == null) {
                HttpCookie cookie = sessionManager.createSession(logginedUser);
                cookie.setCookie(httpResponse);
            }
            httpResponse.setStatusCode(HttpStatus.FOUND);
            httpResponse.addHeader("Location", "/index.html");
        }
    }

    private static String getJsessionId(HttpRequest httpRequest) {
        return KeyValueParser.parse(httpRequest.getAttribute("Cookie"), "; ").get("JSESSIONID");
    }

    //TODO: controller 매핑 및 분기 시점 이동 필요
    private void doGet(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        String jsessionId=getJsessionId(httpRequest);

        //로그인되어있을 때
        if (httpRequest.getPath().equals(LOGIN_PATH)) {
            if (LoginService.loggined(sessionManager, jsessionId)) {

                httpResponse.setStatusCode(HttpStatus.FOUND);
                httpResponse.addHeader("Location", "/index.html");
                return;
            }
        }

        //유저 리스트 확인
        if (httpRequest.getPath().equals(LOGIN_LIST)) {
            if (LoginService.loggined(sessionManager, jsessionId)) {

                TemplateLoader loader = new ClassPathTemplateLoader();
                loader.setPrefix("/templates");
                loader.setSuffix(".html");
                Handlebars handlebars = new Handlebars(loader);

                Template template = handlebars.compile("user/list");
                List<User> userList = DataBase.findAll().stream().collect(Collectors.toList());
                template.apply(userList);
                return;
            }

            httpResponse.setStatusCode(HttpStatus.FOUND);
            httpResponse.addHeader("Location", "/user/login.html");
            return;
        }

        //유저 프로필 확인
        if (httpRequest.getPath().equals("/user/profile")) {
            if (LoginService.loggined(sessionManager, jsessionId)) {

                TemplateLoader loader = new ClassPathTemplateLoader();
                loader.setPrefix("/templates");
                loader.setSuffix(".html");
                Handlebars handlebars = new Handlebars(loader);

                Template template = handlebars.compile("user/profile");
                User user = (User) sessionManager.findSession(jsessionId).getAttribute("user");
                template.apply(user);
                return;
            }

            httpResponse.setStatusCode(HttpStatus.FOUND);
            httpResponse.addHeader("Location", "/user/login.html");
        }

    }
}
