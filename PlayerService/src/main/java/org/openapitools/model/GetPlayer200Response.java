package org.openapitools.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonTypeName("getPlayer_200_response")
public class GetPlayer200Response {
    private String playerId;
    private String name;
    private String email;
    private String gameId;

    // Getters and Setters
    @Schema(name = "playerId", example = "player-12345", description = "The unique identifier of the player.")
    @JsonProperty("playerId")
    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    @Schema(name = "name", example = "John Doe", description = "The name of the player.")
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Schema(name = "email", example = "john.doe@example.com", description = "The email of the player.")
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Schema(name = "gameId", example = "game-12345", description = "The ID of the game the player is registered for.")
    @JsonProperty("gameId")
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetPlayer200Response that = (GetPlayer200Response) o;
        return Objects.equals(playerId, that.playerId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(gameId, that.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, name, email, gameId);
    }

    @Override
    public String toString() {
        return "GetPlayer200Response{" +
                "playerId='" + playerId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gameId='" + gameId + '\'' +
                '}';
    }
}

