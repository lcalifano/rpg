package org.backend.rpg.entity.characters.warrior;


import jakarta.persistence.*;
import lombok.*;
import org.backend.rpg.entity.characters.GameCharacter;

import java.util.Set;

@Entity
@Table(name = "warriors")
@DiscriminatorValue("WARRIOR")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("FieldMayBeFinal")
public class Warrior extends GameCharacter {

    private int armorRating;
    private String armorType;
    private boolean shieldEquipped;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "jt_warrior_ability",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "ability_id"))
    private Set<WarriorSpecials> warriorAbilities;

    @Override
    public float basicAttack() {
        return (getStrength() * 2) + (shieldEquipped ? 5 : 0);
    }

    @Override
    public void setInitialHP() {
        float baseHP = (float) (50 + (1.5*getLevel()*getConstitution()));
        this.setMaxHp(baseHP);
        this.setCurrentHp(baseHP);
    }
}
