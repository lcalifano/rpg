package org.backend.rpg.entity.characters;

import jakarta.persistence.*;
import lombok.Getter;
import org.backend.rpg.entity.monsters.GenericMonster;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "character_abilities")
public class CharacterAbilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private float baseDamage;

    @ManyToMany(mappedBy = "abilities")
    private Set<GameCharacter> characters = new HashSet<>();
}
