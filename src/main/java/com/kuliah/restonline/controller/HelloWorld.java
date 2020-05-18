package com.kuliah.restonline.controller;

import com.kuliah.restonline.model.Hello;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorld {

    @GetMapping("/guest")
    public Hello getHello()
    {
        return new Hello("world");
    }

    @GetMapping("/user")
    @Secured("USER")
    public Hello getSecuredHello()
    {
        return new Hello("secured user");
    }
}
