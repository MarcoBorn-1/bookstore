package com.example.bookstore.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.bookstore.entity.Customer;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.CustomerManager;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import static com.example.bookstore.config.SecurityConstants.*;

@AllArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final CustomerManager userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        assert authentication != null;
        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(
                        req
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    // Reads the JWT from the Authorization header, and then uses JWT to validate the token
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();

            if (user != null) {
                UserDetails userObj = userRepository.loadUserByUsername(user);

                if (userObj != null) {
                    return new UsernamePasswordAuthenticationToken(userObj, null, userObj.getAuthorities());
                }
                System.out.println(3);
                // new arraylist means authorities

            }
            System.out.println(4);
            return null;
        }

        return null;
    }
}