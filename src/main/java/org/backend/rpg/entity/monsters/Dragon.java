package org.backend.rpg.entity.monsters;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dragons")
@DiscriminatorValue("DRAGON")
@Getter
@Setter
public class Dragon extends GenericMonster {

    private boolean isFlying;


    @Override
    public int calculateDamage() {
        int level = getLevel();
        float strength =  getBaseDamage();



        return getLevel();
    }

    @Override
    public Set<MonsterAbilities> getSpecialAbilities() {
        return getAbilities();
//                .stream()
//                .map(MonsterAbilities::getName)
//                .toList();
    }
}
