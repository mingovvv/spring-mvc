package com.mvc.devyu.web.sevletMvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // /WEB-INF 하위경로의 JSP파일은 URL로 직접 경로 호출이 되지 않는다.
        String viewPath = "/WEB-INF/views/new-form.jsp";

        // Controller -> View 이동
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);

        // servlet 에서 JSP를 호출할 수 있음
        dispatcher.forward(request, response);

    }

}
