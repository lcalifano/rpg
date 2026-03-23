package org.backend.rpg.service.character;

import lombok.AllArgsConstructor;
import org.backend.rpg.dto.characterCreation.CharacterCreationRequestDto;
import org.backend.rpg.entity.User;
import org.backend.rpg.entity.characters.GameCharacter;
import org.backend.rpg.entity.characters.mage.Mage;
import org.backend.rpg.entity.characters.rogue.Rogue;
import org.backend.rpg.entity.characters.warrior.Warrior;
import org.backend.rpg.repository.characters.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CharacterCreationService {

    private final CharacterRepository characterRepository;

    public void createCharacter(CharacterCreationRequestDto characterCreationRequestDto, User user) {

        GameCharacter character = switch (characterCreationRequestDto.getCharacterClass()) {
            case WARRIOR -> buildWarrior(characterCreationRequestDto);
            case MAGE -> buildMage(characterCreationRequestDto);
            case ROGUE -> buildRogue(characterCreationRequestDto);

        };
        character.setInitialHP();
        character.setOwner(user);
        characterRepository.save(character);

    }

    private Warrior buildWarrior (CharacterCreationRequestDto request){
        Warrior warrior = new Warrior();
        mapCommonFields(warrior, request);
        // valori di default specifici del Warrior
        warrior.setArmorRating(0);
        warrior.setShieldEquipped(false);
        return warrior;
    }
    private Mage buildMage (CharacterCreationRequestDto request){
        Mage mage = new Mage();
        mapCommonFields(mage, request);
        // valori di default specifici del Warrior
        mage.setMaxMana(20);
        mage.setMana(20);
        return mage;
    }
    private Rogue buildRogue (CharacterCreationRequestDto request){
        Rogue rogue = new Rogue();
        mapCommonFields(rogue, request);
        // valori di default specifici del Warrior
        rogue.setFlourishPoints(5);
        rogue.setDualWielding(false);
        return rogue;
    }
    private void mapCommonFields (GameCharacter character, CharacterCreationRequestDto request){
        character.setName(request.getName());
        character.setDescription(request.getDescription());
        character.setLevel(1);
        character.setStrength(request.getStrength());
        character.setDexterity(request.getDexterity());
        character.setConstitution(request.getConstitution());
        character.setIntelligence(request.getIntelligence());
        character.setWisdom(request.getWisdom());
        character.setCharisma(request.getCharisma());

    }
}