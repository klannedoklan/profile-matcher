package com.demo.profilematcher.fixture;

import com.demo.profilematcher.data.entity.InventoryItemEntity;

public class InventoryItemFixture {

  private InventoryItemFixture() {
  }

  public static InventoryItemEntity buildInventoryItemEntity() {
    InventoryItemEntity inventoryItem = new InventoryItemEntity();
    inventoryItem.setId(1L);
    inventoryItem.setName("item_1");
    inventoryItem.setQuantity(12);

    return inventoryItem;
  }

}
