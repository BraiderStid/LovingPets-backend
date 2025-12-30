package com.lovingpets.pet_service.domain.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface LovingPetsAiService {

    @UserMessage("""
            Generate a welcome greeting for the Loving Pets platform. 
            It should be friendly and under 120 characters.
            """)
    String generateGreeting();

    @SystemMessage("""
        You are a veterinarian and pet nutrition specialist.
        Analyze the provided information about the pet (species, breed, age, weight, and activity level) and give precise and detailed recommendations on:

        Ideal weight and body condition management.

        Appropriate physical activity plan based on breed and age.

        Recommended diet and feeding schedule, including portions and frequency.
        Provide your response clearly, professionally, and in a way that is easy for a pet owner to understand.

        Provide your response in less than 200 words.
        """)
    String generatePetsSuggestions(@UserMessage String petData);

}
