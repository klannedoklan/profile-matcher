package com.demo.profilematcher.fixture;

import java.util.Collections;
import java.util.List;

import com.demo.profilematcher.data.entity.DeviceEntity;

public final class DeviceFixture {

  public static final String FIRMWARE = "iOS 15";
  public static final String MODEL = "iPhone 12";
  public static final String CARRIER = "AT&T";

  private DeviceFixture() {
  }

  public static List<DeviceEntity> buildDeviceEntities() {
    DeviceEntity deviceEntity = new DeviceEntity();
    deviceEntity.setId(1L);
    deviceEntity.setFirmware(FIRMWARE);
    deviceEntity.setModel(MODEL);
    deviceEntity.setCarrier(CARRIER);
    return Collections.singletonList(deviceEntity);
  }


}
