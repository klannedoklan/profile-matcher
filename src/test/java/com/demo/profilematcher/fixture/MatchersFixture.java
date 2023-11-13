package com.demo.profilematcher.fixture;

import java.util.List;
import java.util.Map;

import com.demo.profilematcher.api.model.MatchersDto;

public final class MatchersFixture {

  private MatchersFixture() {
  }

  public static MatchersDto buildMatchers() {
    return new MatchersDto(
        LevelFixture.buildLevel(),
        Map.of("country", List.of("US", "RO","CA"),
            "items", List.of("item_1")),
        Map.of("items", List.of("item_4"))
    );
  }

}
