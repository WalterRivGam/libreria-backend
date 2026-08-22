package com.libreria.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.libreria.dto.ErrorDto;
import com.libreria.service.impl.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper mapper = new ObjectMapper();

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);

        try {
            username = jwtService.extractUsername(jwt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
        } catch (ExpiredJwtException e) {
            manejarErrorFiltro(response, request, HttpStatus.UNAUTHORIZED,
                    "El token de acceso ha expirado. Refresque su sesión.");
        } catch (SignatureException | MalformedJwtException e) {
            manejarErrorFiltro(response, request, HttpStatus.UNAUTHORIZED, "El token es inválido o ha sido alterado.");
        } catch (Exception e) {
            manejarErrorFiltro(response, request, HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error procesando la autenticación.");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void manejarErrorFiltro(HttpServletResponse response, HttpServletRequest request, HttpStatus status,
            String mensaje)
            throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");

        ErrorDto error = new ErrorDto();
        error.setTimestamp(LocalDateTime.now());
        error.setStatus(status.value());
        error.setError(status.getReasonPhrase());
        error.setMensaje(mensaje);
        error.setPath(request.getRequestURI());

        response.getWriter().write(mapper.writeValueAsString(error));
    }
}