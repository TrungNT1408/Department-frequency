package vn.osp.adfilex.Departmentfrequency.exception;

import java.io.Serializable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * 
 * vn.osp.adfilex.exception 
 * @author LuongTN : 9:09:47 AM
 * 
 * 
 * NotAllowCallingException.java
 */
@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
public class NotAllowCallingException extends Exception implements Serializable {


  private static final long serialVersionUID = 2141685067515932795L;

  public NotAllowCallingException(final String message) {
    super(message);
  }
}
