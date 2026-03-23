package org.backend.rpg.dto.characterInfo;

import lombok.Builder;
import lombok.Data;
import org.backend.rpg.entity.characters.CharacterAbilities;

@Data
@Builder
public class AbilityDto {

    private Long id;
    private String name;
    private String description;
    private float baseDamage;

    public static AbilityDto from(CharacterAbilities ability) {
        return AbilityDto.builder()
                .id(ability.getId())
                .name(ability.getName())
                .description(ability.getDescription())
                .baseDamage(ability.getBaseDamage())
                .build();
    }
}
