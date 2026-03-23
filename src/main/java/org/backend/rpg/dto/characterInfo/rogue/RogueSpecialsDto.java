package org.backend.rpg.dto.characterInfo.rogue;

import lombok.Builder;
import lombok.Data;
import org.backend.rpg.entity.characters.rogue.RogueSpecials;

@Data
@Builder
public class RogueSpecialsDto {
    private Long id;
    private String name;
    private String description;
    private int minLevel;
    private int flourishPointsCost;
    private float baseDamage;

    public static RogueSpecialsDto from(RogueSpecials specials) {
        return RogueSpecialsDto.builder()
                .id(specials.getId())
                .name(specials.getName())
                .description(specials.getDescription())
                .minLevel(specials.getMinLevel())
                .flourishPointsCost(specials.getFlourishPointsCost())
                .baseDamage(specials.getBaseDamage())
                        .build();
    }
}
