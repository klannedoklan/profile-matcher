package com.demo.profilematcher.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.profilematcher.api.model.CampaignDto;
import com.demo.profilematcher.api.model.PlayerProfileDto;
import com.demo.profilematcher.client.CampaignConfigClient;
import com.demo.profilematcher.data.entity.InventoryEntity;
import com.demo.profilematcher.data.entity.InventoryItemEntity;
import com.demo.profilematcher.data.entity.ProfileEntity;
import com.demo.profilematcher.data.repository.jpa.ProfileRepository;
import com.demo.profilematcher.fixture.CampaignFixture;
import com.demo.profilematcher.fixture.InventoryFixture;
import com.demo.profilematcher.fixture.InventoryItemFixture;
import com.demo.profilematcher.fixture.ProfileFixture;
import com.demo.profilematcher.service.MatcherService;

@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {

  @Mock
  private ProfileRepository profileRepository;

  @Mock
  private CampaignConfigClient campaignConfigClient;

  @Mock
  private MatcherService matcherService;

  @InjectMocks
  private ProfileServiceImpl profileService;

  @Test
  void getMatchingProfile_WhenCampaignConfigNotSuccessful_ThrowsIllegalStateException() {
    // given
    when(campaignConfigClient.getCurrentCampaigns()).thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

    // when & then
    assertThrows(IllegalStateException.class, () -> profileService.getMatchingProfile("playerId"));
  }

  @Test
  void getMatchingProfile_WhenCampaignsNull_ThrowsIllegalStateException() {
    // given
    when(campaignConfigClient.getCurrentCampaigns()).thenReturn(ResponseEntity.ok(null));

    // when & then
    assertThrows(IllegalStateException.class, () -> profileService.getMatchingProfile("playerId"));
  }

  @Test
  void getMatchingProfile_WhenProfileNotFound_ReturnsEmptyOptional() {
    // given
    CampaignDto campaign = CampaignFixture.buildCampaignDto();
    when(campaignConfigClient.getCurrentCampaigns()).thenReturn(ResponseEntity.ok(List.of(campaign)));
    when(profileRepository.findByPlayerId("playerId")).thenReturn(Optional.empty());

    // when
    Optional<PlayerProfileDto> result = profileService.getMatchingProfile("playerId");

    // then
    assertTrue(result.isEmpty());
  }

  @Test
  void getMatchingProfile_WhenInventoryIsEmpty_ReturnsEmptyOptional() {
    // given
    CampaignDto campaign = CampaignFixture.buildCampaignDto();
    ProfileEntity profileEntity = ProfileFixture.buildProfileEntity();
    when(campaignConfigClient.getCurrentCampaigns()).thenReturn(ResponseEntity.ok(List.of(campaign)));
    when(profileRepository.findByPlayerId("playerId")).thenReturn(Optional.of(profileEntity));

    // when
    Optional<PlayerProfileDto> result = profileService.getMatchingProfile("playerId");

    // then
    assertTrue(result.isEmpty());
  }

  @Test
  void getMatchingProfile_WhenInventoryItems_And_PlayerLevel_ReturnsNoMatchingCampaign() {
    // given
    CampaignDto campaign = CampaignFixture.buildCampaignDto();
    ProfileEntity profileEntity = ProfileFixture.buildProfileEntity();
    InventoryEntity inventory = InventoryFixture.buildInventoryEntity();
    inventory.setProfile(profileEntity);

    InventoryItemEntity item = InventoryItemFixture.buildInventoryItemEntity();
    item.setInventory(inventory);

    inventory.setItems(Collections.singletonList(item));
    profileEntity.setInventory(inventory);

    when(campaignConfigClient.getCurrentCampaigns()).thenReturn(ResponseEntity.ok(List.of(campaign)));
    when(profileRepository.findByPlayerId("playerId")).thenReturn(Optional.of(profileEntity));

    // when
    Optional<PlayerProfileDto> result = profileService.getMatchingProfile("playerId");

    // then
    assertFalse(result.isEmpty());
    assertTrue(result.get().getActiveCampaigns().isEmpty());
  }

  @Test
  void getMatchingProfile_WhenInventoryItems_And_PlayerLevelInRange_ReturnsMatchingCampaign() {
    // given
    CampaignDto campaign = CampaignFixture.buildCampaignDto();
    ProfileEntity profileEntity = ProfileFixture.buildProfileEntity();
    InventoryEntity inventory = InventoryFixture.buildInventoryEntity();
    inventory.setProfile(profileEntity);

    InventoryItemEntity item = InventoryItemFixture.buildInventoryItemEntity();
    item.setInventory(inventory);

    inventory.setItems(Collections.singletonList(item));
    profileEntity.setInventory(inventory);

    when(campaignConfigClient.getCurrentCampaigns()).thenReturn(ResponseEntity.ok(List.of(campaign)));
    when(profileRepository.findByPlayerId("playerId")).thenReturn(Optional.of(profileEntity));
    when(matcherService.isLevelMatch(anyInt(), anyInt(), anyInt())).thenReturn(true);

    // when
    Optional<PlayerProfileDto> result = profileService.getMatchingProfile("playerId");

    // then
    assertFalse(result.isEmpty());
    assertEquals(CampaignFixture.NAME, result.get().getActiveCampaigns().get(0));
  }

  // Add more tests for different matchers in the next sprint...

}
