package com.mvc.devyu.web.frontcontroller.v2;

import com.mvc.devyu.web.frontcontroller.v2.controller.MemberFormControllerV2;
import com.mvc.devyu.web.frontcontroller.v2.controller.MemberListControllerV2;
import com.mvc.devyu.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// frontController 에서 모든 요청을 받아 각각의 controller의 url에 매핑시킨다.
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private static Map<String, ControllerV2> controllerV1Map = new HashMap<>();

    static  {
        controllerV1Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerV1Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerV1Map.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV2 controller = controllerV1Map.get(requestURI); // 인터페이스 다형성 활용

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request, response).render(request, response);

    }
}
