package com.demo.profilematcher.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.profilematcher.api.model.DeviceDto;
import com.demo.profilematcher.data.entity.DeviceEntity;
import com.demo.profilematcher.fixture.DeviceFixture;

@ExtendWith(MockitoExtension.class)
class ProfileMapperTest {

  @Test
  void givenDeviceEntities_thenVerifyMapping() {
    // given
    List<DeviceEntity> deviceEntities = DeviceFixture.buildDeviceEntities();

    // when
    List<DeviceDto> devices = ProfileMapper.toDevicesDto(deviceEntities);

    // then
    assertEquals(1, devices.size());
    assertEquals(DeviceFixture.CARRIER, devices.get(0).getCarrier());
    assertEquals(DeviceFixture.FIRMWARE, devices.get(0).getFirmware());
    assertEquals(DeviceFixture.MODEL, devices.get(0).getModel());
    assertEquals(1L, devices.get(0).getId());
  }

}
