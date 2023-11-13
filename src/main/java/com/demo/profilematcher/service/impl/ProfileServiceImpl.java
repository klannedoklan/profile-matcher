package com.demo.profilematcher.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.profilematcher.api.model.CampaignDto;
import com.demo.profilematcher.api.model.LevelDto;
import com.demo.profilematcher.api.model.PlayerProfileDto;
import com.demo.profilematcher.client.CampaignConfigClient;
import com.demo.profilematcher.data.entity.InventoryEntity;
import com.demo.profilematcher.data.entity.InventoryItemEntity;
import com.demo.profilematcher.data.entity.ProfileEntity;
import com.demo.profilematcher.data.matcher.LookupFields;
import com.demo.profilematcher.data.repository.jpa.ProfileRepository;
import com.demo.profilematcher.service.MatcherService;
import com.demo.profilematcher.service.ProfileService;
import com.demo.profilematcher.util.ProfileMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

  private final ProfileRepository profileRepository;
  private final CampaignConfigClient campaignConfigClient;
  private final MatcherService matcherService;

  @Override
  public Optional<PlayerProfileDto> getMatchingProfile(String playerId) {

    ResponseEntity<List<CampaignDto>> currentCampaignsResponse = campaignConfigClient.getCurrentCampaigns();
    if (!currentCampaignsResponse.getStatusCode().is2xxSuccessful()) {
      throw new IllegalStateException("Failed to get campaign configuration");
    }

    List<CampaignDto> campaigns = currentCampaignsResponse.getBody();
    if (campaigns == null) {
      log.error("Missing campaign configuration!");
      throw new IllegalStateException("Campaign config is null!");
    }
    List<CampaignDto> enabledCampaigns = campaigns.stream()
        .filter(CampaignDto::getEnabled)
        .toList();

    Optional<ProfileEntity> profileEntity = profileRepository.findByPlayerId(playerId);
    if (profileEntity.isEmpty()) {
      log.warn("Profile with id {} not found!", playerId);

      return Optional.empty();
    }

    return updatePlayerProfileWithActiveCampaigns(enabledCampaigns, profileEntity.get());
  }

  private Optional<PlayerProfileDto> updatePlayerProfileWithActiveCampaigns(List<CampaignDto> enabledCampaigns, ProfileEntity profile) {
    InventoryEntity inventory = profile.getInventory();

    if (inventory == null) {
      return Optional.empty();
    }

    List<String> playerItems = inventory
        .getItems()
        .stream()
        .map(InventoryItemEntity::getName)
        .toList();

    Set<String> activeCampaignNames = new HashSet<>();

    enabledCampaigns.forEach(campaign ->
        updateMatchingCampaigns(profile, playerItems, activeCampaignNames, campaign)
    );

    return buildPlayerProfileDto(profile, inventory, new ArrayList<>(activeCampaignNames));
  }

  private static Optional<PlayerProfileDto> buildPlayerProfileDto(ProfileEntity profile, InventoryEntity inventory, List<String> activeCampaigns) {
    return Optional.of(PlayerProfileDto.builder()
        .activeCampaigns(activeCampaigns)
        .clan(profile.getClan().getName())
        .credential(profile.getCredential())
        .devices(ProfileMapper.toDevicesDto(profile.getDevices()))
        .inventory(ProfileMapper.toInventory(inventory.getItems()))
        .birthDate(profile.getBirthDate())
        .country(profile.getCountry())
        .createdAt(profile.getCreatedAt())
        .gender(profile.getGender())
        .language(profile.getLanguage())
        .lastPurchaseTime(profile.getLastPurchaseTime())
        .lastSessionTime(profile.getLastSessionTime())
        .level(profile.getLevel())
        .updatedAt(profile.getUpdatedAt())
        .playerId(profile.getPlayerId())
        .totalPlayTime(profile.getTotalPlayTime())
        .totalRefund(profile.getTotalRefund())
        .totalSpent(profile.getTotalSpent())
        .totalTransactions(profile.getTotalTransactions())
        .experience(profile.getExperience())
        .build());
  }

  private void updateMatchingCampaigns(ProfileEntity profile, List<String> playerItems, Set<String> activeCampaignNames, CampaignDto campaign) {
    LevelDto level = campaign.getMatchers().getLevel();

    if (matcherService.isLevelMatch(profile.getLevel(), level.getMin(), level.getMax())) {
      activeCampaignNames.add(campaign.getName());
    }

    if (matcherService.anyItemMatch(playerItems, getIncludedFilter(campaign, LookupFields.ITEMS.getFieldName()))) {
      activeCampaignNames.add(campaign.getName());
    }

    if (matcherService.anyCountryMatch(profile.getCountry(), getIncludedFilter(campaign, LookupFields.COUNTRY.getFieldName()))) {
      activeCampaignNames.add(campaign.getName());
    }

    if (matcherService.noneItemMatch(playerItems, getExcludedFilter(campaign, LookupFields.ITEMS.getFieldName()))) {
      activeCampaignNames.add(campaign.getName());
    }

    if (matcherService.noneCountryMatch(profile.getCountry(), getExcludedFilter(campaign, LookupFields.COUNTRY.getFieldName()))) {
      activeCampaignNames.add(campaign.getName());
    }
  }

  private static List<String> getIncludedFilter(CampaignDto campaign, String filterKey) {
    return campaign.getMatchers()
        .getIncludeMap()
        .getOrDefault(filterKey, Collections.emptyList());
  }

  private static List<String> getExcludedFilter(CampaignDto campaign, String filterKey) {
    return campaign.getMatchers()
        .getExcludeMap()
        .getOrDefault(filterKey, Collections.emptyList());
  }

}
