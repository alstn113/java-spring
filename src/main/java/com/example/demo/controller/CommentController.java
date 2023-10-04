package com.example.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class CommentController {

    @GetMapping("/comments")
    @ResponseBody
    public String list(String a) {
        return "comments/list";
    }

    @GetMapping("/comments/new")
    @ResponseBody
    public String createForm() {
        return "comments/createForm";
    }

}


