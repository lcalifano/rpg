package org.backend.rpg.repository.monsters;

import org.backend.rpg.entity.monsters.GenericMonster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterRepository extends JpaRepository<GenericMonster, Long> {
}
