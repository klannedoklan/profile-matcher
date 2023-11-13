package com.demo.profilematcher.fixture;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;

import com.demo.profilematcher.data.entity.ClanEntity;
import com.demo.profilematcher.data.entity.DeviceEntity;
import com.demo.profilematcher.data.entity.ProfileEntity;

public final class ProfileFixture {

  public static final String PLAYER_ID = "97983be2-98b7-11e7-90cf-082e5f28d836";

  private static final int TOTAL_PLAY_TIME = 20;
  public static final OffsetDateTime BIRTH_DATE = OffsetDateTime.of(2023, 3, 1, 11, 30, TOTAL_PLAY_TIME, 30, ZoneOffset.UTC);
  public static final OffsetDateTime CREATED_AT = OffsetDateTime.of(2023, 3, 1, 11, 30, TOTAL_PLAY_TIME, 30, ZoneOffset.UTC);
  public static final OffsetDateTime UPDATED_AT = OffsetDateTime.of(2023, 3, 1, 11, 30, TOTAL_PLAY_TIME, 30, ZoneOffset.UTC);
  public static final OffsetDateTime LAST_PURCHASE_TIME = OffsetDateTime.of(2023, 3, 1, 11, 30, TOTAL_PLAY_TIME, 30, ZoneOffset.UTC);
  public static final OffsetDateTime LAST_SESSION_TIME = OffsetDateTime.of(2023, 3, 1, 11, 30, TOTAL_PLAY_TIME, 30, ZoneOffset.UTC);
  private static final String COUNTRY = "CA";
  private static final String GENDER = "male";
  private static final String LANGUAGE = "gb";
  private static final int LEVEL = 4;
  private static final int TOTAL_TRANSACTIONS = 23;
  private static final int EXPERIENCE = 400;
  private static final double TOTAL_REFUND = 4.66;
  private static final double TOTAL_SPENT = 121.40;
  private static final String CREDENTIAL = "apple_credential";
  private static final String CARRIER = "vodafone";
  private static final String FIRMWARE = "123";
  private static final String MODEL = "apple iphone 11";

  private ProfileFixture() {
  }

  public static ProfileEntity buildProfileEntity() {
    ProfileEntity profileEntity = new ProfileEntity();
    profileEntity.setId(1L);
    profileEntity.setPlayerId(PLAYER_ID);
    profileEntity.setCountry(COUNTRY);
    profileEntity.setGender(GENDER);
    profileEntity.setLanguage(LANGUAGE);
    profileEntity.setLevel(LEVEL);
    profileEntity.setTotalPlayTime(TOTAL_PLAY_TIME);
    profileEntity.setTotalTransactions(TOTAL_TRANSACTIONS);
    profileEntity.setExperience(EXPERIENCE);
    profileEntity.setTotalRefund(TOTAL_REFUND);
    profileEntity.setTotalSpent(TOTAL_SPENT);
    profileEntity.setCreatedAt(CREATED_AT);
    profileEntity.setUpdatedAt(UPDATED_AT);
    profileEntity.setLastPurchaseTime(LAST_PURCHASE_TIME);
    profileEntity.setLastSessionTime(LAST_SESSION_TIME);
    profileEntity.setBirthDate(BIRTH_DATE);
    profileEntity.setCredential(CREDENTIAL);
    profileEntity.setClan(buildClanEntity());
    profileEntity.setDevices(Collections.singletonList(buildDeviceEntity()));
    return profileEntity;
  }

  private static DeviceEntity buildDeviceEntity() {
    DeviceEntity deviceEntity = new DeviceEntity();
    deviceEntity.setCarrier(CARRIER);
    deviceEntity.setFirmware(FIRMWARE);
    deviceEntity.setModel(MODEL);
    deviceEntity.setId(1L);
    return deviceEntity;
  }

  private static ClanEntity buildClanEntity() {
    ClanEntity clanEntity = new ClanEntity();
    clanEntity.setId(123456L);
    clanEntity.setName("Hello world clan");
    return clanEntity;
  }

}
