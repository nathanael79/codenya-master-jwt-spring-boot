package com.kuliah.restonline.controller.auth;

import com.kuliah.restonline.security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/login")
public class Login {

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping
    public HashMap<String, String> login(@RequestBody HashMap<String, String> user)
    {
//        user.get();
        HashMap<String, String> body = new HashMap<>();
        body.put("AccessToken", jwtUtils.generateToken(user.get("username")));
        return body;
    }
}
