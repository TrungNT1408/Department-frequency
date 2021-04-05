package vn.osp.adfilex.Departmentfrequency.exception;

import java.io.Serializable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * 
 * vn.osp.adfilex.exception 
 * @author LuongTN : 9:09:40 AM
 * 
 * 
 * LoginFailedException.java
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class LoginFailedException extends Exception implements Serializable {

  private static final long serialVersionUID = 1668054874585244657L;

  public LoginFailedException(final String message) {
    super(message);
  }
}
