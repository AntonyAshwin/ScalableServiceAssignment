package org.openapitools.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class PlayerProgressResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("gameId")
    private String gameId;

    @JsonProperty("level")
    private int level;

    @JsonProperty("points")
    private int points;

    @JsonProperty("milestones")
    private List<String> milestones;

    @Schema(name = "id", example = "player-12345", description = "The unique identifier of the player.")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Schema(name = "gameId", example = "game-12345", description = "The ID of the game the player is registered for.")
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    @Schema(name = "level", example = "5", description = "The current level of the player.")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Schema(name = "points", example = "1500", description = "The points the player has earned.")
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Schema(name = "milestones", example = "[\"First Kill\", \"Reached Level 5\"]", description = "List of milestones the player has achieved.")
    public List<String> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<String> milestones) {
        this.milestones = milestones;
    }
}
