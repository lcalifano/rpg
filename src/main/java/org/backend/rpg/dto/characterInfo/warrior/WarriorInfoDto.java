package org.backend.rpg.dto.characterInfo.warrior;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.backend.rpg.dto.characterInfo.CharacterInfoResponseDto;
import org.backend.rpg.entity.characters.warrior.Warrior;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class WarriorInfoDto extends CharacterInfoResponseDto {
    private int armorRating;
    private String armorType;
    private boolean shieldEquipped;
    private Set<WarriorSpecialsDto> warriorAbilities;

    public static WarriorInfoDto from(Warrior warrior) {
        WarriorInfoDto dto = new WarriorInfoDto();
        mapCommonFields(dto, warrior);
        dto.setArmorRating(warrior.getArmorRating());
        dto.setArmorType(warrior.getArmorType());
        dto.setShieldEquipped(warrior.isShieldEquipped());
        dto.setWarriorAbilities(warrior.getWarriorAbilities().stream()
                .map(WarriorSpecialsDto::from)
                .collect(Collectors.toSet()));
        return dto;
    }
}
