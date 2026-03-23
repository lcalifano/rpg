package org.backend.rpg.dto.combatActions;

public enum CombatActionType {
    BASIC_ATTACK,   // tutti i personaggi
    USE_ABILITY,    // CharacterAbility (Warrior, Rogue, ...)
    CAST_SPELL,     // solo Mage
    DEFEND          // tutti - aumenta defense per un turno
}
