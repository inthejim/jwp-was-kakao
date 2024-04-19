package webserver.handler;

import db.DataBase;
import model.User;

public class LoginService {

    public static User login(String userId, String password) {
        User user = DataBase.findUserById(userId);
        if (user == null || user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public static boolean loggined(SessionManager sessionManager, String jsessionId) {
        if(sessionManager.findSession(jsessionId)==null){
            return false;
        }
        return true;
    }
}
