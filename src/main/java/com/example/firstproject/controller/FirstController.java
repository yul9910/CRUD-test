package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        model.addAttribute("username","이서율");
        return "greetings"; // templates/greeting.mustache -> 브라우저로 전송
    }
    @GetMapping("/bye")
    public String seeyouNext(Model model){
        model.addAttribute("nickname","율서");
        return "goodbye";
    }
}
