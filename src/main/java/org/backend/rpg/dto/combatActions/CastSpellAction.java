package org.backend.rpg.dto.combatActions;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Lancia un incantesimo — solo per il Mage.
 * Il service valida che il personaggio sia effettivamente un Mage
 * e che abbia mana sufficiente per lo spell richiesto.
 *
 * JSON esempio:
 * { "actionType": "CAST_SPELL", "spellId": 7 }
 */
@Data
public class CastSpellAction extends CombatAction {

    @NotNull(message = "spellId è obbligatorio per CAST_SPELL")
    private Long spellId;

    @Override
    public CombatActionType getActionType() {
        return CombatActionType.CAST_SPELL;
    }
}
