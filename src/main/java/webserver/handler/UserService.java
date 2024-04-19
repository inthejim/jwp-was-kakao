package webserver.handler;

import db.DataBase;
import model.User;

import java.util.Map;

public class UserService {
    public static void addUser(Map<String, String> userParameters) {
        validate(userParameters);
        notDuplicatedUser(userParameters);
        User user = new User(userParameters);
        DataBase.addUser(user);
    }

    public static void notDuplicatedUser(Map<String, String> userParameters){
        if(DataBase.findUserById(userParameters.get("userID"))!=null){
            throw new IllegalArgumentException("같은 아이디가 존재합니다");
        }
    }

    public static void validate(Map<String, String> userParameters) {
        if (userParameters.get("userId") == null || userParameters.get("userId").isEmpty()) {
            throw new IllegalArgumentException("아이디에 대한 입력이 유효하지 않습니다.");
        }

        if (userParameters.get("password") == null || userParameters.get("password").isEmpty()) {
            throw new IllegalArgumentException("비밀번호에 대한 입력이 유효하지 않습니다.");
        }

        if (userParameters.get("name") == null || userParameters.get("name").isEmpty()) {
            throw new IllegalArgumentException("이름에 대한 입력이 유효하지 않습니다.");
        }

        if (userParameters.get("email") == null || userParameters.get("email").isEmpty()) {
            throw new IllegalArgumentException("이메일에 대한 입력이 유효하지 않습니다.");
        }
    }
}
