package org.backend.rpg.entity.monsters;

import jakarta.persistence.*;
import lombok.Getter;
import java.util.Set;

@Entity
@Table(name = "monsters")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
@Getter
@SuppressWarnings("FieldMayBeFinal")
public abstract class GenericMonster {
    @Id
    private Long Id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "level")
    private Integer level;
    private float health;
    private float speed;
    private float defense;
    private float baseDamage;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "jt_mon_abilities", // Nome della tabella di giunzione nel DB
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "ability_id")
    )
    private Set<MonsterAbilities> abilities;
    public abstract int calculateDamage();
    public abstract Set<MonsterAbilities> getSpecialAbilities();
}
