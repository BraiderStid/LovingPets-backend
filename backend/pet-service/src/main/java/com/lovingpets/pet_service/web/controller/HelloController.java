package com.lovingpets.pet_service.web.controller;

import com.lovingpets.pet_service.domain.service.LovingPetsAiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final LovingPetsAiService aiService;

    public HelloController(LovingPetsAiService aiService) {
        this.aiService = aiService;
    }


    @GetMapping("/hello")
    public String hello(){
        return this.aiService.generateGreeting();
    }

}
