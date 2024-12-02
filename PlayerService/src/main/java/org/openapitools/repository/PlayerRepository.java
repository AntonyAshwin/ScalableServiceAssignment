package org.openapitools.repository;

import org.openapitools.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
import java.util.List;

public interface PlayerRepository extends MongoRepository<Player, String> {
    Optional<Player> findByGameIdAndName(String gameId, String name);
    List<Player> findByGameId(String gameId);
}