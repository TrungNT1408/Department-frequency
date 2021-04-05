package vn.osp.adfilex.Departmentfrequency.model.account;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.osp.adfilex.Departmentfrequency.utils.StringUtils;


/**
 * 
 * vn.osp.adfilex.model.account 
 * @author LuongTN : 9:12:17 AM
 * 
 * 
 * UserSearchDto.java
 */
// @JsonInclude(value = Include.NON_NULL)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "JSon Object Basic DTO cho User Tìm kiếm")
public class UserSearchDto {

  @JsonProperty(value = "status")
  private Integer status;

  @JsonProperty(value = "username_list")
  private List<String> usernameList;

  @JsonProperty(value = "created_date_from")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StringUtils.DF_YYYY_MM_DD)
  private Date createdDateFrom;

  @JsonProperty(value = "created_date_to")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StringUtils.DF_YYYY_MM_DD)
  private Date createdDateTo;

  @JsonProperty(value = "role_code")
  private String roleCode;

  @Override
  public String toString() {
    return "UserSearchDto [status=" + status + ", usernameList=" + usernameList
        + ", createdDateFrom=" + createdDateFrom + ", createdDateTo=" + createdDateTo
        + ", roleCode=" + roleCode + "]";
  }

}
