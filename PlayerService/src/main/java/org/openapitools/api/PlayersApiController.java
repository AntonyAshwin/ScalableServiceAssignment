package org.openapitools.api;

import org.openapitools.model.CreatePlayer201Response;
import org.openapitools.model.CreatePlayerRequest;
import org.openapitools.model.Player;
import org.openapitools.service.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;

import java.util.Optional;
import javax.annotation.Generated;

@Controller
@RequestMapping("/players")
public class PlayersApiController implements PlayersApi {

    @Autowired
    private PlayerService playerService;

    private final NativeWebRequest request;

    @Autowired
    public PlayersApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<CreatePlayer201Response> createPlayer(@Valid @RequestBody CreatePlayerRequest createPlayerRequest) {
        Player player = new Player();
        player.setName(createPlayerRequest.getName());
        player.setEmail(createPlayerRequest.getEmail());
        player.setGameId(createPlayerRequest.getGameId());

        Player createdPlayer = playerService.createPlayer(player);

        CreatePlayer201Response response = new CreatePlayer201Response();
        response.setPlayerId(createdPlayer.getPlayerId());

        return ResponseEntity.status(201).body(response);
    }
}
