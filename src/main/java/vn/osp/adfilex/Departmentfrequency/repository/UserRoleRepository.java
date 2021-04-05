/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.osp.adfilex.Departmentfrequency.entity.User;
import vn.osp.adfilex.Departmentfrequency.entity.UserRole;


/**
 * 
 * vn.osp.adfilex.repository 
 * @author LuongTN : 9:19:41 AM
 * 
 * 
 * UserRoleRepository.java
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

  @Query(
      value = "SELECT  new UserRole(ur.userRoleId) FROM UserRole ur WHERE ur.user.userId=:userId")
  List<UserRole> findByUserUserIdForAssignPermission(Long userId);

  @Query(
      value = "SELECT new User( u.userId, u.username) FROM UserRole ur JOIN ur.user u JOIN ur.role r WHERE u.createByUserId=:createByUserId AND r.roleCode=:role")
  List<User> findByCreateByUserIdAndRole(Long createByUserId, String role);

  @Query(
      value = "SELECT new User( u.userId, u.username) FROM UserRole ur JOIN ur.user u JOIN ur.role r WHERE r.roleCode=:role")
  List<User> findAllContractOwner(String role);

  @Query(
      value = "SELECT r.roleCode FROM UserRole ur JOIN ur.user u JOIN ur.role r WHERE u.userId=:userId")
  String findRoleCodeByUserUserId(Long userId);
}
