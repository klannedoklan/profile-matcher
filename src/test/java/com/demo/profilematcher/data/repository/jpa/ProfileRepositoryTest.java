package com.demo.profilematcher.data.repository.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.profilematcher.data.entity.ProfileEntity;
import com.demo.profilematcher.fixture.ProfileFixture;

@SpringBootTest
class ProfileRepositoryTest {

  @Autowired
  private ProfileRepository profileRepository;

  @Test
  void whenFindByPlayerId_thenReturnProfile() {
    // given
    ProfileEntity profileEntity = ProfileFixture.buildProfileEntity();
    profileRepository.save(profileEntity);

    // when
    Optional<ProfileEntity> profile = profileRepository.findByPlayerId(ProfileFixture.PLAYER_ID);

    // then
    assertTrue(profile.isPresent());
    assertEquals(ProfileFixture.PLAYER_ID, profile.get().getPlayerId());
  }

}