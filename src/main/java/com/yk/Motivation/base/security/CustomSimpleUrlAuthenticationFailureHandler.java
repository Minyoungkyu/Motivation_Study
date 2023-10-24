package com.yk.Motivation.base.security;

import com.yk.Motivation.standard.util.Ut;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

public class CustomSimpleUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler { // Spring Security 의 로그인 과정 중 실패 핸들러
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // http 요청(post) 에서 username 값을 뽑아냄.
        String username = request.getParameter("username");

        // exception 이 BadCredentialException 이면 비밀번호가 틀린거다. ( 아이디는 UsernameNotFoundException )
        String failMsg = exception instanceof BadCredentialsException ? "비밀번호가 일치하지 않습니다." : "존재하지 않는 회원입니다.";

        // redirect 될 url
        setDefaultFailureUrl("/usr/member/login?lastUsername=" + username + "&failMsg=" + Ut.url.encodeWithTtl(failMsg));

        super.onAuthenticationFailure(request, response, exception); // redirect
    }
}