package org.backend.rpg.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.backend.rpg.dto.characterCreation.CharacterCreationRequestDto;
import org.backend.rpg.dto.characterInfo.CharacterInfoResponseDto;
import org.backend.rpg.entity.User;
import org.backend.rpg.service.character.CharacterCreationService;
import org.backend.rpg.service.character.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/character")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;
    private final CharacterCreationService characterCreationService;

    @GetMapping("/{id}")
    public ResponseEntity<CharacterInfoResponseDto> getCharacterInfo(@PathVariable Long id) {
        return ResponseEntity.ok(characterService.getCharacterInfo(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createCharacter(
            @Valid @RequestBody CharacterCreationRequestDto request,
            @AuthenticationPrincipal User user  // ← Spring lo inietta automaticamente
    ) {
        characterCreationService.createCharacter(request, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
