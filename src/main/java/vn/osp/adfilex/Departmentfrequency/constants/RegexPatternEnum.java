/*
 * @author Sysmex
 *
 * Copyright (c) 2019 Sysmex America, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Sysmex America, Inc.
 * ("Confidential Information").
 *
 */
package vn.osp.adfilex.Departmentfrequency.constants;

/**
 * 
 * vn.osp.adfilex.constants 
 * @author LuongTN : 9:08:08 AM
 * 
 * 
 * RegexPatternEnum.java
 */
public enum RegexPatternEnum {

  // Regex found "_" and "%" replace to "\_" or "\%"
  ESCAPE_CHARACTER_IN_LIKE("(_|%)", "\\\\$1"), REPLACE_CAMELCASE_TO_SNAKE_CASE("([a-z])([A-Z]+)",
      "$1_$2");

  private String pattern;
  private String replacement;

  private RegexPatternEnum(String pattern, String replacement) {
    this.pattern = pattern;
    this.replacement = replacement;
  }

  public String getPattern() {
    return pattern;
  }

  public String getReplacement() {
    return replacement;
  }

}
