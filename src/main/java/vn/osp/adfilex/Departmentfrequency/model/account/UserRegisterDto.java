package vn.osp.adfilex.Departmentfrequency.model.account;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
 *
 * @author LuongTN : 9:12:13 AM
 *
 *
 * UserRegisterDto.java
 */
// @JsonInclude(value = Include.NON_NULL)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "JSon Object Basic DTO cho User Đăng ký")
public class UserRegisterDto {

    @JsonProperty(value = "user_id")
    private Long userId;

    private String avatar;

    @JsonProperty(value = "phone_number", defaultValue = "")
    @Pattern(regexp = StringUtils.REGEX_NUMBER, message = " Số điện thoại không hợp lệ. ")
    private String phoneNumber;

    @JsonProperty(value = "email", defaultValue = "")
    @Email(message = " Bạn phải nhập email hợp lệ.")
    private String email;

    @JsonProperty(value = "username")
    @NotNull(message = " Tên đăng nhập không bỏ trống. ")
    @NotEmpty(message = " Tên đăng nhập không bỏ trống. ")
    @Pattern(regexp = StringUtils.REGEX_USERNAME, message = " Tên đăng nhập không hợp lệ. ")
    @Size(min = 3, message = " Tên đăng nhập phải từ 3 ký tự.")
    @Size(max = 25, message = " Tên đăng nhập không được vượt quá 25 ký tự.")

    private String username;

    @JsonProperty(value = "first_name", required = true)
    @NotNull
    @NotEmpty
    @Pattern(regexp = StringUtils.REGEX_CHARACTER_VN, message = " Họ chứa ký tự không hợp lệ. ")
    private String firstName;

    @JsonProperty(value = "last_name", required = true)
    @NotNull
    @NotEmpty
    @Pattern(regexp = StringUtils.REGEX_CHARACTER_VN, message = " Tên chứa ký tự không hơp lệ. ")
    private String lastName;

    @JsonProperty(value = "password", required = true)
    @NotNull(message = " Mật khẩu không bỏ trống. ")
    @NotEmpty
    private String password;

    @JsonProperty(value = "status", required = true)
    private Integer status;

    @JsonProperty(value = "address")
    private String address;

    @NotNull
    @NotEmpty
    @JsonProperty(value = "role_code", required = true)
    private String roleCode;

}
