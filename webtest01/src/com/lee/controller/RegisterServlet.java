package com.lee.controller;

import com.lee.dto.RegisterRequestDTO;
import com.lee.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class RegisterServlet extends HttpServlet {

    private final UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Map<String, String[]> parameterMap = req.getParameterMap();
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        try {
            BeanUtils.populate(registerRequestDTO, parameterMap);
            userService.register(registerRequestDTO);
            resp.sendRedirect(req.getContextPath() + "/pages/register_success.html");
        } catch (IllegalAccessException | InvocationTargetException e) {
            resp.sendRedirect(req.getContextPath() + "/pages/register_fail.html");
        }
    }
}
