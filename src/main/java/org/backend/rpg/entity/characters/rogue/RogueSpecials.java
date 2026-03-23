package org.backend.rpg.entity.characters.rogue;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "rogue_specials")
@Getter
@SuppressWarnings("FieldMayBeFinal")
public class RogueSpecials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int minLevel;
    private int flourishPointsCost;
    private float baseDamage;

}
