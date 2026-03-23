package org.backend.rpg.dto.combatActions;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "actionType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BasicAttackAction.class,  name = "BASIC_ATTACK"),
        @JsonSubTypes.Type(value = UseAbilityAction.class,   name = "USE_ABILITY"),
        @JsonSubTypes.Type(value = CastSpellAction.class,    name = "CAST_SPELL"),
        @JsonSubTypes.Type(value = DefendAction.class,       name = "DEFEND")
})
public abstract class CombatAction {
    public abstract CombatActionType getActionType();
}
