/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.constants;

/**
 *
 * @author Nguyen_Toan
 */

public enum PermissionEnum {

  ADMIN("ADM", "Admin", 1), MODERATOR("MOD", "Moderator", 2), CONTRACT("CON", "Contract owner",
      3), SALE("SAL", "Sale", 4);

  private String roleCode;
  private String roleDescription;
  private Integer level;

  private PermissionEnum(String roleCode, String roleDescription, Integer level) {
    this.roleCode = roleCode;
    this.roleDescription = roleDescription;
    this.level = level;
  }

  private PermissionEnum() {}

  public String getRoleCode() {
    return roleCode;
  }

  public String getRoleDescription() {
    return roleDescription;
  }

  public Integer getLevel() {
    return level;
  }

  public static String getRoleDescription(String roleCode) {
    for (PermissionEnum rermissionEnum : PermissionEnum.values()) {
      if (rermissionEnum.getRoleCode().equals(roleCode)) {
        return rermissionEnum.getRoleDescription();
      }
    }
    return null;
  }


}
