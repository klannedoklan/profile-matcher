package com.demo.profilematcher.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MatcherServiceImplTest {

  @InjectMocks
  private MatcherServiceImpl matcherService;

  @Test
  void testIsLevelMatch() {
    // given
    Integer playerLevel = 10;
    Integer campaignMin = 5;
    Integer campaignMax = 15;

    // when
    boolean result = matcherService.isLevelMatch(playerLevel, campaignMin, campaignMax);

    // then
    assertTrue(result);
  }

  @Test
  void testAnyItemMatch() {
    // given
    List<String> playerItems = List.of("sword", "shield", "potion");
    List<String> campaignItems = List.of("bow", "arrow", "potion");

    // when
    boolean result = matcherService.anyItemMatch(playerItems, campaignItems);

    // then
    assertTrue(result);
  }

  @Test
  void testNoneItemMatch() {
    // given
    List<String> playerItems = List.of("sword", "shield", "potion");
    List<String> campaignItems = List.of("bow", "arrow", "ring");

    // when
    boolean result = matcherService.noneItemMatch(playerItems, campaignItems);

    // then
    assertTrue(result);
  }

  @Test
  void testAnyCountryMatch() {
    // given
    String playerCountry = "BG";
    List<String> campaignCountries = List.of("CA", "GR", "BG");

    // when
    boolean result = matcherService.anyCountryMatch(playerCountry, campaignCountries);

    // then
    assertTrue(result);
  }

  @Test
  void testNoneCountryMatch() {
    // given
    String playerCountry = "FR";
    List<String> campaignCountries = List.of("CA", "DE", "IT");

    // when
    boolean result = matcherService.noneCountryMatch(playerCountry, campaignCountries);

    // then
    assertTrue(result);
  }
}
