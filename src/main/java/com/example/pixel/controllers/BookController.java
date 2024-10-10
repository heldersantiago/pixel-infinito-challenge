package com.example.pixel.controllers;

import com.example.pixel.services.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {
    @Autowired
    private final BookService bookService;
}
