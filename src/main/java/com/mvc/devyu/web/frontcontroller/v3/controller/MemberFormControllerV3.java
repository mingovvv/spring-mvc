package com.mvc.devyu.web.frontcontroller.v3.controller;

import com.mvc.devyu.web.frontcontroller.ModelView;
import com.mvc.devyu.web.frontcontroller.v3.ControllerV3;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) throws ServletException, IOException {
        return new ModelView("new-form"); // 논리적인 view name
    }

}
