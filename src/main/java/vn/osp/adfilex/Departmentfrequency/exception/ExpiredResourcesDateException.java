package vn.osp.adfilex.Departmentfrequency.exception;

import java.io.Serializable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * vn.osp.adfilex.exception
 * @author LuongTN : 9:09:18 AM
 * 
 * 
 * ExpiredResourcesDateException.java
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ExpiredResourcesDateException extends Exception implements Serializable {


  private static final long serialVersionUID = 2141685067515932795L;

  public ExpiredResourcesDateException(final String message) {
    super(message);
  }
}
