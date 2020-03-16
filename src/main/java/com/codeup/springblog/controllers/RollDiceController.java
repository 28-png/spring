package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice/{n}")
    public String rollIt(@PathVariable int n, Model model) {
        int num = (int) Math.random() * 6 + 1;
        model.addAttribute("n", n);
        if(n == num) {
           return "Congrats!";
        }
        return "Wrong number!";
    }

}
