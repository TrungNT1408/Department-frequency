/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.osp.adfilex.Departmentfrequency.entity.User;

/**
 *
 * vn.osp.adfilex.repository
 *
 * @author LuongTN : 9:19:30 AM
 *
 *
 * UserRepository.java
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     *
     * @param email
     * @param username
     * @param phoneNumber
     * @return
     */
    @Query(
            value = "SELECT new User( u.username,u.phoneNumber,u.email ) FROM User u WHERE LOWER(u.username)=:username ")
    List<User> checkAlreadyUsernameExsit(String username);

    /**
     *
     * @param email
     * @param username
     * @param phoneNumber
     * @return
     */
    @Query(
            value = "SELECT new User( u.username,u.phoneNumber,u.email ) FROM User u WHERE LOWER(u.email)=:email ")
    List<User> checkAlreadyEmailExsit(String email);

    /**
     *
     * @param email
     * @param username
     * @param phoneNumber
     * @return
     */
    @Query(
            value = "SELECT new User( u.username,u.phoneNumber,u.email ) FROM User u WHERE LOWER(u.phoneNumber)=:phoneNumber")
    List<User> checkAlreadyPhoneExsit(String phoneNumber);

    /**
     *
     * @param userId
     * @param username
     * @return
     */
//  @Query(
//      value = "UPDATE User u SET u.status=6 WHERE u.userId=:userId AND u.username=:username")
//  Integer deleteByUserIdAndUsername(Long userId, String username);
    /**
     *
     * @param username
     * @return
     */
    User findByUsernameIgnoreCase(String username);

    /**
     *
     * @param username
     * @return
     */
    @Query(
            value = "SELECT rol.roleLevel FROM User u JOIN u.userRoleList ur JOIN ur.role rol WHERE u.username =:username ")
    Integer getRole(String username);

    /**
     *
     * @param userId
     */
    @Override
    Optional<User> findById(Long userId);

    /**
     *
     * @param userId
     * @return
     */
    @Query(value = "SELECT u FROM User u WHERE u.userId=:userId")
    User findByIdCustom(Long userId);

    @Query(
            value = "SELECT u FROM User u WHERE u.userId=(SELECT us.createByUserId FROM User us WHERE us.userId=:userIdSub)")
    User findUserParentFromIdSub(Long userIdSub);

    /**
     *
     * @param roleCode
     * @return
     */
    List<User> findAllByUserRoleListRoleRoleCode(String roleCode);

    @Query(value = "SELECT u FROM User u WHERE LOWER(u.username) !='admin' ")
    List<User> findAllUtilAdmin();

    @Query(value = "SELECT u FROM User u WHERE LOWER(u.username) ='admin' ")
    User getAccountAdmin();

    /**
     *
     * @param roleCode
     * @param createByUserId
     * @param username
     * @return
     */
//  @Query(
//      value = "( (SELECT LOWER(u.username) as username, u.user_id FROM osp_user u JOIN osp_user_role ur ON u.user_id=ur.user_id JOIN osp_role r ON ur.role_id=r.role_id "
//          + " WHERE (u.create_by_user_id=:createByUserId OR u.create_by_user_id IN (SELECT uu.user_id FROM osp_user uu WHERE uu.create_by_user_id=:createByUserId)) "
//          + " AND ((:roleCode IS NULL) OR LOWER(r.role_code=:roleCode)) AND LOWER(u.username) LIKE  concat(:username,'%') ) "
//          + " UNION ( SELECT LOWER(u.username) as username, u.user_id  FROM osp_user u JOIN osp_user_role ur ON u.user_id=ur.user_id JOIN osp_role r ON ur.role_id=r.role_id "
//          + " WHERE  (u.create_by_user_id=:createByUserId OR u.create_by_user_id IN (SELECT uu.user_id FROM osp_user uu WHERE uu.create_by_user_id=:createByUserId))  "
//          + " AND ((:roleCode IS NULL) OR LOWER(r.role_code=:roleCode)) AND LOWER(u.username) LIKE  concat('%',:username,'%') "
//          + " AND LOWER(u.username) NOT LIKE concat(:username,'%')  ) ) ORDER BY username ",
//      nativeQuery = true)
    @Query(
            value = "( (SELECT LOWER(u.username) as username, u.user_id FROM osp_user u JOIN osp_user_role ur ON u.user_id=ur.user_id JOIN osp_role r ON ur.role_id=r.role_id "
            + " WHERE u.status = 1 AND (u.create_by_user_id=:createByUserId OR u.create_by_user_id IN (SELECT uu.user_id FROM osp_user uu WHERE uu.status = 1 AND uu.create_by_user_id=:createByUserId)) "
            + " AND ((:roleCode IS NULL) OR LOWER(r.role_code=:roleCode)) AND LOWER(u.username) LIKE  concat(:username,'%') ) "
            + " UNION ( SELECT LOWER(u.username) as username, u.user_id  FROM osp_user u JOIN osp_user_role ur ON u.user_id=ur.user_id JOIN osp_role r ON ur.role_id=r.role_id "
            + " WHERE u.status = 1 AND  (u.create_by_user_id=:createByUserId OR u.create_by_user_id IN (SELECT uu.user_id FROM osp_user uu WHERE uu.status = 1 AND uu.create_by_user_id=:createByUserId))  "
            + " AND ((:roleCode IS NULL) OR LOWER(r.role_code=:roleCode)) AND LOWER(u.username) LIKE  concat('%',:username,'%') "
            + " AND LOWER(u.username) NOT LIKE concat(:username,'%')  ) ) ORDER BY username ",
            nativeQuery = true)
    List<Map<String, String>> suggestUserByCreateByUserId(String roleCode, Long createByUserId,
            String username);

    /**
     *
     * @param userIdCurrent
     * @param username
     * @param roleCode
     * @return
     */
