package org.backend.rpg.dto.characterCreation;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.backend.rpg.validator.ValidStatPoints;

@Data
@ValidStatPoints
public class CharacterCreationRequestDto {

    @NotBlank(message = "Il nome è obbligatorio")
    @Size(min = 2, max = 30, message = "Il nome deve essere tra 2 e 30 caratteri")
    private String name;

    private String description;

    @NotNull(message = "La classe del personaggio è obbligatoria")
    private CharacterClass characterClass;

    // ── Stat distribuibili — ognuna tra 0 e 10, somma massima 10 ─────
    @Min(value = 0, message = "Il valore minimo per ogni stat è 0")
    @Max(value = 10, message = "Il valore massimo per ogni stat è 10")
    private int strength = 0;

    @Min(value = 0, message = "Il valore minimo per ogni stat è 0")
    @Max(value = 10, message = "Il valore massimo per ogni stat è 10")
    private int dexterity = 0;

    @Min(value = 0, message = "Il valore minimo per ogni stat è 0")
    @Max(value = 10, message = "Il valore massimo per ogni stat è 10")
    private int constitution = 0;

    @Min(value = 0, message = "Il valore minimo per ogni stat è 0")
    @Max(value = 10, message = "Il valore massimo per ogni stat è 10")
    private int intelligence = 0;

    @Min(value = 0, message = "Il valore minimo per ogni stat è 0")
    @Max(value = 10, message = "Il valore massimo per ogni stat è 10")
    private int wisdom = 0;

    @Min(value = 0, message = "Il valore minimo per ogni stat è 0")
    @Max(value = 10, message = "Il valore massimo per ogni stat è 10")
    private int charisma = 0;

}
