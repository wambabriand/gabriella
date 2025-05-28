package com.example.demo.jwt;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain filterChain)
	        throws ServletException, IOException {

		System.out.println("Je suis ciicic 1");
	    String path = request.getServletPath();

		System.out.println("Je suis ciicic 2");
	   
	    if (path.equals("/register") || path.equals("/login") || path.equals("/init")) {

			System.out.println("Je suis ciicic 3");
			//response.set
	        filterChain.doFilter(request, response);
	        return;
	    }


		System.out.println("Je suis ciicic 4");
	    String authHeader = request.getHeader("Authorization");

	    if (authHeader != null && authHeader.startsWith("Bearer ")) {
	        String token = authHeader.substring(7);
	        if (JwtUtil.validateToken(token)) {
	            String username = JwtUtil.extractUsername(token);
	            String role = JwtUtil.extractRole(token);

	            var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
	            var auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
	            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	            SecurityContextHolder.getContext().setAuthentication(auth);
	        }
	    }

	    filterChain.doFilter(request, response);
	}
	
}
