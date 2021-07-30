package com.mvc.devyu.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Controller spring bean 등록
 * spring MVC에서 애노테이션 기반 컨트롤러로 인식함
 *
 * @Controller = @RequestMapping + @Component
 * 'RequestMappingHandlerMapping' 은 스프링 빈 중에서 @RequestMapping 또는 @Controller가 class level에 붙어 있는 경우에 매핑 정보로 인식한다.
 */
@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/member/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }

}
