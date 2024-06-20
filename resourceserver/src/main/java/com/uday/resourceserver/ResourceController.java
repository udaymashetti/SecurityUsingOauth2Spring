package com.uday.resourceserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from resource server";
    }
}
