package com.company.innowise.airticketsapp.businessservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String pathInfo = request.getServletPath();

        if (!pathInfo.startsWith("/api/auth/") || pathInfo.equals("/api/auth/refresh")) {
            try {
                String token = request.getHeader(HttpHeaders.AUTHORIZATION);
                String accessToken = token.split(" ")[1];
                boolean isAccess = true;
                if (pathInfo.equals("/api/auth/refresh")) {
                    isAccess = false;
                }
                UserDetails userDetails = jwtUtils.verifyToken(accessToken, isAccess);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                log.warn("USER IS NOT AUTHENTICATED");
            }
        }

        filterChain.doFilter(request, response);
    }

}
