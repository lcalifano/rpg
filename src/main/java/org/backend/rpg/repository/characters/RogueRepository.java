package org.backend.rpg.repository.characters;

import org.backend.rpg.entity.characters.rogue.Rogue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RogueRepository extends CrudRepository<Rogue, Long> {
List<Rogue> findByOwnerId(Long userId);
}
