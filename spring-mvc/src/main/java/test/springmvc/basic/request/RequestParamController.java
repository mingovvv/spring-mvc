package test.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import test.springmvc.basic.HelloData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

@Slf4j
@RestController
public class RequestParamController {

    /**
     * 쿼리 파라미터 요청과 HTML Form 요청을 동시에 받을 수 있음.
     *   - 쿼리 파라미터 : url?username=dev&age=70
     *   - HTML Form : header = [content-type:application/x-www-form-urlencoded] body = [username=dev&age=70]
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username = [{}], age = [{}]", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String username, @RequestParam("age") int age) {
        log.info("username = [{}], age = [{}]", username, age);
        return "ok";
    }

    /**
     * @RequestParam name 생략 가능 (파라미터 이름과 변수 이름이 같아야 함)
     *
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, @RequestParam int age) {
        log.info("username = [{}], age = [{}]", username, age);
        return "ok";
    }

    /**
     * @RequestParam 생략 가능 (파라미터 이름과 변수 이름이 같아야 함)
     *
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username = [{}], age = [{}]", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(value = "username", required = true) String username, @RequestParam(value = "age", required = false) Integer age) {
        log.info("username = [{}], age = [{}]", username, age);
        return "ok";
    }

    /**
     * defaultValue가 설정되면 required = true 인 파라미터가 오지 않아도 error 발생하지 않음
     * defaultValue가 설정되면 null 뿐만아니라 ""(빈문자)의 경우에도 설정 값이 인식됨
     */
    @ResponseBody
    @RequestMapping("/request-param-dafault")
    public String requestParamDafaultValue(@RequestParam(value = "username", required = true, defaultValue = "min") String username, @RequestParam(value = "age", required = false, defaultValue = "30") Integer age) {
        log.info("username = [{}], age = [{}]", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamDafaultValue(@RequestParam Map<String, Object> map) {
        log.info("username = [{}], age = [{}]", map.get("username"), map.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@RequestParam String username, @RequestParam int age) {
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);
        log.info("helloDate = [{}]", helloData);
        return "ok";
    }

    /**
     * 요청 파라미터의 이름으로 객체의 프로퍼티를 찾는다. 해당 프로퍼티의 setter를 호출해서 전달받은 파라미터의 값을 바인딩 해줌
     *
     * 프로퍼티? : 객체의 필드가 게터와 세터 메서드가 존재하면 해당 필드는 프로퍼티를 가지고 있다고 표현함
     *
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1.5")
    public String modelAttributeV15(@ModelAttribute HelloData helloData) {
        log.info("helloDate = [{}]", helloData);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("helloDate = [{}]", helloData);
        return "ok";
    }

}
