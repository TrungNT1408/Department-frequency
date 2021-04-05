package vn.osp.adfilex.Departmentfrequency.constants;
/**
 * 
 * vn.osp.adfilex.constants 
 * @author LuongTN : 9:08:12 AM
 * 
 * 
 * StatusAccountEnum.java
 */
public enum StatusAccountEnum {

  ACTIVE(1, "Actived"), IN_ACTIVE(2, "Inactive"), LOCK(3, "Locked"), PENDING_CONFIRM(4,
      "Pending Confirm"), ONLINE(5, "Online"), DELETE(6, "Delete");

  private Integer status;
  private String statusDescription;

  private StatusAccountEnum(Integer status, String statusDescription) {
    this.status = status;
    this.statusDescription = statusDescription;
  }

  public Integer getStatus() {
    return status;
  }

  public String getStatusDescription() {
    return statusDescription;
  }

  public static String getStatusDescription(Integer status) {

    for (StatusAccountEnum statusDescription : StatusAccountEnum.values()) {
      if (statusDescription.getStatus().equals(status)) {

        return statusDescription.getStatusDescription();
      }
    }
    return null;
  }
}
