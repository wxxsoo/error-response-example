package com.example.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BusinessExceptionResolver implements HandlerExceptionResolver {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if (ex instanceof BusinessException) {
                ErrorCode errorCode = ((BusinessException) ex).getErrorCode();
                Map<String, Object> errorResult = new HashMap<>();
                errorResult.put("status", errorCode.getStatus());
                errorResult.put("code", errorCode.getCode());
                errorResult.put("message", errorCode.getMessage());
                String result =
                        objectMapper.writeValueAsString(errorResult);
                response.setStatus(errorCode.getStatus());
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(result);
                return new ModelAndView();
            }
        } catch (IOException e) {
            log.error("BusinessExceptionResolver ex", e);
        }
        return null;
    }
}
