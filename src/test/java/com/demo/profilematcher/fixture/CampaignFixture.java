package com.demo.profilematcher.fixture;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import com.demo.profilematcher.api.model.CampaignDto;

public final class CampaignFixture {

  public static final String NAME = "mycampaign";

  private static final OffsetDateTime START_DATE = OffsetDateTime.of(2022, 1, 25, 0, 0, 0, 0, ZoneOffset.UTC);
  private static final OffsetDateTime END_DATE = OffsetDateTime.of(2022, 1, 25, 0, 0, 0, 0, ZoneOffset.UTC);
  private static final OffsetDateTime LAST_UPDATE_DATE = OffsetDateTime.of(2022, 1, 25, 0, 0, 0, 0, ZoneOffset.UTC);
  private static final String GAME = "mygame";
  private static final double PRIORITY = 10.5;

  private CampaignFixture() {
  }

  public static CampaignDto buildCampaignDto() {
    CampaignDto campaign = new CampaignDto();
    campaign.setName(NAME);
    campaign.setEnabled(Boolean.TRUE);
    campaign.setStartDate(START_DATE);
    campaign.setEndDate(END_DATE);
    campaign.setUpdatedAt(LAST_UPDATE_DATE);
    campaign.setPriority(PRIORITY);
    campaign.setGame(GAME);
    campaign.setMatchers(MatchersFixture.buildMatchers());

    return campaign;
  }

}
