package com.mvc.devyu.web.frontcontroller.v3;

import com.mvc.devyu.web.frontcontroller.ModelView;
import com.mvc.devyu.web.frontcontroller.MyView;
import com.mvc.devyu.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.mvc.devyu.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.mvc.devyu.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// frontController 에서 모든 요청을 받아 각각의 controller의 url에 매핑시킨다.
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private static Map<String, ControllerV3> controllerV1Map = new HashMap<>();

    static  {
        controllerV1Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV1Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV1Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerV1Map.get(requestURI); // 인터페이스 다형성 활용

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);

        ModelView mv = controller.process(paramMap);
        MyView myView = viewRevsolver(mv);
        myView.render(mv.getModel(), request, response);

    }

    private MyView viewRevsolver(ModelView mv) {
        return new MyView("/WEB-INF/views/" + mv.getViewName() + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

}
