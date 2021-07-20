package com.mvc.devyu.web.frontcontroller.v4;

import com.mvc.devyu.web.frontcontroller.ModelView;
import com.mvc.devyu.web.frontcontroller.MyView;
import com.mvc.devyu.web.frontcontroller.v3.ControllerV3;
import com.mvc.devyu.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.mvc.devyu.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.mvc.devyu.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// frontController 에서 모든 요청을 받아 각각의 controller의 url에 매핑시킨다.
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private static Map<String, ControllerV4> controllerV1Map = new HashMap<>();

    static  {
        controllerV1Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV1Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerV1Map.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV4 controller = controllerV1Map.get(requestURI); // 인터페이스 다형성 활용

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);
        MyView myView = viewRevsolver(viewName);
        myView.render(model, request, response);

    }

    private MyView viewRevsolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

}
