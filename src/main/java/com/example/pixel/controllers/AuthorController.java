package com.example.pixel.controllers;

import com.example.pixel.services.interfaces.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/authors")
public class AuthorController {
    @Autowired
    private final AuthorService authorService;

    @GetMapping
    public String sample() {
        return "Nova Vida";
    }
}
