package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * GetPlayer200Response
 */

@JsonTypeName("getPlayer_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-01T14:56:16.342685+05:30[Asia/Kolkata]", comments = "Generator version: 7.10.0")
public class GetPlayer200Response {

  private String playerId;

  private String name;

  private String email;

  private String gameId;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  public GetPlayer200Response playerId(String playerId) {
    this.playerId = playerId;
    return this;
  }

  /**
   * The unique identifier of the player.
   * @return playerId
   */
  
  @Schema(name = "playerId", example = "player-12345", description = "The unique identifier of the player.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("playerId")
  public String getPlayerId() {
    return playerId;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  public GetPlayer200Response name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the player.
   * @return name
   */
  
  @Schema(name = "name", example = "John Doe", description = "The name of the player.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public GetPlayer200Response email(String email) {
    this.email = email;
    return this;
  }

  /**
   * The email of the player.
   * @return email
   */
  
  @Schema(name = "email", example = "john.doe@example.com", description = "The email of the player.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public GetPlayer200Response gameId(String gameId) {
    this.gameId = gameId;
    return this;
  }

  /**
   * The ID of the game the player is registered for.
   * @return gameId
   */
  
  @Schema(name = "gameId", example = "game-12345", description = "The ID of the game the player is registered for.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("gameId")
  public String getGameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }

  public GetPlayer200Response createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * The date and time when the player was created.
   * @return createdAt
   */
  @Valid 
  @Schema(name = "createdAt", example = "2024-12-01T00:00Z", description = "The date and time when the player was created.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("createdAt")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetPlayer200Response getPlayer200Response = (GetPlayer200Response) o;
    return Objects.equals(this.playerId, getPlayer200Response.playerId) &&
        Objects.equals(this.name, getPlayer200Response.name) &&
        Objects.equals(this.email, getPlayer200Response.email) &&
        Objects.equals(this.gameId, getPlayer200Response.gameId) &&
        Objects.equals(this.createdAt, getPlayer200Response.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerId, name, email, gameId, createdAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetPlayer200Response {\n");
    sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    gameId: ").append(toIndentedString(gameId)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

