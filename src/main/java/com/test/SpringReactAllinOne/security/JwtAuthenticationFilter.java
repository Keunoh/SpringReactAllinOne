package com.test.SpringReactAllinOne.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            //JWT = JSON Web Token
            String jwt = getJwtFromRequest(request);

            //사용자가 로그인이 되었을 때 실행되는 if구간이고
            //헤더가 포함되어야 실행이 되어야한다.
            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)){
                Long userId = jwtTokenProvider.getUserIdFromJWT(jwt);
                UserDetails userDetails = customUserDetailsService.loadUserById(userId);
                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, "myCredentials", userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            log.error("Could not set user authentication in security context, -{}", e);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        /**
         * 이 리턴 부분을 null이 아닌 다른 문자열로 반환 했을 때
         * 헤더의 Bearer를 읽을 수 없기 때문에 오류가 나는데
         * null을 반환했을 때는 오류가 왜 안나는지 알아봐야 한다.
         * 오류가나도 토큰이 생성되기는 한다.
         */
        return null;
    }
}
