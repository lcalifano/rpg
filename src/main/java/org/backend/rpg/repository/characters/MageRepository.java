package org.backend.rpg.repository.characters;

import org.backend.rpg.entity.characters.mage.Mage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MageRepository extends CrudRepository<Mage, Long> {
    List<Mage> findByOwnerId(Long userId);
}
