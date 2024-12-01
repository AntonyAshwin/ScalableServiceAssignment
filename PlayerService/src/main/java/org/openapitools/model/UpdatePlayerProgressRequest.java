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
  private List<String> milestones;

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public Integer getPoints() {
    return points;
  }

  public void setPoints(Integer points) {
    this.points = points;
  }

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

