package org.backend.rpg.entity.characters.warrior;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "warrior_specials")
@Getter
@SuppressWarnings("FieldMayBeFinal")
public class WarriorSpecials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int minLevel;
    private boolean needsArmor;
    private String needsArmorType;
    private float baseDamage;
}
