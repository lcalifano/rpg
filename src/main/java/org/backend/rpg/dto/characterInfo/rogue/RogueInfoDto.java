package org.backend.rpg.dto.characterInfo.rogue;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.backend.rpg.dto.characterInfo.CharacterInfoResponseDto;
import org.backend.rpg.entity.characters.rogue.Rogue;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class RogueInfoDto extends CharacterInfoResponseDto {
    private int flourishPoints;
    private boolean isDualWielding;
    private Set<RogueSpecialsDto> rogueAbilities;

public static RogueInfoDto from(Rogue rogue) {
    RogueInfoDto dto = new RogueInfoDto();
    mapCommonFields(dto, rogue);
    dto.setFlourishPoints(rogue.getFlourishPoints());
    dto.setDualWielding(rogue.isDualWielding());
    dto.setRogueAbilities(rogue.getRogueAbilities().stream()
            .map(RogueSpecialsDto::from)
            .collect(Collectors.toSet()));
    return dto;
}
}
