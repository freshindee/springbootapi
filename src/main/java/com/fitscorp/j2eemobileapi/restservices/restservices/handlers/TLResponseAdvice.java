package com.fitscorp.j2eemobileapi.restservices.restservices.handlers;

import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class TLResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response) {
    	if (body instanceof Resource || body instanceof ApiError || body instanceof RestResponse) {
            return body;
        }
        final RestResponse<Object> output = new RestResponse<>();
        output.setBody(body);
        output.setErrorMessages(null);
        int status = HttpStatus.OK.value();
        if (response instanceof ServletServerHttpResponse) {
            status = ((ServletServerHttpResponse) response).getServletResponse().getStatus();
        }
        response.setStatusCode(HttpStatus.OK);
        output.setStatus(HttpStatus.valueOf(status).value());
        return output;
    }
}