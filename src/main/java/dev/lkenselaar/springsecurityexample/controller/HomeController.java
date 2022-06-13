package dev.lkenselaar.springsecurityexample.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER')")
    public String user() {
        return "Welcome user";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String admin() {
        return "Welcome admin";
    }
}
