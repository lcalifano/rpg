package org.backend.rpg.controller.combat;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.backend.rpg.dto.CombatRequest;
import org.backend.rpg.dto.CombatResult;
import org.backend.rpg.dto.combatActions.CombatAction;
import org.backend.rpg.service.combat.CombatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/combat")
@RequiredArgsConstructor
public class CombatController {

    private final CombatService combatService;

    @PostMapping("/turn")
    public ResponseEntity<CombatResult> turn(@Valid @RequestBody CombatRequest request) {
        CombatResult result = combatService.executeTurn(request);
        return ResponseEntity.ok(result);
    };
//    todo aggiungere un controller per dare le info del personaggio (ragionamento inverso dell'imput del combattimento) sfruttare polimorfismo

}
