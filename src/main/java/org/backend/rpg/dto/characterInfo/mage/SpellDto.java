package org.backend.rpg.dto.characterInfo.mage;

import lombok.Builder;
import lombok.Data;
import org.backend.rpg.entity.characters.mage.Spells;

@Data
@Builder
public class SpellDto {
    private int id;
    private String name;
    private String description;
    private float manaCost;
    private float baseDamage;
    private int minLevel;

    public static SpellDto from(Spells spell) {
        return SpellDto.builder()
                .id(spell.getId())
                .name(spell.getName())
                .description(spell.getDescription())
                .manaCost(spell.getManaCost())
                .baseDamage(spell.getBaseDamage())
                .minLevel(spell.getMinLevel())
                .build();
    }
}

