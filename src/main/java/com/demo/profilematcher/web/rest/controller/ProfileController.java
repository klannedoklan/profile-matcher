package com.demo.profilematcher.web.rest.controller;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.demo.profilematcher.api.model.PlayerProfileDto;
import com.demo.profilematcher.api.responses.BadRequestResponse;
import com.demo.profilematcher.api.responses.InternalServerErrorResponse;
import com.demo.profilematcher.api.responses.NotFoundResponse;
import com.demo.profilematcher.service.ProfileService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/get_client_config")
public class ProfileController {

  private final ProfileService profileService;

  @Operation(summary = "Gets player profile by ID", description = "Player profile must exist")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Ok", content =
          { @Content(mediaType = "application/json", schema =
          @Schema(implementation = PlayerProfileDto.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid Player ID supplied", content =
          { @Content(mediaType = "application/json", schema =
          @Schema(implementation = BadRequestResponse.class)) }),
      @ApiResponse(responseCode = "404", description = "Player profile not found", content =
          { @Content(mediaType = "application/json", schema =
          @Schema(implementation = NotFoundResponse.class)) }),
      @ApiResponse(responseCode = "500", description = "Internal server error", content =
          { @Content(mediaType = "application/json", schema =
          @Schema(implementation = InternalServerErrorResponse.class)) })
  })
  @GetMapping("/{player_id}")
  public ResponseEntity<PlayerProfileDto> getPlayerProfile(@PathVariable("player_id") String playerId) {
    Optional<PlayerProfileDto> profile = profileService.getMatchingProfile(playerId);

    return profile.map(playerProfile -> new ResponseEntity<>(playerProfile, HttpStatus.OK))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

}
