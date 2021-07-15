package com.mvc.devyu.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.devyu.basic.HelloData;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/ ")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper ob = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Content-Type : application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("Devyu");
        helloData.setAge(32);

        String objectString = ob.writeValueAsString(helloData);

        response.getWriter().write(objectString);

    }

}
