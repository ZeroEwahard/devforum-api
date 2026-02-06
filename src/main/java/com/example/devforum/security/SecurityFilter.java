package com.example.devforum.security;

import com.example.devforum.repository.UsuarioRepository;
import com.example.devforum.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    private final TokenService tokenService;
    private final UsuarioService usuarioService;

    public SecurityFilter(TokenService tokenService, UsuarioService usuarioService) {
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        var header = request.getHeader("Authorization");
        String token = null;
        String usuario = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            if (tokenService.validarToken(token)) {
                usuario = tokenService.extrairUsuario(token);
            }
        }

        if (usuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = usuarioService.loadUserByUsername(usuario);

            if (tokenService.validarToken(token)) {
                UsernamePasswordAuthenticationToken autenticar =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                autenticar.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(autenticar);
            }
        }

        filterChain.doFilter(request, response);
    }
}
