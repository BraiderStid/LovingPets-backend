package com.lovingpets.pet_service.web.controller;

import com.lovingpets.pet_service.domain.dto.PetDto;
import com.lovingpets.pet_service.domain.dto.PetUpdateDto;
import com.lovingpets.pet_service.domain.service.LovingPetsAiService;
import com.lovingpets.pet_service.domain.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
@Tag(name = "Pets", description = "Pets Service handles all pet-related data and operations across the platform.")
public class PetController {

    private final PetService petService;
    private final LovingPetsAiService aiService;

    public PetController(PetService petService, LovingPetsAiService lovingPetsAiService, LovingPetsAiService aiService) {
        this.petService = petService;
        this.aiService = aiService;
    }

    @GetMapping
    public ResponseEntity<List<PetDto>> getAll(){
        return ResponseEntity.ok(this.petService.getAll());
    }

    @Operation(
            summary = "Get pet data by id",
            description = "Retrieve detailed information of a specific pet using its unique identifier.",
    responses = {
            @ApiResponse(responseCode = "200", description = "Pet found"),
            @ApiResponse(responseCode = "404", description = "Pet not found")
    }
    )
    @GetMapping(("/{id}"))
    public ResponseEntity<PetDto> getById(@Parameter(description = "Identifier of the pet to retrieve", example = "1") @PathVariable long id) {
        return ResponseEntity.ok(this.petService.getById(id));
    }

    //aiSuggest init
    @GetMapping("/{id}/suggest")
    public ResponseEntity<String> generatePetsSuggestions(@PathVariable long id) {
        PetDto pet = this.petService.getById(id);

        String petData = this.petService.formatPetDataForAI(pet);
        String suggestions = this.aiService.generatePetsSuggestions(petData);

        return ResponseEntity.ok(suggestions);
    }
    //aiSuggest end

    @PostMapping
    public  ResponseEntity<PetDto> save(@Valid @RequestBody PetDto petDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.petService.save(petDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetDto> update(@PathVariable long id, @Valid @RequestBody PetUpdateDto petUpdateDto) {
        return ResponseEntity.ok(this.petService.update(id, petUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        this.petService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
