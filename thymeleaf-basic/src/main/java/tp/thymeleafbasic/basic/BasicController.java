package tp.thymeleafbasic.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hellooooooooo !");
        return "basic/text-basic";
    }

    /**
     * HTML문서는 '<', '>' 와 같은 특수 문자를 기반으로 정의 되기 때문에 뷰 템플릿으로  HTML 화면을
     * 생성할 때 출력하는 데이터의 값을 확인하여야 함. ( < - &lt; /  > - &gt; 변경)
     * (HTML 엔티티)
     *
     * @param model
     * @return
     */
    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "<b>Hellooooooooo !</b>");
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        ArrayList<User> list = new ArrayList<>(Arrays.asList(new User("userA", 10), new User("userB", 20)));
        Map<String, User> map = Map.of("userA", new User("userA", 10), "userB", new User("userB", 20));

        model.addAttribute("user", new User("userA", 10));
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObject(HttpSession httpSession) {
        httpSession.setAttribute("sessionData", "SESSION TEST");
        return "basic/basic-objects";
    }

    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("param1", "date1");
        model.addAttribute("param2", "date2");
        return "basic/link";
    }

    @GetMapping("/literal")
    public String literal(Model model) {
        model.addAttribute("data", "Spring!");
        model.addAttribute("param2", "date2");
        return "basic/literal";
    }

    @GetMapping("/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }

    @Component("helloBean")
    static class TestBean {
        public String hello(String data) {
            return "BEAN TEST" + data;
        }
    }

    @Data
    @AllArgsConstructor
    static class User {
        private String username;
        private int age;
    }

}