//  @Query(
//      value = "( (SELECT LOWER(u.username) as username, u.user_id FROM osp_user u JOIN osp_user_role ur ON u.user_id=ur.user_id JOIN osp_role r ON ur.role_id=r.role_id "
//          + "WHERE u.user_id !=:userIdCurrent   AND  ((:roleCode IS NULL) OR LOWER(r.role_code=:roleCode))  AND LOWER(u.username) LIKE  concat(:username,'%')  ) "
//          + " UNION ( SELECT LOWER(u.username) as username, u.user_id FROM osp_user u JOIN osp_user_role ur ON u.user_id=ur.user_id JOIN osp_role r ON ur.role_id=r.role_id "
//          + "WHERE u.user_id !=:userIdCurrent   AND  ((:roleCode IS NULL) OR LOWER(r.role_code=:roleCode))  AND LOWER(u.username) LIKE  concat('%',:username,'%') "
//          + " AND LOWER(u.username) NOT LIKE concat(:username,'%') )  ORDER BY username )",
//      nativeQuery = true)
    @Query(
            value = "( (SELECT LOWER(u.username) as username, u.user_id FROM osp_user u JOIN osp_user_role ur ON u.user_id=ur.user_id JOIN osp_role r ON ur.role_id=r.role_id "
            + "WHERE u.status = 1 AND u.user_id !=:userIdCurrent   AND  ((:roleCode IS NULL) OR LOWER(r.role_code=:roleCode))  AND LOWER(u.username) LIKE  concat(:username,'%') ) "
            + " UNION ( SELECT LOWER(u.username) as username, u.user_id FROM osp_user u JOIN osp_user_role ur ON u.user_id=ur.user_id JOIN osp_role r ON ur.role_id=r.role_id "
            + "WHERE u.status = 1 AND u.user_id !=:userIdCurrent   AND  ((:roleCode IS NULL) OR LOWER(r.role_code=:roleCode))  AND LOWER(u.username) LIKE  concat('%',:username,'%') "
            + " AND LOWER(u.username) NOT LIKE concat(:username,'%') )  ORDER BY username )",
            nativeQuery = true)
    List<Map<String, String>> suggestAllUser(Long userIdCurrent, String username, String roleCode);

    /**
     *
     * @param roleCodeOfModerator
     * @param pageable
     * @return
     */
    @Query(value = "SELECT u FROM User u JOIN u.userRoleList ul JOIN ul.role r "
            + "  WHERE r.roleCode=:roleCodeOfModerator and u.status !=:status ")
    Page<User> findAllModerator(int status, String roleCodeOfModerator, Pageable pageable);
    
    @Query(value = "SELECT u FROM User u JOIN u.userRoleList ul JOIN ul.role r "
            + "  WHERE u.status !=:status ")
    Page<User> findAll(int status, Pageable pageable);

    /**
     *
     * @param createByUserId
     * @param pageable
     * @return
     */
    @Query(value = "SELECT u  FROM User u JOIN u.userRoleList ul JOIN ul.role r "
            + "  WHERE r.roleCode=:roleCode AND u.createByUserId=:createByUserId and u.status !=:status ")
    Page<User> findAllUserByRoleCodeAndCreateBy(int status, String roleCode, long createByUserId,
            Pageable pageable);

    /**
     *
     * @param roleCode
     * @param createByUserId
     * @return
     */
    @Query(value = "SELECT u  FROM User u  WHERE u.createByUserId=:createByUserId ")
    List<User> findAllUserByCreateByUserId(long createByUserId);

    @Query(
            value = "SELECT u  FROM User u JOIN u.userRoleList ur JOIN  ur.role r  WHERE u.createByUserId=:createByUserId AND r.roleCode=:roleCode ")
    List<User> findAllUserByCreateByUserIdUntilByRole(long createByUserId, String roleCode);

    /**
     * Search for admin
     *
     * @param roleLevel
     * @param status
     * @param createdDateFrom
     * @param createdDateTo
     * @param roleCode
     * @param pageable
     * @return
     */
    @Query(value = "SELECT u  FROM User u JOIN u.userRoleList ul JOIN ul.role r WHERE u.status != :statusDe and "
            + " r.roleLevel > :roleLevel " + " AND (:roleCode IS NULL or r.roleCode=:roleCode) "
            + " AND (:status IS NULL  or u.status=:status)"
            + " AND  (:createdDateFrom IS NULL OR  (( DATE_FORMAT(u.createdDate,'%Y-%m-%d' ) BETWEEN DATE_FORMAT(:createdDateFrom,'%Y-%m-%d' )  AND DATE_FORMAT( :createdDateTo,'%Y-%m-%d' )))) ")
    Page<User> searchAllUser(int statusDe, Integer roleLevel, Integer status, Date createdDateFrom,
            Date createdDateTo, String roleCode, Pageable pageable);

    @Query(value = "SELECT u  FROM User u JOIN u.userRoleList ul JOIN ul.role r WHERE u.status != :statusDe and "
            + " r.roleLevel > :roleLevel " + " AND (:roleCode IS NULL or r.roleCode=:roleCode) "
            + " AND  u.username IN (:usernameList) " + " AND (:status IS NULL  or u.status=:status) "
            + " AND  (:createdDateFrom IS NULL OR  (( DATE_FORMAT(u.createdDate,'%Y-%m-%d' ) BETWEEN DATE_FORMAT(:createdDateFrom,'%Y-%m-%d' )  AND DATE_FORMAT( :createdDateTo,'%Y-%m-%d' )))) ")
    Page<User> searchAllByUserCurrent(int statusDe, List<String> usernameList, Integer roleLevel, Integer status,
            Date createdDateFrom, Date createdDateTo, String roleCode, Pageable pageable);

    /**
     * Search for admin
     *
     * @param statusDe
     * @param createByUserId
     * @param roleLevel
     * @param status
     * @param createdDateFrom
     * @param createdDateTo
     * @param roleCode
     * @param pageable
     * @return
     */
    @Query(value = "SELECT u  FROM User u JOIN u.userRoleList ul JOIN ul.role r WHERE u.status != :statusDe and "
            + " r.roleLevel > :roleLevel " + " AND  u.createByUserId=:createByUserId "
            + " AND (:roleCode IS NULL or r.roleCode=:roleCode) "
            + " AND (:status IS NULL  or u.status=:status) "
            + " AND  (:createdDateFrom IS NULL OR  (( DATE_FORMAT(u.createdDate,'%Y-%m-%d' ) BETWEEN DATE_FORMAT(:createdDateFrom,'%Y-%m-%d' )  AND DATE_FORMAT( :createdDateTo,'%Y-%m-%d' )))) ")
    Page<User> searchUser(int statusDe, Long createByUserId, Integer roleLevel, Integer status,
            Date createdDateFrom, Date createdDateTo, String roleCode, Pageable pageable);

    @Query(value = "SELECT u  FROM User u JOIN u.userRoleList ul JOIN ul.role r WHERE u.status != :statusDe and "
            + " r.roleLevel > :roleLevel " + " AND  u.createByUserId=:createByUserId "
            + " AND (:roleCode IS NULL or r.roleCode=:roleCode) " + " AND  u.username IN (:usernameList) "
            + " AND (:status IS NULL  or u.status=:status) "
            + " AND  (:createdDateFrom IS NULL OR  (( DATE_FORMAT(u.createdDate,'%Y-%m-%d' ) BETWEEN DATE_FORMAT(:createdDateFrom,'%Y-%m-%d' )  AND DATE_FORMAT( :createdDateTo,'%Y-%m-%d' )))) ")
    Page<User> searchByUserCurrent(int statusDe, Long createByUserId, List<String> usernameList, Integer roleLevel,
            Integer status, Date createdDateFrom, Date createdDateTo, String roleCode, Pageable pageable);

    /**
     *
     * @param date
     * @param roleCode
     * @return
     */
    @Query(
            value = "SELECT u FROM User u WHERE u.status = :status")
    List<User> findAllAccount(int status);

    /**
     *
     * @param createByUserId
     * @param status
     * @return
     */
    @Query(value = "SELECT u  FROM User u  WHERE u.createByUserId=:createByUserId and u.status != :status")
    List<User> findAllActiveUserByCreateByUserId(long createByUserId, int status);

    /**
     *
     * @param userId
     * @param status
     * @return
     */
    @Query(value = "SELECT u  FROM User u  WHERE u.createByUserId =:userId and u.status =:status ")
    List<User> findUserByCreateByUser(long userId, int status);

    @Query(nativeQuery = true,
            value = "select u.* from osp_user u,osp_user_role ur,osp_role r where u.user_id=ur.user_id\n"
            + "and r.role_id=ur.role_id "
            //            + "and r.role_code !=:role"
            + "and (:role_code is null or r.role_code =:role_code) "
            + "and u.create_by_user_id =:user_id and u.status !=:status ")
    List<User> findAllUser(String role_code, Integer user_id, Integer status);

    @Query(nativeQuery = true,
            value = "select u.* from osp_user u,osp_user_role ur,osp_role r where u.user_id=ur.user_id\n"
            + "and r.role_id=ur.role_id "
            + "and r.role_code !=:role "
            + "and (:role_code is null or r.role_code =:role_code) "
            + "and u.status !=:status ")
    List<User> findAllUserAdmin(String role_code, Integer status, String role);

    @Query(value = "SELECT u FROM User u  WHERE u.createByUserId=:createByUserId and u.status !=:status")
    List<User> findAllUserByCreateByUserIdActive(long createByUserId, int status);

    /**
     * Search for admin
     *
     * @param createByUserId
     * @param status
     * @param createdDateFrom
     * @param createdDateTo
     * @param usernameList
     * @return
     */
    @Query(value = "SELECT u FROM User u  WHERE u.createByUserId=:createByUserId and u.status !=:status "
            + "AND (:usernameList is null or u.username IN (:usernameList)) "
            + "and (:createdDateFrom IS NULL OR  ( DATE_FORMAT(u.createdDate,'%Y-%m-%d' ) >= DATE_FORMAT(:createdDateFrom,'%Y-%m-%d' ))) "
            + "and (:createdDateTo IS NULL OR  ( DATE_FORMAT(u.createdDate,'%Y-%m-%d' ) <= DATE_FORMAT(:createdDateTo,'%Y-%m-%d' )))  ")
    List<User> findAllUserByCreateByUserIdActive1(long createByUserId, int status, Date createdDateFrom, Date createdDateTo,List<String> usernameList);
    
    /**
     * Search for admin
     *
     * @param createByUserId
     * @param status
     * @param createdDateFrom
     * @param createdDateTo
     * @param usernameList
     * @return
     */
    @Query(value = "SELECT u FROM User u  WHERE u.createByUserId=:createByUserId and u.status !=:status "
            + "AND (u.username IN (:usernameList)) "
            + "and (:createdDateFrom IS NULL OR  ( DATE_FORMAT(u.createdDate,'%Y-%m-%d' ) >= DATE_FORMAT(:createdDateFrom,'%Y-%m-%d' ))) "
            + "and (:createdDateTo IS NULL OR  ( DATE_FORMAT(u.createdDate,'%Y-%m-%d' ) <= DATE_FORMAT(:createdDateTo,'%Y-%m-%d' )))  ")
    List<User> findAllUserByCreateByUserIdActive2(long createByUserId, int status, Date createdDateFrom, Date createdDateTo,List<String> usernameList);
}
