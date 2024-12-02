package org.openapitools.api;

import org.openapitools.model.Player;
import org.openapitools.model.AchievementResponse;
import org.openapitools.model.CreatePlayer201Response;
import org.openapitools.model.CreatePlayerRequest;
import org.openapitools.model.GetPlayer200Response;
import org.openapitools.model.PlayerProgressResponse;
import org.openapitools.model.UpdatePlayerProgressRequest;
import org.openapitools.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import javax.validation.Valid;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/players")
public class PlayersApiController {
    @Autowired
    private PlayerService playerService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${achievement.service.url}")
    private String achievementsUrl;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<CreatePlayer201Response> createPlayer(@Valid @RequestBody CreatePlayerRequest createPlayerRequest) {
        Player player = new Player();
        player.setName(createPlayerRequest.getName());
        player.setEmail(createPlayerRequest.getEmail());
        player.setGameId(createPlayerRequest.getGameId());

        Player createdPlayer = playerService.createPlayer(player);

        CreatePlayer201Response response = new CreatePlayer201Response();
        response.setPlayerId(createdPlayer.getId());

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<GetPlayer200Response> getPlayer(@PathVariable String playerId) {
        Optional<Player> playerOptional = playerService.findById(playerId);
        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            GetPlayer200Response response = new GetPlayer200Response();
            response.setPlayerId(player.getId());
            response.setName(player.getName());
            response.setEmail(player.getEmail());
            response.setGameId(player.getGameId());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{playerId}/progress", method = RequestMethod.PATCH, consumes = "application/json", produces = "application/json")
    public ResponseEntity<PlayerProgressResponse> updatePlayerProgress(@PathVariable String playerId, @Valid @RequestBody UpdatePlayerProgressRequest updatePlayerProgressRequest) {
        Optional<Player> playerOptional = playerService.findById(playerId);
        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();

            if (updatePlayerProgressRequest.getLevel() != null) {
                player.setLevel(updatePlayerProgressRequest.getLevel());
            }

            if (updatePlayerProgressRequest.getPoints() != null) {
                player.setPoints(updatePlayerProgressRequest.getPoints());
            }

            if (updatePlayerProgressRequest.getMilestones() != null) {
                player.setMilestones(updatePlayerProgressRequest.getMilestones());
            }

            playerService.save(player);

            PlayerProgressResponse response = new PlayerProgressResponse();
            response.setId(player.getId());
            response.setGameId(player.getGameId());
            response.setLevel(player.getLevel());
            response.setPoints(player.getPoints());
            response.setMilestones(player.getMilestones());

            // Make HTTP call to achievements service
            ResponseEntity<List<AchievementResponse>> achievementsResponse = restTemplate.exchange(
                achievementsUrl,
                HttpMethod.POST,
                new HttpEntity<>(response),
                new ParameterizedTypeReference<List<AchievementResponse>>() {}
            );
            List<AchievementResponse> achievements = achievementsResponse.getBody();
            System.out.println(achievements);

            // Update player's milestones with the response from achievements service
            if (achievements != null) {
                List<String> newMilestones = achievements.stream()
                    .map(AchievementResponse::getName)
                    .collect(Collectors.toList());
                player.setMilestones(newMilestones);
                playerService.save(player);
                response.setMilestones(newMilestones);
            }

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{playerId}/progress", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PlayerProgressResponse> getPlayerProgress(@PathVariable String playerId) {
        Optional<PlayerProgressResponse> progressOptional = playerService.getPlayerProgress(playerId);
        if (progressOptional.isPresent()) {
            return ResponseEntity.ok(progressOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/details")
    public ResponseEntity<GetPlayer200Response> getPlayerDetails(@RequestParam String gameId, @RequestParam String name) {
        Optional<Player> playerOptional = playerService.findByGameIdAndName(gameId, name);
        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            GetPlayer200Response response = new GetPlayer200Response();
            response.setPlayerId(player.getId());
            response.setName(player.getName());
            response.setEmail(player.getEmail());
            response.setGameId(player.getGameId());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/updateProgressByGameId")
    public ResponseEntity<Void> updateProgressByGameId(@RequestParam String gameId) {
        List<Player> players = playerService.findByGameId(gameId);
        for (Player player : players) {
            UpdatePlayerProgressRequest updateRequest = new UpdatePlayerProgressRequest();
            updateRequest.setLevel(player.getLevel());
            updateRequest.setPoints(player.getPoints());
            updateRequest.setMilestones(player.getMilestones());

            updatePlayerProgress(player.getId(), updateRequest);
        }
        return ResponseEntity.ok().build();
    }
}
