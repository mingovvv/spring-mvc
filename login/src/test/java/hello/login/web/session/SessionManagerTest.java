package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {

        // session create
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, mockHttpServletResponse);

        // http request cookie 설정
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setCookies(mockHttpServletResponse.getCookies());

        // session 조회
        Object session = sessionManager.getSession(mockHttpServletRequest);
        Assertions.assertThat(session).isEqualTo(member);

        // session 만료
        sessionManager.expired(mockHttpServletRequest);
        Assertions.assertThat(sessionManager.getSession(mockHttpServletRequest)).isNull();

    }

}