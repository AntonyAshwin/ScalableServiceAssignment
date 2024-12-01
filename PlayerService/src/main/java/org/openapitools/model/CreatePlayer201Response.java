package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * CreatePlayer201Response
 */

@JsonTypeName("createPlayer_201_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-01T14:56:16.342685+05:30[Asia/Kolkata]", comments = "Generator version: 7.10.0")
public class CreatePlayer201Response {

  private String playerId;

  public CreatePlayer201Response playerId(String playerId) {
    this.playerId = playerId;
    return this;
  }

  /**
   * The unique identifier for the player.
   * @return playerId
   */
  
  @Schema(name = "playerId", example = "player-12345", description = "The unique identifier for the player.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("playerId")
  public String getPlayerId() {
    return playerId;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreatePlayer201Response createPlayer201Response = (CreatePlayer201Response) o;
    return Objects.equals(this.playerId, createPlayer201Response.playerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreatePlayer201Response {\n");
    sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
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

