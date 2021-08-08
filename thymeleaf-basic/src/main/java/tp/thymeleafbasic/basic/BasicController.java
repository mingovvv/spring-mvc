package tp.thymeleafbasic.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
