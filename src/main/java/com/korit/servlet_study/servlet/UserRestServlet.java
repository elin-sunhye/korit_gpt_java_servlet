package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/user")
public class UserRestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = User.builder()
                .username("test")
                .password("123")
                .name("테스트")
                .email("test@gmail.com")
                .build();

        String jsonUser = objectMapper.writeValueAsString(user);
        System.out.println(jsonUser);

//        Origin = 출처 대상(서버) : http://localhost:3000
//        * : 전체
        resp.setHeader("Access-Control-Allow-Origin", "*");

//        "POST, GET, OPTIONS" 이외 요청은 받지 않겠다 (delete..등)
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");

//        Content-Type 으로 셋팅해서 데이터를 주겠다
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");

//        쿠키 등 중요 데이터 접금 방지 (true하면 접근 방지)
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        resp.setContentType("application/json");
        resp.getWriter().write(jsonUser);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
