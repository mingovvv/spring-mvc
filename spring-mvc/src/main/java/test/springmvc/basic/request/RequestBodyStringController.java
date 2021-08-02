package test.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import test.springmvc.basic.HelloData;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@RestController
public class RequestBodyStringController {

    @RequestMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        // byte 코드인 stream을 문자로 받을 때는 항상 charset를 설정해 주어야 함
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBodu = [{}]", messageBody);
        response.getWriter().write("ok");
    }

    @RequestMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer writer) throws IOException {
        // byte 코드인 stream을 문자로 받을 때는 항상 charset를 설정해 주어야 함
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBodu = [{}]", messageBody);
        writer.write("ok");
    }

    /**
     * HttpEntity -> message converter 동작
     * HttpEntity ? : header 및 body 를 조회할 수 있음.
     * 요청파라미터를 조회하는 기능과 관계없음 (@RequsetParam / @ModelAttribute X)
     *
     */
    @RequestMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String body = httpEntity.getBody();
        log.info("messageBodu = [{}]", body);
        return new HttpEntity<>("ok");
    }

    /**
     * header 정보가 필요하다면 @ReqeustHeader("host") 를 사용하거나, HttpEntity를 사용하면 됨
     *
     * message body를 조회하는 기능은 요청 파라미터를 조회하는 @RequestParam과 @ModelAttribute와는 전혀 관계가 없음음
     */
    @ResponseBody
    @RequestMapping("/request-body-string-v4")
    public String  requestBodyStringV4(@RequestBody String messageBody) throws IOException {
        log.info("messageBodu = [{}]", messageBody);
        return "ok";
    }

}
