package org.backend.rpg.entity.characters;

import jakarta.persistence.*;
import lombok.*;
import org.backend.rpg.entity.User;

import java.util.Set;

@Entity
@Table(name = "character")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
@Getter
@Setter

public abstract class GameCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int level;
    private float currentHp;
    private float maxHp;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "jt_char_abilities",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "ability_id")
    )
    public Set<CharacterAbilities> abilities;
    @ManyToOne
    private User owner;                      // il personaggio appartiene a un utente


    // metodi polimorfici — ogni sottoclasse li implementa
    public abstract float basicAttack();
    public abstract void setInitialHP();
}
