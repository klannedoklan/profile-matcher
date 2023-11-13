package com.demo.profilematcher.service;

import java.util.List;


public interface MatcherService {

  boolean isLevelMatch(Integer playerLevel, Integer campaignMin, Integer campaignMax);

  boolean anyItemMatch(List<String> playerItems, List<String> campaignItems);

  boolean noneItemMatch(List<String> playerItems, List<String> campaignItems);

  boolean anyCountryMatch(String playerCountry, List<String> campaignCountries);

  boolean noneCountryMatch(String playerCountry, List<String> campaignCountries);

}
