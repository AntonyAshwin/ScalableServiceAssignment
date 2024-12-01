package org.openapitools.service;

import org.openapitools.model.Player;
import org.openapitools.model.PlayerProgressResponse;
import org.openapitools.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Optional<Player> findById(String playerId) {
        return playerRepository.findById(playerId);
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }

    public Optional<PlayerProgressResponse> getPlayerProgress(String playerId) {
        return playerRepository.findById(playerId).map(player -> {
            PlayerProgressResponse response = new PlayerProgressResponse();
            response.setId(player.getId());
            response.setGameId(player.getGameId());
            response.setLevel(player.getLevel());
            response.setPoints(player.getPoints());
            response.setMilestones(player.getMilestones());
            return response;
        });
    }

    public Optional<Player> findByGameIdAndName(String gameId, String name) {
        return playerRepository.findByGameIdAndName(gameId, name);
    }
}