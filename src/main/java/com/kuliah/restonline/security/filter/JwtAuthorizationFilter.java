package com.kuliah.restonline.security.filter;

import com.kuliah.restonline.security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Security;
import java.util.*;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private JwtUtils jwtUtils = new JwtUtils();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = httpServletRequest.getHeader("Authorization").replace("Bearer ","");
//            System.out.println(jwt);
            if(jwtUtils.validateToken(jwt)) {
                String username = jwtUtils.getSubject(jwt);
                Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
                grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
                User user = new User(username,"", grantedAuthorities);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
//            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
