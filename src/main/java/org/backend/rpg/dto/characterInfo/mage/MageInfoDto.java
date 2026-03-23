package org.backend.rpg.dto.characterInfo.mage;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.backend.rpg.dto.characterInfo.CharacterInfoResponseDto;
import org.backend.rpg.entity.characters.mage.Mage;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class MageInfoDto extends CharacterInfoResponseDto {
    private int mana;
    private int maxMana;
    private Set<SpellDto> spells;

// ca chiamare con MageInfo mageInfo = newMageInfo.from(mage);
public static MageInfoDto from(Mage mage) {
    MageInfoDto dto = new MageInfoDto();
    mapCommonFields(dto, mage);        // campi comuni dal padre — una riga
    dto.setMana(mage.getMana());
    dto.setMaxMana(mage.getMaxMana());
    dto.setSpells(mage.getSpells().stream()
            .map(SpellDto::from)
            .collect(Collectors.toSet()));
    return dto;
}

}
