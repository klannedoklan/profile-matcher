package com.demo.profilematcher.api.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.demo.profilematcher.util.OffsetDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerProfileDto {

  @JsonProperty("player_id")
  private String playerId;

  @JsonProperty("active_campaigns")
  private List<String> activeCampaigns;

  @JsonProperty("clan")
  private String clan;

  @JsonProperty("devices")
  private List<DeviceDto> devices;

  @JsonProperty("inventory")
  private Map<String, Integer> inventory;

  @JsonProperty("country")
  private String country;

  @JsonProperty("gender")
  private String gender;

  @JsonProperty("language")
  private String language;

  @JsonProperty("credential")
  private String credential;

  @JsonProperty("level")
  private Integer level;

  @JsonProperty("total_play_time")
  private Integer totalPlayTime;

  @JsonProperty("total_transactions")
  private Integer totalTransactions;

  @JsonProperty("xp")
  private Integer experience;

  @JsonProperty("total_refund")
  private Double totalRefund;

  @JsonProperty("total_spent")
  private Double totalSpent;

  @JsonProperty("created")
  @JsonSerialize(using = OffsetDateTimeSerializer.class)
  private OffsetDateTime createdAt;

  @JsonProperty("modified")
  @JsonSerialize(using = OffsetDateTimeSerializer.class)
  private OffsetDateTime updatedAt;

  @JsonProperty("last_purchase")
  @JsonSerialize(using = OffsetDateTimeSerializer.class)
  private OffsetDateTime lastPurchaseTime;

  @JsonProperty("last_session")
  @JsonSerialize(using = OffsetDateTimeSerializer.class)
  private OffsetDateTime lastSessionTime;

  @JsonProperty("birth_date")
  @JsonSerialize(using = OffsetDateTimeSerializer.class)
  private OffsetDateTime birthDate;

}
