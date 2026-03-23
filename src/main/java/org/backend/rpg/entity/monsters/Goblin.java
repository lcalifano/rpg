package org.backend.rpg.entity.monsters;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "goblins")
@DiscriminatorValue("GOBLIN")
@Getter
@Setter
public class Goblin extends GenericMonster {


    @Override
    public int calculateDamage() {
        return 0;
    }

    @Override
    public Set<MonsterAbilities> getSpecialAbilities() {
        return getAbilities();
    }
}
