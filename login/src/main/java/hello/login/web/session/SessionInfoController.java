package hello.login.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
public class SessionInfoController {

    @GetMapping("/session-info")
    public String getSessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "session is empty";
        }

        // session data output
        session.getAttributeNames()
                .asIterator()
                .forEachRemaining(name -> log.info("session name = {}, value = {}", name, session.getAttribute(name)));

        log.info("session id = {}", session.getId());
        log.info("session max inactive interval = {}", session.getMaxInactiveInterval());
        log.info("session creation time = {}", new Date(session.getCreationTime()));
        log.info("session last accessed time = {}", new Date(session.getLastAccessedTime()));
        log.info("session is new = {}", session.isNew());

        return "session is fully";



    }

}
