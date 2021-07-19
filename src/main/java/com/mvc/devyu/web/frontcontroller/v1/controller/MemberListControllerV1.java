package com.mvc.devyu.web.frontcontroller.v1.controller;

import com.mvc.devyu.domain.member.Member;
import com.mvc.devyu.domain.member.MemberRepository;
import com.mvc.devyu.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV1 implements ControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> members = memberRepository.findAll();

        // Model 데이터 보관한다.
        request.setAttribute("members", members);

        String viewPath = "/WEB-INF/views/members.jsp"; // 절대경로 확인 필수
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }

}
