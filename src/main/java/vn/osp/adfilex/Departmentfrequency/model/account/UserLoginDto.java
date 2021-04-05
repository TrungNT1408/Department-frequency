package vn.osp.adfilex.Departmentfrequency.model.account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * vn.osp.adfilex.model.account 
 * @author LuongTN : 9:12:07 AM
 * 
 * 
 * UserLoginDto.java
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "JSon Object Basic DTO cho User Login")
public class UserLoginDto {

  @JsonProperty(value = "uname",defaultValue = "admin")
  @NotNull
  @NotBlank
  private String uname;
  
  @JsonProperty(value = "domain")
//  @NotNull
//  @NotBlank
  private String domain;

  @JsonIgnore
  private String pwd;
  @JsonIgnore
  private String ip;
  @JsonIgnore
  private String userAgent;


}

