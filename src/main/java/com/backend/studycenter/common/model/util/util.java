package com.backend.studycenter.common.model.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class util {
    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getClassName() + "." + Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public static String getFullUri(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String query = request.getQueryString();
        return query == null ? uri : uri + "?" + query;
    }

    public static void sendResponse(HttpServletResponse response, String message, HttpStatus httpStatus, String URL) throws IOException {
        response.setStatus(httpStatus.value());
        response.setContentType("application/json");
        int errorCode = httpStatus.value();
        String errorMessage = message;
        String responseBody = "{\"timestamp\":\"" + LocalDateTime.now() + "\",\"status\":" + errorCode + ",\"error\":\"" + errorMessage + "\",\"path\":\"" + URL + "\"}";
        response.getWriter().write(responseBody);
    }
}
