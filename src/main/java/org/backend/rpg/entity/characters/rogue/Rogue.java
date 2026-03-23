package org.backend.rpg.entity.characters.rogue;

import jakarta.persistence.*;
import lombok.*;
import org.backend.rpg.entity.characters.GameCharacter;

import java.util.Set;

@Entity
@Table(name = "rogues")
@DiscriminatorValue("ROGUE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("FieldMayBeFinal")
public class Rogue extends GameCharacter {

    private int flourishPoints;
    private boolean isDualWielding;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "jt_rogue_ability",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "ability_id"))
    private Set<RogueSpecials> rogueAbilities;

    @Override
    public float basicAttack() {
        return 0;
    }

    @Override
    public void setInitialHP() {
        float baseHP = (float) (35 + (1.5*getLevel()*getConstitution()));
        this.setMaxHp(baseHP);
        this.setCurrentHp(baseHP);
    }


}
