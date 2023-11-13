package com.demo.profilematcher.service.impl;

import java.util.List;

import org.apache.commons.lang3.Range;

import org.springframework.stereotype.Service;

import com.demo.profilematcher.service.MatcherService;

@Service
public class MatcherServiceImpl implements MatcherService {

  @Override
  public boolean isLevelMatch(Integer playerLevel, Integer campaignMin, Integer campaignMax) {
    return Range.between(campaignMin, campaignMax).contains(playerLevel);
  }

  @Override
  public boolean anyItemMatch(List<String> playerItems, List<String> campaignItems) {
    return playerItems.stream()
        .anyMatch(campaignItems::contains);
  }

  @Override
  public boolean noneItemMatch(List<String> playerItems, List<String> campaignItems) {
    return playerItems.stream()
        .noneMatch(campaignItems::contains);
  }

  @Override
  public boolean anyCountryMatch(String playerCountry, List<String> campaignCountries) {
    return campaignCountries.contains(playerCountry);
  }

  @Override
  public boolean noneCountryMatch(String playerCountry, List<String> campaignCountries) {
    return !campaignCountries.contains(playerCountry);
  }

}
