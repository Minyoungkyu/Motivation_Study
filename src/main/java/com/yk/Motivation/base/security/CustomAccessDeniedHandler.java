package com.yk.Motivation.base.security;

import com.yk.Motivation.domain.member.exception.EmailNotVerifiedAccessDeniedException;
import com.yk.Motivation.standard.util.Ut;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import java.io.IOException;

public class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl {

    // 굳이 굳이 이렇게 하는건 EmailNotVerifiedAccessDeniedException 으로 exception 을 터쳐도 AccessDeniedException 으로 귀결 되더라...
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (accessDeniedException instanceof EmailNotVerifiedAccessDeniedException) {
            response.sendRedirect("/usr/member/notVerified?msg=" + Ut.url.encodeWithTtl(accessDeniedException.getMessage()));
            return;
        }

        super.handle(request, response, accessDeniedException);
    }
}