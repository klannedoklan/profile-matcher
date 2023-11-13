package com.demo.profilematcher.fixture;

import com.demo.profilematcher.data.entity.InventoryEntity;

public final class InventoryFixture {

  private InventoryFixture() {
  }

  public static InventoryEntity buildInventoryEntity() {
    InventoryEntity inventory = new InventoryEntity();
    inventory.setId(1L);

    return inventory;
  }

}
