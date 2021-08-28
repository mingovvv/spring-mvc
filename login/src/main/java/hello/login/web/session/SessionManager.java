package hello.login.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  세션 관리
 */
@Component
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "MySessionId";
    private Map<String, Object> sessionStore = new ConcurrentHashMap();

    /**
     *  세션 생성
     *  1. sessionId 생성
     *  2. 세션 저장소에 seesionId와 보관할 값 저장
     *  3. seesionId로 응답 쿠키를 생성에서 클라이언트에게 전달
     */
    public void createSession(Object value, HttpServletResponse response) {

        // 1. sessionId 생성
        String seesionId = UUID.randomUUID().toString();
        sessionStore.put(seesionId, value);

        // 2. 3. 쿠키 생성 및 응답값 설정
        response.addCookie(new Cookie(SESSION_COOKIE_NAME, seesionId));

    }

    /**
     * 세션 조회
     */
    public Object getSession(HttpServletRequest request) {

        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);

        if (sessionCookie == null) {
            return null;
        }

        return sessionStore.get(sessionCookie.getValue());

    }

    private Cookie findCookie(HttpServletRequest request, String cookieName) {

        if (request.getCookies() == null) {
            return null;
        }

        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }

    /**
     * 세션 만료
     */
    public void expired(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie != null) {
            sessionStore.remove(sessionCookie.getValue());
        }
    }

}
