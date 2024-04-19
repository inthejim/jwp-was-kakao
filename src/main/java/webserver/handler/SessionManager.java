package webserver.handler;

import model.User;
import webserver.http.HttpCookie;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionManager {
    private static final Map<String, Session> SESSIONS = new HashMap<>();

    public void add(final Session session) {
        SESSIONS.put(session.getId(), session);
    }

    public HttpCookie createSession(User user){
        String uuid = UUID.randomUUID().toString();
        Session session = new Session(uuid);
        session.setAttribute("user", user);
        this.add(session);

        return new HttpCookie(uuid);
    }

    public Session findSession(final String id) {
        return SESSIONS.get(id);
    }

    public void remove(final String id) {
        SESSIONS.remove(id);
    }

    public SessionManager() {
    }
}
