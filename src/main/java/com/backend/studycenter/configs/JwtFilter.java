package com.backend.studycenter.configs;

import static com.backend.studycenter.common.model.util.util.sendResponse;

import com.backend.studycenter.common.service.security.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;
    @Value("${jwt.secret}")
    private String secret;

    public JwtFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String endpoint = request.getRequestURI();
        if (endpoint.equals("/api/v1/users/login") || endpoint.contains("swagger") || endpoint.startsWith("/v3/") || endpoint.startsWith("/favicon.ico")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7); // Remove "Bearer " prefix

            try {
                Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody();
                Date expirationDate = claims.getExpiration();
                Date now = new Date();

                if (expirationDate != null && now.before(expirationDate)) {
                    String username = claims.getSubject();
                    UserDetails userData = userService.loadUserByUsername(username);
                    if (userData == null) {
                        sendResponse(response, "User not found", HttpStatus.NOT_FOUND, endpoint);
                        return;
                    }

                    String allowErrorMessage = allowUserToEndpoint(userData, request);

                    if (allowErrorMessage.equals("allow")) {
                        filterChain.doFilter(request, response);
                    } else {
                        sendResponse(response, allowErrorMessage, HttpStatus.FORBIDDEN, endpoint);
                    }
                } else {
                    sendResponse(response, "Token expired", HttpStatus.UNAUTHORIZED, endpoint);
                }
            } catch (Exception e) {
                sendResponse(response, "Invalid token", HttpStatus.UNAUTHORIZED, endpoint);
            }
        } else {
            sendResponse(response, "No JWT token found", HttpStatus.UNAUTHORIZED, endpoint);
        }
    }

    private String allowUserToEndpoint(UserDetails userData, HttpServletRequest request) {
        String endpoint = request.getRequestURI();
        String requestMethodType = request.getMethod();
        List<String> roles = userData.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if (roles.isEmpty()) {
            return "User does not have any role";
        }

        if (roles.contains("ADMIN")) {
            return "allow";
        }

        if (requestMethodType.equals("DELETE")) {
            return "DELETE endpoints are allowed to ADMIN only";
        }

        if (endpoint.contains("/payment/")) {
            if (roles.contains("PAYMENT")) {
                return "allow";
            } else {
                return "Payment endpoints are allowed to PAYMENT role only";
            }
        }

        if (roles.contains("TEACHER") && (endpoint.contains("/teacher/") || endpoint.contains("/edu/"))) {
            return "allow";
        }

        if (roles.contains("STUDENT") && (endpoint.contains("/student/") || endpoint.contains("/edu/"))) {
            return "allow";
        }

        if (roles.contains("ASSISTANT") && endpoint.contains("/assist/")) {
            return "allow";
        }

        if (roles.contains("EDITOR")) {
            return "allow";
        }

        if (requestMethodType.equals("POST") || requestMethodType.equals("PUT")) {
            return "POST&PUT request types are allowed only EDITOR role";
        }

        if (roles.contains("VIEWER")) {
            return "allow";
        }

        return "Restricted. Not registered role";
    }
}