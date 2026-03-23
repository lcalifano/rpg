package org.backend.rpg.repository.characters;

import org.backend.rpg.entity.characters.GameCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<GameCharacter, Long> {
    List<GameCharacter> findByOwnerId(Long userId);

}
