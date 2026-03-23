package org.backend.rpg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CombatResult {
    // --- Azione del personaggio ---
    private String actionPerformed;       // es. "BASIC_ATTACK", "CAST_SPELL: Fireball"
    private float damageDealt;              // danno inflitto al mostro

    // --- Contrattacco del mostro ---
    private String monsterAction;         // descrizione dell'azione del mostro
    private float monsterDamageDealt;       // danno inflitto al personaggio

    // --- Stato post-turno ---
    private float characterHpAfter;
    private float monsterHpAfter;

    // --- Esito ---
    private boolean monsterDefeated;
    private boolean characterDefeated;

    // --- Log testuale del turno (utile per RPG testuale) ---
    private List<String> combatLog;

}
