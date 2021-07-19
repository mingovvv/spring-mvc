package com.mvc.devyu.web.frontcontroller.v1.controller;

import com.mvc.devyu.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV1 implements ControllerV1 {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // /WEB-INF 하위경로의 JSP파일은 URL로 직접 경로 호출이 되지 않는다.
        String viewPath = "/WEB-INF/views/new-form.jsp";

        // Controller -> View 이동
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);

        // servlet 에서 JSP를 호출할 수 있음
        dispatcher.forward(request, response);
    }

}
