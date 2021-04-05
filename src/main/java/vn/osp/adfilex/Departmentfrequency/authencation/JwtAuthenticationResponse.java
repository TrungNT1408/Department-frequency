package vn.osp.adfilex.Departmentfrequency.authencation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Nguyen_Toan
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationResponse {

  @JsonProperty(value = "access_token")
  private String accessToken;
  
  @JsonProperty(value = "check",required = false)
  private String check = null;

}
