package com.demo.profilematcher.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.profilematcher.api.model.CampaignDto;

@FeignClient(name ="campaignConfigClient", url = "https://my-json-server.typicode.com/klannedoklan/mock-gameloft-campaign")
public interface CampaignConfigClient {

  @GetMapping("/campaigns")
  ResponseEntity<List<CampaignDto>> getCurrentCampaigns();

}
