package com.mvc.devyu.web.frontcontroller.v1;

import com.mvc.devyu.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.mvc.devyu.web.frontcontroller.v1.controller.MemberListControllerV1;
import com.mvc.devyu.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// frontController 에서 모든 요청을 받아 각각의 controller의 url에 매핑시킨다.
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private static Map<String, ControllerV1> controllerV1Map = new HashMap<>();

    static  {
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV1 controller = controllerV1Map.get(requestURI); // 인터페이스 다형성 활용

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request, response);

    }
}
