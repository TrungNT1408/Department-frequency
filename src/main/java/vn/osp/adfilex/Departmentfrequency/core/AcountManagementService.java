package vn.osp.adfilex.Departmentfrequency.core;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Page;
import vn.osp.adfilex.Departmentfrequency.authencation.CustomUserDetails;
import vn.osp.adfilex.Departmentfrequency.authencation.JwtAuthenticationResponse;
import vn.osp.adfilex.Departmentfrequency.entity.User;
import vn.osp.adfilex.Departmentfrequency.exception.ExpiredResourcesDateException;
import vn.osp.adfilex.Departmentfrequency.exception.LoginFailedException;
import vn.osp.adfilex.Departmentfrequency.exception.PermissionException;
import vn.osp.adfilex.Departmentfrequency.model.JwtAuthDto;
import vn.osp.adfilex.Departmentfrequency.model.RoleDto;
import vn.osp.adfilex.Departmentfrequency.model.account.UserBasicDto;
import vn.osp.adfilex.Departmentfrequency.model.account.UserChangePass;
import vn.osp.adfilex.Departmentfrequency.model.account.UserDto;
import vn.osp.adfilex.Departmentfrequency.model.account.UserForSuggest;
import vn.osp.adfilex.Departmentfrequency.model.account.UserLoginDto;
import vn.osp.adfilex.Departmentfrequency.model.account.UserRegisterDto;
import vn.osp.adfilex.Departmentfrequency.model.account.UserSearchDto;
import vn.osp.adfilex.Departmentfrequency.model.account.UserUpdateDto;

/**
 *
 * @author Nguyen_Toan
 */
public interface AcountManagementService {

    /**
     * Check already username exist .
     *
     * @param email
     * @param username
     * @param phoneNumber
     * @return
     */
    List<User> checkAlreadyUsernameExist(String username);

    /**
     * Check already email exist
     *
     * @param email
     * @return
     */
    List<User> checkAlreadyEmailExist(String email);

    /**
     * Find user parent from id sub
     *
     * @param userId
     * @return
     */
    User findUserParentFromIdSub(Long userId);

    /**
     * Check already phone exist
     *
     * @param phoneNumber
     * @return
     */
    List<User> checkAlreadyPhoneExist(String phoneNumber);

    /**
     * Create new account
     *
     * @param userBasicDto
     * @return
     * @throws PermissionException
     */
    User createNewAccount(UserRegisterDto userRegisterDto) throws PermissionException;

    /**
     * Update account
     *
     * @param userUpdateDto
     * @return
     * @throws ExpiredResourcesDateException
     * @throws PermissionException
     */
    User updateAccount(UserUpdateDto userUpdateDto, User userOld)
            throws ExpiredResourcesDateException, PermissionException;

    /**
     * Delete account
     *
     * @param userBasicDto
     * @throws PermissionException
     */
    User deleteAccount(UserBasicDto userBasicDto);

    /**
     * Lock account
     *
     * @param userBasicDto
     * @return User
     */
    User lockAccount(UserBasicDto userBasicDto);

    /**
     * Update password
     *
     * @param userChangePass
     * @throws PermissionException
     */
    User updatePassword(UserChangePass userChangePass) throws PermissionException;

    /**
     * Get role by user.
     *
     * @param user
     * @return List<RoleDto>
     */
    List<RoleDto> getRoleDtoByUser(User user);


    /**
     *
     * @param pageNumber
     * @param size
     * @param sortByProperties
     * @param sortBy
     * @param user
     * @return Page<UserBasicDto>
     */
    Page<UserBasicDto> initDataGirdAccount(int pageNumber, int size, String sortByProperties,
            String sortBy, User user);

    /**
     *
     * @param createdByUserId
     * @param roleCode
     * @return List<UserBasicDto>
     */
    List<UserBasicDto> initDataGirdSubAccount(Long createdByUserId);

    /**
     * Search account
     *
     * @param pageNumber
     * @param size
     * @param sortByProperties
     * @param sortBy
     * @param userSearchDto
     * @param user
     * @return Page<UserBasicDto>
     */
    Page<UserBasicDto> searchAccount(int pageNumber, int size, String sortByProperties, String sortBy,
            UserSearchDto userSearchDto, Integer roleLevel);

    /**
     * Get user by id
     *
     * @param userId
     * @return UserUpdateDto
     * @throws PermissionException
     */
    UserUpdateDto getUserById(Long userId);

    /**
     * Suggest username
     *
     * @param user
     * @param roleUserCurrent
     * @return
     */
    List<?> suggestUsername(User user, String roleUserCurrent, String username,
            String roleCodeSelected);

    /**
     * Find user by id
     *
     * @param userId
     * @return User
     */
    User findUserById(Long userId);

    /**
     * Find user by username
     *
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * Save
     *
     * @param user
     * @return User
     */
    User save(User user);

    /**
     * Disable Or lock accountsub
     *
     * @param user
     */
    void disableOrLockAccountSub(User userParent, Integer status);

    /**
     * Refresh token
     *
     * @param customUserDetails
     * @return JwtAuthenticationResponse
     */
    JwtAuthenticationResponse refreshToken(CustomUserDetails customUserDetails);


    List<UserDto> findAllAccount();
    
    /**
     * Login
     *
     * @param userLoginDto
     * @return
     * @throws LoginFailedException
     */
    JwtAuthenticationResponse login(UserLoginDto userLoginDto) throws LoginFailedException;
    
    String getPassword(String username);
    
    List<UserForSuggest> suggestUsername(User user,String roleCode);
    
}
