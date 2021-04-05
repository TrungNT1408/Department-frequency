package vn.osp.adfilex.Departmentfrequency.model;

import java.util.Date;
import java.util.List;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * vn.osp.adfilex.model 
 * @author LuongTN : 9:11:21 AM
 * 
 * 
 * JwtAuthDto.java
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "JSon Object Basic DTO cho authorization")
public class JwtAuthDto {

  private String issuer;
  private Long ui;
  private String uname;
  private String rol;
  private Date expi;
  private String usAgent;
  private String ip;
  private String ss;
  private Double voiceResource;
  private Date expireResource;
  private Integer smsResource;
  private List<Long> hobby;
  private List<Long> uiSubs;

}
