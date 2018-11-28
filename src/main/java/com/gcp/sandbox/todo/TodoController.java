package com.gcp.sandbox.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
public class TodoController {
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Hello Thymeleaf !");
        return "todo/index";
    }
}