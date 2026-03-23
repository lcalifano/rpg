package org.backend.rpg.service.combat;

import lombok.RequiredArgsConstructor;
import org.backend.rpg.dto.CombatRequest;
import org.backend.rpg.dto.CombatResult;
import org.backend.rpg.dto.combatActions.*;
import org.backend.rpg.entity.characters.GameCharacter;
import org.backend.rpg.entity.characters.mage.Mage;
import org.backend.rpg.entity.characters.mage.Spells;
import org.backend.rpg.entity.monsters.GenericMonster;
import org.backend.rpg.repository.characters.CharacterRepository;
import org.backend.rpg.repository.monsters.MonsterRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CombatService {
private final MonsterRepository monsterRepository;
private final CharacterRepository characterRepository;

    /**
     * Esegue un singolo turno di combattimento.
     * Flusso:
     *  1. Carica personaggio e mostro dal DB
     *  2. Risolve l'azione del personaggio (instanceof pattern matching - Java 16+)
     *  3. Il mostro contrattacca con il suo calculateDamage()
     *  4. Restituisce CombatResult con stato aggiornato e log testuale
     */
//    todo aggiungere una scelta di combattimento randomica per i mostri

    public CombatResult executeTurn(CombatRequest request) {
        GameCharacter character = characterRepository.findById(request.getCharacterId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Personaggio non trovato: " + request.getCharacterId()));

        GenericMonster monster = monsterRepository.findById(request.getMonsterId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Mostro non trovato: " + request.getMonsterId()));

        List<String> log = new ArrayList<>();
        float damageDealt = 0;
        String actionDescription;
        boolean defending = false;

        // ── Risoluzione azione del personaggio ──────────────────────────────
        CombatAction action = request.getAction();

        if (action instanceof BasicAttackAction) {
            damageDealt = character.basicAttack();
            actionDescription = "Attacco base";
            log.add(character.getName() + " attacca per " + damageDealt + " danni!");

//        } else if (action instanceof UseAbilityAction useAbility) {
//            CharacterAbilities ability = character.getAbilities().stream()
//                    .filter(a -> a.getId().equals(useAbility.getAbilityId()))
//                    .findFirst()
//                    .orElseThrow(() -> new IllegalArgumentException(
//                            "Il personaggio non possiede l'abilità: " + useAbility.getAbilityId()));
//
//            damageDealt = (int) ability.getBaseDamage();
//            actionDescription = "Abilità: " + ability.getName();
//            log.add(character.getName() + " usa " + ability.getName() + " per " + damageDealt + " danni!");
//
        } else if (action instanceof CastSpellAction castSpell) {
            if (!(character instanceof Mage mage)) {
                throw new IllegalArgumentException("Solo un Mage può lanciare magie!");
            }
//            dato che ho messo EAGER come join condition allora il sistema già mi dovrebbe dare la lista di tutte le splls
            Spells spell = mage.getSpells().stream()
                    .filter(s -> (long) s.getId() == castSpell.getSpellId())
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Il Mage non conosce lo spell: " + castSpell.getSpellId()));

            if (mage.getMana() < spell.getManaCost()) {
                throw new IllegalStateException("Mana insufficiente! Richiesto: "
                        + spell.getManaCost() + ", disponibile: " + mage.getMana());
            }

            damageDealt = (int) spell.getBaseDamage();
            actionDescription = "Spell: " + spell.getName();
            log.add(character.getName() + " lancia " + spell.getName() + " per " + damageDealt + " danni!");
//
//        } else if (action instanceof DefendAction) {
//            defending = true;
//            actionDescription = "Difesa";
//            log.add(character.getName() + " si mette in posizione difensiva.");

        } else {
            // Fail-fast: se aggiungi un'azione nuova senza implementarla qui,
            // lo scopri subito a runtime.
            throw new UnsupportedOperationException(
                    "Tipo di azione non gestito: " + action.getClass().getSimpleName());
        }

        // ── Contrattacco del mostro ──────────────────────────────────────────
        int rawMonsterDamage = monster.calculateDamage();
        int monsterDamageDealt = defending ? rawMonsterDamage / 2 : rawMonsterDamage;
        log.add(monster.getName() + " contrattacca per " + monsterDamageDealt + " danni!"
                + (defending ? " (ridotto dalla difesa)" : ""));

        // ── Calcolo HP rimanenti ─────────────────────────────────────────────
        float characterHpAfter = Math.max(0, character.getCurrentHp() - monsterDamageDealt);
        float monsterHpAfter   = Math.max(0, monster.getHealth() - damageDealt);

        boolean monsterDefeated   = monsterHpAfter <= 0;
        boolean characterDefeated = characterHpAfter <= 0;

        if (monsterDefeated)   log.add(monster.getName() + " è stato sconfitto!");
        if (characterDefeated) log.add(character.getName() + " è caduto in battaglia...");

        return CombatResult.builder()
                .actionPerformed(actionDescription)
                .damageDealt(damageDealt)
                .monsterAction("Attacco base di " + monster.getName())
                .monsterDamageDealt(monsterDamageDealt)
                .characterHpAfter(characterHpAfter)
                .monsterHpAfter(monsterHpAfter)
                .monsterDefeated(monsterDefeated)
                .characterDefeated(characterDefeated)
                .combatLog(log)
                .build();
    }

}
