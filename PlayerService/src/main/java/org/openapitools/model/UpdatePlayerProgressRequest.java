package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * UpdatePlayerProgressRequest
 */

@JsonTypeName("updatePlayerProgress_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-01T14:56:16.342685+05:30[Asia/Kolkata]", comments = "Generator version: 7.10.0")
public class UpdatePlayerProgressRequest {

  private Integer level;

  private Integer points;

  @Valid
  private List<String> milestones = new ArrayList<>();

  public UpdatePlayerProgressRequest level(Integer level) {
    this.level = level;
    return this;
  }

  /**
   * The current level of the player.
   * @return level
   */
  
  @Schema(name = "level", example = "5", description = "The current level of the player.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("level")
  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public UpdatePlayerProgressRequest points(Integer points) {
    this.points = points;
    return this;
  }

  /**
   * The points the player has earned.
   * @return points
   */
  
  @Schema(name = "points", example = "1500", description = "The points the player has earned.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("points")
  public Integer getPoints() {
    return points;
  }

  public void setPoints(Integer points) {
    this.points = points;
  }

  public UpdatePlayerProgressRequest milestones(List<String> milestones) {
    this.milestones = milestones;
    return this;
  }

  public UpdatePlayerProgressRequest addMilestonesItem(String milestonesItem) {
    if (this.milestones == null) {
      this.milestones = new ArrayList<>();
    }
    this.milestones.add(milestonesItem);
    return this;
  }

  /**
   * List of milestones the player has achieved.
   * @return milestones
   */
  
  @Schema(name = "milestones", example = "[\"First Kill\",\"Reached Level 5\"]", description = "List of milestones the player has achieved.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("milestones")
  public List<String> getMilestones() {
    return milestones;
  }

  public void setMilestones(List<String> milestones) {
    this.milestones = milestones;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdatePlayerProgressRequest updatePlayerProgressRequest = (UpdatePlayerProgressRequest) o;
    return Objects.equals(this.level, updatePlayerProgressRequest.level) &&
        Objects.equals(this.points, updatePlayerProgressRequest.points) &&
        Objects.equals(this.milestones, updatePlayerProgressRequest.milestones);
  }

  @Override
  public int hashCode() {
    return Objects.hash(level, points, milestones);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdatePlayerProgressRequest {\n");
    sb.append("    level: ").append(toIndentedString(level)).append("\n");
    sb.append("    points: ").append(toIndentedString(points)).append("\n");
    sb.append("    milestones: ").append(toIndentedString(milestones)).append("\n");
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

