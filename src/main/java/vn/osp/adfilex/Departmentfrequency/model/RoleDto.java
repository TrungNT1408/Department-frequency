package vn.osp.adfilex.Departmentfrequency.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "JSon Object Basic DTO Role")
public class RoleDto {

  @JsonProperty(value = "role_code")
  private String roleCode;

  @JsonProperty(value = "role_name")
  private String roleName;

}
