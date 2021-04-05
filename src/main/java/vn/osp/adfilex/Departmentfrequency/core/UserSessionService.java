/**
 * Welcome developer friend. PC ospAdfilex-smartTeleSale-service UserSessionService.java 2:21:07 PM
 */
package vn.osp.adfilex.Departmentfrequency.core;

import vn.osp.adfilex.Departmentfrequency.entity.UserSession;


/**
 * 
 * vn.osp.adfilex.core 
 * @author LuongTN : 9:06:07 AM
 * 
 * 
 * UserSessionService.java
 */
public interface UserSessionService {

  /**
   * 
   * Save or update
   * 
   * @param session
   * @return UserSession
   */
  UserSession saveOrUpdate(UserSession session);

  /**
   * Delete
   * @param userId
   */
  void delete(Long userId);
}
