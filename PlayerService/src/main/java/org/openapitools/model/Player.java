package org.openapitools.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.OffsetDateTime;
import java.util.List;

@Document(collection = "players")
public class Player {
    @Id
    private String id;
    private String name;
    private String email;
    private String gameId;
    private OffsetDateTime createdAt;
    private int level;
    private int points;
    private List<String> milestones;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<String> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<String> milestones) {
        this.milestones = milestones;
    }

    // Add this method
    public String getPlayerId() {
        return id;
    }
}