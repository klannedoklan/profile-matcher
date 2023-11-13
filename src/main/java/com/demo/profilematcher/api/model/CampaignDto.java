package com.demo.profilematcher.api.model;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.demo.profilematcher.util.OffsetDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CampaignDto {

  @JsonProperty("name")
  private String name;

  @JsonProperty("matchers")
  private MatchersDto matchers;

  @JsonProperty("game")
  private String game;

  @JsonProperty("priority")
  private Double priority;

  @JsonProperty(value = "enabled", defaultValue = "false")
  private Boolean enabled;

  @JsonProperty("start_date")
  @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
  private OffsetDateTime startDate;

  @JsonProperty("end_date")
  @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
  private OffsetDateTime endDate;

  @JsonProperty("last_updated")
  @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
  private OffsetDateTime updatedAt;

}
