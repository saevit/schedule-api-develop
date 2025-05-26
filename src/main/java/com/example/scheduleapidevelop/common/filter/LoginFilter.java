package com.example.scheduleapidevelop.common.filter;

import com.example.scheduleapidevelop.common.Const;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

/**
 * 로그인 된 사용자인지 확인하는 필터
 */
public class LoginFilter implements Filter {

    // 인증을 하지 않아도될 URL Path 배열
    private static final String[] WHITE_LIST = {"/users/signup", "/users/login"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                throws IOException, ServletException {

        // 다양한 기능을 사용하기 위해 다운 캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // 로그인을 체크 해야하는 URL인지 검사
        if (!isWhiteList(requestURI)) {

            // 세션 가져오기 (false: 이때 세션이 존재하지 않아도 새로 생성 X - 즉, null 반환)
            HttpSession session = httpRequest.getSession(false);

            // 로그인하지 않은 사용자인 경우 예외 처리
            if (session == null || session.getAttribute(Const.LOGIN_USER) == null) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.setContentType("application/json");
                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.getWriter().write("{\"status\": \"401\", \"message\": \"로그인 후 이용해주세요.\"}");

                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    // 로그인 여부를 확인하는 URL인지 체크하는 메서드
    private boolean isWhiteList(String requestURI) {

        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
