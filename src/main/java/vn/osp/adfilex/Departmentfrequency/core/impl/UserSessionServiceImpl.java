/**
 * Welcome developer friend. PC ospAdfilex-smartTeleSale-service UserSessionServiceImpl.java 2:21:15
 * PM
 */
package vn.osp.adfilex.Departmentfrequency.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.osp.adfilex.Departmentfrequency.core.UserSessionService;
import vn.osp.adfilex.Departmentfrequency.entity.UserSession;
import vn.osp.adfilex.Departmentfrequency.repository.UserSessionRepository;


/**
 *
 * @author Nguyen_Toan
 */
@Service
public class UserSessionServiceImpl implements UserSessionService {

  @Autowired
  private UserSessionRepository userSessionRepository;

  @Override
  public UserSession saveOrUpdate(UserSession session) {
    return userSessionRepository.save(session);
  }

  @Override
  public void delete(Long userId) {
    userSessionRepository.deleteByUserUserId(userId);
  }

}
