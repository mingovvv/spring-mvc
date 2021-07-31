package test.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView reponseViewV1() {
        ModelAndView mv = new ModelAndView("response/hello")
                .addObject("data", "have a nice day :)");

        return mv;
    }

    @RequestMapping("/response-view-v2")
    public String reponseViewV2(Model model) {
        model.addAttribute("data","have a nice day :)");
        return "response/hello"; // view의 논리적 이름
    }

    /**
     * url과 view의 논리적 이름이 같으면 반환 생략 가능 (직관성이 떨어져 추천하지 않음)
     */
    @RequestMapping("/response/hello")
    public void reponseViewV3(Model model) {
        model.addAttribute("data","have a nice day :)");
    }

}
