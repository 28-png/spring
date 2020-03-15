package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/${num1}/and/${num2}")
    @ResponseBody
    public int add(@PathVariable int num1, int num2) {
    return num1 + num2;
}


}