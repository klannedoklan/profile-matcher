package com.demo.profilematcher.fixture;

import com.demo.profilematcher.api.model.LevelDto;

public final class LevelFixture {

  private static final int MIN = 1;
  private static final int MAX = 10;

  private LevelFixture() {
  }

  public static LevelDto buildLevel() {
    LevelDto level = new LevelDto();
    level.setMin(MIN);
    level.setMax(MAX);

    return level;
  }

}
