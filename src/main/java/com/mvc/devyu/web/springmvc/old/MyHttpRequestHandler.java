package com.mvc.devyu.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  HandlerMapping 우선 순위
 *   - 1. RequestMappingHandlerMapping
 *     2. BeanNameUrlHandlerMapping <- 스프링 빈의 이름을 이용하여 핸들러를 찾는 방식
 *  HandlerAdapter 우선 순위
 *   - 1. RequestMappingHandlerAdapter
 *     2. HttpRequestHandlerAdapter <- HttpRequestHandler 인터페이스
 *     3. SimpleControllerHandlerAdapter
 *
 *  HandlerMapping 우선순서대로 실행해서 핸들러를 찾는다.
 *  DispatcherServlet은 HandlerAdapter 인터페이스 구현객체를 순회하면서 supports()를 호출하면서 대상을 찾는다.
 *  supports()를 통해 발견된 대상에게 핸들러 정보도 함께 넘겨주며 내부 실행된다.
 */
@Component("/springmvc/request-handler")
public class MyHttpRequestHandler implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest");
    }

}
