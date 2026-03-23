package org.backend.rpg.dto.characterInfo.warrior;


import lombok.Data;
import org.backend.rpg.entity.characters.warrior.WarriorSpecials;

@Data
public class WarriorSpecialsDto {

    private Long id;
    private String name;
    private String description;
    private int minLevel;
    private boolean needsArmor;
    private String needsArmorType;
    private float baseDamage;

    public static WarriorSpecialsDto from(WarriorSpecials specials) {
        WarriorSpecialsDto dto = new WarriorSpecialsDto();
        dto.id = specials.getId();
        dto.name = specials.getName();
        dto.description = specials.getDescription();
        dto.minLevel = specials.getMinLevel();
        dto.needsArmor = specials.isNeedsArmor();
        dto.needsArmorType = specials.getNeedsArmorType();
        dto.baseDamage = specials.getBaseDamage();
        return dto;
    }
}
