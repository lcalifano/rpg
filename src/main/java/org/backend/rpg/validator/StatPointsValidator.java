package org.backend.rpg.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.backend.rpg.dto.characterCreation.CharacterCreationRequestDto;

public class StatPointsValidator implements ConstraintValidator<ValidStatPoints, CharacterCreationRequestDto> {
    private int maxPoints;

    @Override
    public void initialize(ValidStatPoints annotation) {
        this.maxPoints = annotation.maxPoints();
    }

    @Override
    public boolean isValid(CharacterCreationRequestDto dto, ConstraintValidatorContext context) {
        if (dto == null) return true;

        int total = dto.getStrength()
                + dto.getDexterity()
                + dto.getConstitution()
                + dto.getIntelligence()
                + dto.getWisdom()
                + dto.getCharisma();

        if (total > maxPoints) {
            // Sovrascrive il messaggio di default aggiungendo i dettagli
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Hai assegnato " + total + " punti su " + maxPoints + " disponibili"
            ).addConstraintViolation();
            return false;
        }

        return true;
    }
}
