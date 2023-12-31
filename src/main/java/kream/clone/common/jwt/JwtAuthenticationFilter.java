package kream.clone.common.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if(uri.contains("api/auth/sign-up") || uri.contains("api/auth/login")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = jwtUtil.resolveToken(request, JwtUtil.AUTHORIZATION_ACCESS);

        if (token == null){
            filterChain.doFilter(request, response);
            return;
        }

        jwtUtil.validateAccessToken(request, response);
        Claims info = jwtUtil.getUserInfoFromToken(token,false);
        setAuthentication(info.getSubject());
        filterChain.doFilter(request,response);
    }
    private void setAuthentication(String username) {

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = jwtUtil.createAuthentication(username);
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }
}
