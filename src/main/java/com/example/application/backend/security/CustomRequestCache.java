package com.example.application.backend.security;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Request Cache is required to save all the incoming requests (link) which are made before login
public class CustomRequestCache extends HttpSessionRequestCache {
    @Override
    public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
        if (!SecurityUtils.isFrameworkInternalRequest(request)) {
            super.saveRequest(request, response);
        }
    }

}
