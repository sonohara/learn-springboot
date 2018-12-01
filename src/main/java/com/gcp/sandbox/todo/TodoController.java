package com.gcp.sandbox.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/todo")
public class TodoController {
    
    private TodoService todoService;

    public TodoController(@Autowired TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String index(Model model) {
        List<Todo> todos = this.todoService.findAll();
        model.addAttribute("title", "Hello Thymeleaf !");
        model.addAttribute("todos", todos);
        return "todo/index";
    }

    @PostMapping
    public String create(
        @ModelAttribute Todo todo,
        RedirectAttributes redirectAttributes
    ) {
        this.todoService.save(todo);
        redirectAttributes.addFlashAttribute("message", "Successful Created !");
        return "redirect:/todo";
    }
}