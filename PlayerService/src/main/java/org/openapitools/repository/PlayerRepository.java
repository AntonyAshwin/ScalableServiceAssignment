package org.openapitools.repository;

import org.openapitools.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface PlayerRepository extends MongoRepository<Player, String> {
    Optional<Player> findByGameIdAndName(String gameId, String name);
}