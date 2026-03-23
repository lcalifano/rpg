package org.backend.rpg.dto.combatActions;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Usa una CharacterAbility specifica del personaggio.
 * Disponibile per Warrior, Rogue e qualsiasi classe futura con abilities.
 *
 * JSON esempio:
 * { "actionType": "USE_ABILITY", "abilityId": 3 }
 */
@Data
public class UseAbilityAction extends CombatAction {

    @NotNull(message = "abilityId è obbligatorio per USE_ABILITY")
    private Long abilityId;

    @Override
    public CombatActionType getActionType() {
        return CombatActionType.USE_ABILITY;
    }
}
