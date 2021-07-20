package com.mvc.devyu.web.frontcontroller.v3.controller;

import com.mvc.devyu.domain.member.Member;
import com.mvc.devyu.domain.member.MemberRepository;
import com.mvc.devyu.web.frontcontroller.ModelView;
import com.mvc.devyu.web.frontcontroller.v3.ControllerV3;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) throws ServletException, IOException {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member savedMember = memberRepository.save(new Member(username, age));
        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", savedMember);
        return mv;
    }

}
