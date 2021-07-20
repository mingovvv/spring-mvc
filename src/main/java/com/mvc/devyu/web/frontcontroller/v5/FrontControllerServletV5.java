package com.mvc.devyu.web.frontcontroller.v5;

import com.mvc.devyu.web.frontcontroller.ModelView;
import com.mvc.devyu.web.frontcontroller.MyView;
import com.mvc.devyu.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.mvc.devyu.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.mvc.devyu.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.mvc.devyu.web.frontcontroller.v4.ControllerV4;
import com.mvc.devyu.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.mvc.devyu.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.mvc.devyu.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import com.mvc.devyu.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import com.mvc.devyu.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// frontController 에서 모든 요청을 받아 각각의 controller의 url에 매핑시킨다.
@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private static Map<String, Object> handlerMappiongMap = new HashMap<>();
    private static List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    static  {
        handlerMappiongMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappiongMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappiongMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappiongMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappiongMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappiongMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());

        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        ModelView mv = adapter.handle(request, response, handler);

        MyView myView = viewRevsolver(mv.getViewName());
        myView.render(mv.getModel(), request, response);

    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)) {
                return adapter;
            }
        }

        throw new IllegalArgumentException("handler adapter is missing, " + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappiongMap.get(requestURI);
    }

    private MyView viewRevsolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
