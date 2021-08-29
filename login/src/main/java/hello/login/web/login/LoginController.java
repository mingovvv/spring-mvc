package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "login/loginForm";
    }

    /**
     * cookie
     */
//    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        // global error
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        // 쿠키에 시간 정보를 넣지 않으면 세션 쿠키(브라우저 종료시 쿠키 만료)
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);

        return "redirect:/";

    }

    /**
     * cookie -> session manager
     */
//    @PostMapping("/login")
    public String loginV2(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        // global error
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        // 세션 관리자를 통해 세션을 관리하고, 회원 데이터를 보관
        sessionManager.createSession(loginMember, response);
        return "redirect:/";

    }

    /**
     * http servlet session
     */
    @PostMapping("/login")
    public String loginV3(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        // global error
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        // http servlet 에서 제공하는 세션관리자를 사용
        HttpSession session = request.getSession();
        // request.getSession(true), default : 세션이 있으면 세션 반환 / 없으면 신규 생성
        // request.getSession(false) : 세션이 있으면 세션 반환 / 없으면 null 반환
        // ** 사용자의 request 정보를 가지고 key를 생성. 해당 *key*를 클라이언트를 위해 쿠키에 담아 응답까지 하는 역할

        // ** 세션에 로그인 회원 정보를 보관 : 세션에 저장하는 것 : 생성한 key에 value를 매핑하는 것 : "login-member" 는 단순 상수값이며 일종의 폴더이름 같은 것(로그인 맴버를 위한 상수값이구나 !)
        session.setAttribute("login-member", loginMember);

        return "redirect:/";

    }

    /**
     *  cookie
     */
//    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        expiredCookie(response, "memberId");
        return "redirect:/";
    }

    /**
     *  cookie -> session
     */
//    @PostMapping("/logout")
    public String logoutV2(HttpServletRequest request) {
        sessionManager.expired(request);
        return "redirect:/";
    }

    /**
     *  http servlet session
     */
    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    private void expiredCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0); // 만료처리
        response.addCookie(cookie);
    }
}