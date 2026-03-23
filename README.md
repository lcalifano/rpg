# RPG Backend

Backend API per un gioco di ruolo sviluppato con Spring Boot 4 e Java 21. Gestisce personaggi, autenticazione e un sistema di combattimento a turni.

## Tech Stack

- **Java 21** / **Spring Boot 4.0.3**
- **Spring Security** + **JWT** (JJWT 0.12.3)
- **Spring Data JPA** + **PostgreSQL 16**
- **Flyway** per le migrazioni DB
- **SpringDoc OpenAPI** per la documentazione API
- **Lombok**
- **Docker Compose** per il database

## Setup

### Prerequisiti

- Java 21+
- Docker (per PostgreSQL)

### Avvio

```bash
# 1. Avvia il database
docker compose up -d

# 2. Crea il file di configurazione locale
cp src/main/resources/application-local.yaml.example src/main/resources/application-local.yaml
# Modifica le credenziali in application-local.yaml

# 3. Avvia l'applicazione
./mvnw spring-boot:run
```

Il server parte su `http://localhost:8080`.

## API Endpoints

### Autenticazione

| Metodo | Endpoint         | Descrizione           | Auth |
|--------|------------------|-----------------------|------|
| POST   | `/auth/register` | Registrazione utente  | No   |
| POST   | `/auth/login`    | Login, ritorna JWT    | No   |

### Personaggi

| Metodo | Endpoint             | Descrizione                          | Auth |
|--------|----------------------|--------------------------------------|------|
| GET    | `/character/{id}`    | Info personaggio (risposta polimorfa)| No   |
| POST   | `/character/create`  | Crea un nuovo personaggio            | JWT  |

### Combattimento

| Metodo | Endpoint        | Descrizione                  | Auth |
|--------|-----------------|------------------------------|------|
| POST   | `/combat/turn`  | Esegue un turno di combattimento | No |

## Classi Giocabili

| Classe    | HP Base | Specialità                     | Risorsa speciale   |
|-----------|---------|--------------------------------|--------------------|
| **Warrior** | 50 + 1.5 × LVL × CON | Armatura, scudo | Armor Rating |
| **Mage**    | 20 + 1.5 × LVL × CON | Spell con costo mana | Mana (20) |
| **Rogue**   | 35 + 1.5 × LVL × CON | Dual wield, abilità speciali | Flourish Points (5) |

### Creazione Personaggio

Alla creazione si distribuiscono **10 punti** tra 6 statistiche (Strength, Dexterity, Constitution, Intelligence, Wisdom, Charisma), ciascuna con valore 0-10.

## Sistema di Combattimento

Combattimento a turni dove il giocatore sceglie un'azione e il mostro risponde:

- **Basic Attack** — danno basato sulle stats della classe
- **Cast Spell** (solo Mage) — lancia una spell consumando mana
- **Defend** — dimezza il danno ricevuto nel turno
- **Use Ability** — usa un'abilità speciale (WIP)

Il risultato contiene danno inflitto/ricevuto, HP rimanenti e un log testuale.

## Struttura Progetto

```
src/main/java/org/backend/rpg/
├── config/          # Security config
├── controller/      # REST endpoints
├── dto/             # Request/Response objects
├── entity/
│   ├── characters/  # GameCharacter → Warrior, Mage, Rogue
│   └── monsters/    # GenericMonster → Dragon, Goblin
├── exception/       # Global error handling
├── repository/      # Spring Data JPA repos
├── security/        # JWT filter
├── service/         # Business logic
└── validator/       # Custom bean validation
```

## Database

Schema con ereditarietà JOINED per personaggi e mostri:

```
users ──< character (base) ──< warriors / mages / rogues
                              └── character_abilities (M2M)

monsters (base) ──< dragons / goblins
                └── monster_abilities (M2M)
```

## TODO

### Combattimento
- [ ] Implementare `basicAttack()` per il Rogue
- [ ] Implementare `calculateDamage()` per Dragon e Goblin
- [ ] Completare `UseAbilityAction` nel combat service
- [ ] Selezione random dell'azione del mostro (attualmente usa solo `calculateDamage()`)
- [ ] Sistema di iniziativa / ordine turni basato su Dexterity
- [ ] Reward system (XP, loot) alla sconfitta dei mostri

### Personaggi
- [ ] Sistema di level up con distribuzione nuovi punti stats
- [ ] Inventario e equipaggiamento
- [ ] Endpoint per listare tutti i personaggi di un utente (`GET /character/my`)
- [ ] Endpoint per eliminare un personaggio

### Mostri
- [ ] Aggiungere più tipi di mostri
- [ ] Popolare il DB con mostri di vari livelli
- [ ] Endpoint per ottenere info su un mostro

### Sicurezza
- [ ] Proteggere gli endpoint con controllo ruoli (ROLE_USER, ROLE_ADMIN)
- [ ] Verificare che un utente possa usare solo i propri personaggi in combattimento
- [ ] Refresh token

### Infrastruttura
- [ ] Aggiungere migrazioni Flyway
- [ ] Test unitari e di integrazione
- [ ] Dockerizzare l'intera applicazione (non solo il DB)
- [ ] CI/CD pipeline
