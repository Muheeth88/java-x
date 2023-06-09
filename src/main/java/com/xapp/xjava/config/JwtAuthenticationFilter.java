package com.xapp.xjava.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import com.xapp.xjava.utility.JwtUtility;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired 
    private JwtUtility jwtUtility;

    @Autowired
    private CustomUserDetailsService userDetailsService;
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
            String reqTokenHeader = request.getHeader("Authorization");
            String userName = null; 
            String jwtToken = null;

            if(reqTokenHeader != null && reqTokenHeader.startsWith("Bearer ")) {
                jwtToken = reqTokenHeader.substring(7);
                try {
                    userName = this.jwtUtility.getUsernameFromToken(jwtToken);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                UserDetails userDetails= userDetailsService.loadUserByUsername(userName);
                //  Security
                if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    System.out.println("Token Not Valid");
                }
            }
        filterChain.doFilter(request, response); 
    }
}
