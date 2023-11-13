package com.demo.profilematcher;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.profilematcher.service.MatcherService;
import com.demo.profilematcher.service.ProfileService;
import com.demo.profilematcher.web.rest.controller.ProfileController;

@SpringBootTest
class ProfileMatcherApplicationTests {

  @Autowired
  private MatcherService matcherService;

  @Autowired
  private ProfileService profileService;

  @Autowired
  private ProfileController profileController;

  @Test
  void contextLoads() {
    assertNotNull(matcherService);
    assertNotNull(profileService);
    assertNotNull(profileController);
  }

}
