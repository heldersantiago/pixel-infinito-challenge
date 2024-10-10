package com.example.pixel.controllers;

import com.example.pixel.services.interfaces.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
}
