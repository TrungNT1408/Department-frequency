/**
 * Welcome developer friend. LuongTN ospAdfilex-smartTeleSale-service FileIOException.java 3:23:12
 * PM
 */
package vn.osp.adfilex.Departmentfrequency.exception;

import java.io.Serializable;

/**
 * vn.osp.adfilex.exception 
 * @author LuongTN : 3:23:12 PM
 * 
 * 
 * FileIOException.java
 */
public class FileIOException extends Exception implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 2962209147503143449L;

  public FileIOException(final String message) {
    super(message);
  }
}
