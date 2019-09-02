package com.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectionController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
