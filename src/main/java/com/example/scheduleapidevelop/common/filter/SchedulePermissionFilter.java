package com.example.scheduleapidevelop.common.filter;

import com.example.scheduleapidevelop.common.Const;
import com.example.scheduleapidevelop.model.dto.UserResponseDto;
import com.example.scheduleapidevelop.model.entity.Schedule;
import com.example.scheduleapidevelop.repository.ScheduleRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

/**
 * 해당 경로의 일정을 수정및 삭제할 수 있는 사용자인지 확인하는 필터
 */
@RequiredArgsConstructor
public class SchedulePermissionFilter implements Filter {

    private final ScheduleRepository scheduleRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                throws IOException, ServletException {

        // 다양한 기능을 사용하기 위해 다운 캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();
        String requestMethod = httpRequest.getMethod();

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // 사용자 확인을 해야하는 URL 및 요청인지 검사
        if (requestURI.matches("/schedules/\\d+")
                && (requestMethod.equals("PUT") || requestMethod.equals("DELETE"))) {

            // 세션 가져오기 (false: 이때 세션이 존재하지 않아도 새로 생성 X - 즉, null 반환)
            HttpSession session = httpRequest.getSession(false);

            Object sessionAttribute = session.getAttribute(Const.LOGIN_USER);
            Long sessionUserId = ((UserResponseDto) sessionAttribute).getId();

            Long pathId = Long.parseLong(requestURI.substring(requestURI.lastIndexOf("/") + 1));

            Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(pathId);
            Long pathUserId = findSchedule.getUser().getId();

            // 본인 외의 유저 정보를 수정 및 삭제하려고 할 때 예외 처리
            if (!sessionUserId.equals(pathUserId)) {
                httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                httpResponse.setContentType("application/json");
                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.getWriter().write("{\"status\": \"403\", \"message\": \"다른 사용자의 일정은 수정 및 삭제할 수 없습니다.\"}");

                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
