package vn.osp.adfilex.Departmentfrequency.model.account;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.osp.adfilex.Departmentfrequency.utils.StringUtils;

@JsonInclude(value = Include.NON_NULL)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(description = "JSon Object Basic DTO cho User")
public class UserBasicDto implements Comparable<UserBasicDto> {

  @JsonProperty(value = "user_id")
  private Long userId;

  @JsonProperty(value = "username")
  private String username;

  @JsonProperty(value = "status")
  private Integer status;

  @JsonProperty(value = "role_code")
  private String roleCode;
  
  @JsonProperty(value = "type_account")
  private Integer typeAccount;
  
//  @JsonProperty(value = "type_account_string")
//  private String typeAccountStr;

  @JsonProperty(value = "voice_resources_avaiable")
  private Double voiceResourcesAvaiable;

  @JsonProperty(value = "created_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StringUtils.DF_YYYY_MM_DD)
  protected Date createdDate;

  @JsonProperty(value = "expired_resources_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StringUtils.DF_YYYY_MM_DD)
  private Date expiredResourcesDate;

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserBasicDto other = (UserBasicDto) obj;
    return Objects.equals(userId, other.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId);
  }

  @Override
  public int compareTo(UserBasicDto o) {

    return this.username.compareTo(o.getUsername());
  }

}
