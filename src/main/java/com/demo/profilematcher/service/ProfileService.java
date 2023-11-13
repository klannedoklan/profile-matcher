package com.demo.profilematcher.service;

import java.util.Optional;

import com.demo.profilematcher.api.model.PlayerProfileDto;

public interface ProfileService {

  Optional<PlayerProfileDto> getMatchingProfile(String playerId);
}
