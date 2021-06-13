package ru.geekbrains.spring.first.app.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    // /sum?a11=10&b=20
    @GetMapping("/sum")
    public int getSum(@RequestParam(name = "a11", defaultValue = "0") int a, int b) {
        return a + b;
    }

    // /users/1
    @GetMapping("/users/{id}")
    public String getUser(@PathVariable Long id) {
        return "User #" + id;
    }

    // /sum?a11=10&b=20
    @PostMapping("/post")
    public String postDemo() {
        return "post";
    }
}
