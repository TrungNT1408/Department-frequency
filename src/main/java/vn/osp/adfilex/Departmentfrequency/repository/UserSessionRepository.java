/**
 * Welcome developer friend. PC ospAdfilex-smartTeleSale-data UserSessionRepository.java 1:00:24 PM
 */
package vn.osp.adfilex.Departmentfrequency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.osp.adfilex.Departmentfrequency.entity.UserSession;


/**
 *
 * @author Nguyen_Toan
 */
@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {

  /**
  * 
  * @param userId
  * @return
  */
  UserSession findAllByUserUserId(Long userId);

  /**
   * 
   * @param userId
   */
  void deleteByUserUserId(Long userId);
}
