package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RollDiceController {

    @GetMapping("/roll")
    public String rollIt() {
        return "roll";

    }

    @GetMapping("/roll/{guess}")
    public String rollDiceGuess(@PathVariable int guess, Model model) {
        String message;
        int random = (int) (Math.random() * 6) + 1;
        if(random == guess) {
            message = "you guessed random number";
        } else {
            message = "Sorry, try again.";
        }
        model.addAttribute("message", message);
        model.addAttribute("guess", guess);
        model.addAttribute("random", random);
        return "roll-dice-guess";
    }
}
