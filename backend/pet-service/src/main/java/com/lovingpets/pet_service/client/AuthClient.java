package com.lovingpets.pet_service.client;

import com.lovingpets.pet_service.client.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface AuthClient {

    @GetMapping("/users/{id}")
    UserResponse getUserById(@PathVariable Long id);

}
