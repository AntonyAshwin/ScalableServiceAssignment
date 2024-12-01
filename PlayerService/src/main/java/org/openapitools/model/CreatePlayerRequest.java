package org.openapitools.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonTypeName("createPlayer_request")
public class CreatePlayerRequest {
    private String name;
    private String email;
    private String gameId;

    // Getters and Setters
    @Schema(name = "name", example = "John Doe", description = "The name of the player.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Schema(name = "email", example = "john.doe@example.com", description = "The email of the player.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Schema(name = "gameId", example = "game-12345", description = "The ID of the game the player is registered for.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("gameId")
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}

