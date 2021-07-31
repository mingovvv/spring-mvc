package test.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import test.springmvc.basic.HelloData;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        // byte 코드인 stream을 문자로 받을 때는 항상 charset를 설정해 주어야 함
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = [{}]", messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("helloData = [{}]", helloData);
        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String body) throws IOException {
        log.info("messageBody = [{}]", body);
        HelloData helloData = objectMapper.readValue(body, HelloData.class);
        log.info("helloData = [{}]", helloData);
        return "ok";
    }

    /**
     * HttpMessageConverter가 객체로 변환해줌 (jackson)
     *
     * @RequestBody 는 생략이 되면 쿼리파라미터를 조회하는 @ModelAttribute로 인식되기 때문에 생략이 될 수 없음
     *
     *  [참고]
     *   - @RequestParam 생략 - String, int, Integer 같은 단순 기본 타입
     *   - @ModelAttribute 생략 - argument resolver로 지정한 타입 외 전체
     */
    @ResponseBody
    @RequestMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        log.info("helloData = [{}]", helloData);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> data) {
        log.info("helloData = [{}]", data.getBody());
        return "ok";
    }

    /**
     * 반환도 객체로
     * HttpMessageConverter가 req 뿐만 아니라 res 시에도 동작함을 알 수 있다.
     *
     * req = json -> HelloData obj 변환
     * res = HelloData obj -> json 변환
     */
    @ResponseBody
    @RequestMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) {
        log.info("helloData = [{}]", helloData);
        return helloData;
    }


}
