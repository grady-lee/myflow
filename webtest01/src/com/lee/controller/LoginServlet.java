package com.lee.controller;

import com.lee.dto.LoginRequestDTO;
import com.lee.dto.LoginResponseDTO;
import com.lee.service.UserService;
import com.lee.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        Map<String, String[]> parameterMap = req.getParameterMap();
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        try {
            BeanUtils.populate(loginRequestDTO, parameterMap);
            LoginResponseDTO login = userService.login(loginRequestDTO);
            resp.getWriter().println(login.toString());
        } catch (IllegalAccessException | InvocationTargetException e) {
            resp.getWriter().println(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
