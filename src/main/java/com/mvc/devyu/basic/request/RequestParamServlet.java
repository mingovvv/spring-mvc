package com.mvc.devyu.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 파라미터 전송 기능
 *
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회]");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));
        System.out.println();

        System.out.println("[단일 파라미터 조회]");
        System.out.println("username = " + request.getParameter("username"));
        System.out.println("age = " + request.getParameter("age"));
        System.out.println();

        System.out.println("[복수 파라미터 KEY를 통한 VAULE 조회]");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println(name);
        }
        System.out.println();

        response.getWriter().write("ok");

    }

}
