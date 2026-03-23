package org.backend.rpg.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.backend.rpg.dto.combatActions.CombatAction;

@Data
public class CombatRequest {
    @NotNull(message = "characterId è obbligatorio")
    private Long characterId;

    @NotNull(message = "monsterId è obbligatorio")
    private Long monsterId;

    @NotNull(message = "action è obbligatoria")
    @Valid
    private CombatAction action;
}
