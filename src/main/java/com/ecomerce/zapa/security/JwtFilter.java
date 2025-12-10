package com.ecomerce.zapa.security;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        String method = request.getMethod();

        // ✅ Permitir preflight CORS
        if ("OPTIONS".equalsIgnoreCase(method)) {
            return true;
        }

        // ✅ Permitir login y registro sin token
        if (path.equals("/api/usuarios/login") && "POST".equalsIgnoreCase(method)) {
            return true;
        }
        if (path.equals("/api/usuarios") && "POST".equalsIgnoreCase(method)) {
            return true;
        }

        // ✅ Permitir catálogos públicos sin token
        return path.startsWith("/api/productos")
            //|| path.startsWith("/api/usuarios")   // ✅ LOGIN, REGISTRO, GET USUARIOS
            || path.startsWith("/api/regiones")
            || path.startsWith("/api/comunas")
            || path.startsWith("/api/roles")
            || path.startsWith("/swagger-ui")
            || path.startsWith("/v3/api-docs");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                String jwt = authHeader.substring(7);
                String email = jwtService.extractUsername(jwt);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(email, null, null);

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }
}
