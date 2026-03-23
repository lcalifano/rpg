package org.backend.rpg.dto.combatActions;

import lombok.Data;

/**
 * Attacco fisico base — disponibile per tutti i personaggi.
 * Usa calculateDamage() del personaggio.
 *
 * JSON esempio:
 * { "actionType": "BASIC_ATTACK" }
 */
@Data
public class BasicAttackAction extends CombatAction {

    @Override
    public CombatActionType getActionType() {
        return CombatActionType.BASIC_ATTACK;
    }
}
