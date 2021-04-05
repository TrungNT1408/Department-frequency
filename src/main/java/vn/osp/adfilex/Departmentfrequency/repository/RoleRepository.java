/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.osp.adfilex.Departmentfrequency.entity.Role;

/**
 * 
 * vn.osp.adfilex.repository 
 * @author LuongTN : 9:19:08 AM
 * 
 * 
 * RoleRepository.java
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  @Query(value = "SELECT new Role(r.roleId) FROM Role r Where r.roleCode=:roleCode")
  Role findByRoleCode(String roleCode);

  @Query(
      value = "SELECT new Role(r.roleId,r.description ,r.roleCode,r.roleName) FROM Role r WHERE r.roleLevel >:roleLevel ")
  List<Role> findByUserCurrent(Integer roleLevel);

}
