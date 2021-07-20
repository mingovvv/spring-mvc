package com.mvc.devyu.web.frontcontroller.v3;

import com.mvc.devyu.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

public interface ControllerV3 {

    // servlet 기술로부터 종속적이지 않은 interface (req, res 사용 X)
    ModelView process(Map<String, String> paramMap) throws ServletException, IOException;

}
