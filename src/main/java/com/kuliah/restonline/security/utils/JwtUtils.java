package com.kuliah.restonline.security.utils;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtUtils {
    private String JWTSECRET = "ajsdioasjdiaiodsjaiojdioasjd";

    public String generateToken(String username)
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("USERNAME",username);
        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
        return Jwts.builder().setClaims(map).setSubject(username).setIssuedAt(today).setExpiration(tomorrow).signWith(SignatureAlgorithm.HS256, JWTSECRET).compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(JWTSECRET).parseClaimsJws(token);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public String getSubject(String token){
        return Jwts.parser().setSigningKey(JWTSECRET).parseClaimsJws(token).getBody().getSubject();
    }
}
