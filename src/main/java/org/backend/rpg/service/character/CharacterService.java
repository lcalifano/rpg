package org.backend.rpg.service.character;

import lombok.RequiredArgsConstructor;
import org.backend.rpg.dto.characterInfo.CharacterInfoResponseDto;
import org.backend.rpg.dto.characterInfo.mage.MageInfoDto;
import org.backend.rpg.dto.characterInfo.rogue.RogueInfoDto;
import org.backend.rpg.dto.characterInfo.warrior.WarriorInfoDto;
import org.backend.rpg.entity.characters.GameCharacter;
import org.backend.rpg.entity.characters.mage.Mage;
import org.backend.rpg.entity.characters.rogue.Rogue;
import org.backend.rpg.entity.characters.warrior.Warrior;
import org.backend.rpg.repository.characters.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;


    public CharacterInfoResponseDto getCharacterInfo(Long characterId) {
        GameCharacter character = characterRepository.findById(characterId)
                .orElseThrow(() -> new IllegalArgumentException("Personaggio non trovato: " + characterId));

        if (character instanceof Mage mage) {
            return MageInfoDto.from(mage);
        } else if (character instanceof Warrior warrior) {
            return WarriorInfoDto.from(warrior);
        } else if (character instanceof Rogue rogue) {
            return RogueInfoDto.from(rogue);
        }
//        altre classi appena le ho fatte

        throw new UnsupportedOperationException(
                "Tipo di personaggio non gestito: " + character.getClass().getSimpleName());
    }

}
