
package com.demo.profilematcher.data.matcher;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LookupFields {
  ITEMS("items"),
  COUNTRY("country");

  private final String fieldName;
}
