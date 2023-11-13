package com.demo.profilematcher.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.profilematcher.api.model.DeviceDto;
import com.demo.profilematcher.data.entity.DeviceEntity;
import com.demo.profilematcher.data.entity.InventoryItemEntity;

public final class ProfileMapper {

  private ProfileMapper() {
  }

  public static List<DeviceDto> toDevicesDto(List<DeviceEntity> deviceEntities) {
    List<DeviceDto> devices = new ArrayList<>();
    deviceEntities.forEach(d -> devices.add(toDeviceDto(d)));

    return devices;
  }

  private static DeviceDto toDeviceDto(DeviceEntity deviceEntity) {
    return DeviceDto.builder()
        .carrier(deviceEntity.getCarrier())
        .id(deviceEntity.getId())
        .firmware(deviceEntity.getFirmware())
        .model(deviceEntity.getModel())
        .build();
  }

  public static Map<String, Integer> toInventory(List<InventoryItemEntity> items) {
    Map<String, Integer> inventory = new HashMap<>();
    items.forEach(item -> inventory.put(item.getName(), item.getQuantity()));

    return inventory;
  }

}
