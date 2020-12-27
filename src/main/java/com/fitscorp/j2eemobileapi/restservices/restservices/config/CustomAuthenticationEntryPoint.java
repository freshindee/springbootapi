package com.fitscorp.j2eemobileapi.restservices.restservices.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fitscorp.j2eemobileapi.restservices.restservices.handlers.ApiError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(401);
        Gson gson = new Gson();
        res.getWriter().write(gson.toJson(new ApiError(401, "", 
        		Arrays.asList("Authorization failed for the requested resource"))));
    }
}