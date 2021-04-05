package vn.osp.adfilex.Departmentfrequency.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import vn.osp.adfilex.Departmentfrequency.utils.StringUtils;


/**
 * 
 * vn.osp.adfilex.model 
 * @author LuongTN : 9:11:33 AM
 * 
 * 
 * ResponseBody.java
 */
@Builder
@Getter
@Setter
@Data
@ApiModel(description = "JSon Object DTO cho data common")
public class ResponseBody<T> {

  private T body;
  private String message;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StringUtils.DF_YYYY_MM_DD)
  private Date time;
  private int status;


  public ResponseBody(T body, String message, Date time,int status) {
    super();
    this.body = body;
    this.message = message;
    this.time = new Date();
    this.status=status;
  }


  public ResponseBody() {
    super();
    this.time = new Date();
  }


}
