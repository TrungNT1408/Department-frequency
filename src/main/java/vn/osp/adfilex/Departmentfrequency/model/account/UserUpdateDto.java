package vn.osp.adfilex.Departmentfrequency.model.account;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.osp.adfilex.Departmentfrequency.utils.StringUtils;


/**
 * 
 * vn.osp.adfilex.model.account 
 * @author LuongTN : 9:12:21 AM
 * 
 * 
 * UserUpdateDto.java
 */
@JsonInclude(value = Include.NON_NULL)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "JSon Object Basic DTO cho User Cập nhật ")
public class UserUpdateDto {

  @JsonProperty(value = "user_id", required = true)
  // @Pattern(regexp = "/d+", message = "User Id must be number")
  private Long userId;

  @JsonProperty(value = "username")
  @NotNull(message = " Tên đăng nhập không bỏ trống. ")
  @NotEmpty(message = " Tên đăng nhập không bỏ trống. ")
  private String username;

  @JsonProperty(value = "password")
  private String password;

  @JsonProperty(value = "email")
  @Email(message = " Bạn phải nhập email hợp lệ. ")
  private String email;

  @JsonProperty(value = "phone_number")
  @Pattern(regexp = StringUtils.REGEX_NUMBER, message = " Số điện thoại không hợp lệ. ")
  private String phoneNumber;

  @JsonProperty(value = "first_name")
  @NotNull(message = "Tên không bỏ trống.")
  @NotEmpty(message = "Tên không bỏ trống.")
  @Pattern(regexp = StringUtils.REGEX_CHARACTER_VN, message = " Họ chứa ký tự không hợp lệ. ")
  private String firstName;

  @JsonProperty(value = "last_name")
  @NotNull(message = "Họ không bỏ trống.")
  @NotEmpty(message = "Họ không bỏ trống.")
  @Pattern(regexp = StringUtils.REGEX_CHARACTER_VN, message = " Tên chứa ký tự không hợp lệ. ")
  private String lastName;

  @JsonProperty(value = "role_code")
  @NotNull(message = " Quyền không được trống. ")
  @NotEmpty(message = " Quyền không được trống. ")
  private String roleCode;

  @JsonProperty(value = "status", required = true)
  private Integer status;

  @JsonProperty(value = "us_p")
  private Long usP;
  
  @JsonProperty(value = "favorit_duration_threshold")
  private Double favDurationThreshold;

}

