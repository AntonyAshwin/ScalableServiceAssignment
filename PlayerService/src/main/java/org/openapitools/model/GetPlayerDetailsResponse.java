package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class GetPlayerDetailsResponse {
    @JsonProperty("playerId")
    private String playerId;

    @JsonProperty("email")
    private String email;

    @Schema(name = "playerId", example = "player-12345", description = "The unique identifier of the player.")
    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    @Schema(name = "email", example = "john.doe@example.com", description = "The email of the player.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
