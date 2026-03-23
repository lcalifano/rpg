package org.backend.rpg.entity.monsters;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "monster_abilities")
@Getter
@Setter
@NoArgsConstructor
public class MonsterAbilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int baseAbilityDamage;


    @ManyToMany(mappedBy = "abilities")
    private Set<GenericMonster> monsters = new HashSet<>();
}
