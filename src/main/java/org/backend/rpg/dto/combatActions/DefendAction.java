package org.backend.rpg.dto.combatActions;

import lombok.Data;

/**
 * Il personaggio si difende: salta l'attacco e riduce il danno
 * subito dal contrattacco del mostro per questo turno.
 *
 * JSON esempio:
 * { "actionType": "DEFEND" }
 */
@Data
public class DefendAction extends CombatAction {

    @Override
    public CombatActionType getActionType() {
        return CombatActionType.DEFEND;
    }
}
