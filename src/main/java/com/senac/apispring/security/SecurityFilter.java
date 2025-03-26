package com.senac.apispring.security;



import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.senac.apispring.repository.UserRepository;
import com.senac.apispring.services.TokenServices;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);


    @Autowired
    private TokenServices tokenServices;

    @Autowired
    private UserRepository repository;
    private static final List<String> PUBLIC_ROUTES = List.of("/auth/login", "/auth/register");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

    	if (PUBLIC_ROUTES.stream().anyMatch(requestURI::startsWith)) {
              filterChain.doFilter(request, response);
              return;
          }
    	String tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            try {
                String subject = tokenServices.getSubject(tokenJWT);
                var usuario = repository.findByEmail(subject);

                if (usuario != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(usuario, null);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    return;
                } else {
                    logger.warn("Usuário não encontrado: {}", subject);
                }
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Erro na autenticação do token");
                return;
            }
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Erro na autenticação do token");
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        logger.info("Token recebido do header Authorization: {}", authorizationHeader);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
