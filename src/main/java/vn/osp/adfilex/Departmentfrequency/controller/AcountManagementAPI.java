package vn.osp.adfilex.Departmentfrequency.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vn.osp.adfilex.Departmentfrequency.authencation.JwtAuthenticationResponse;
import vn.osp.adfilex.Departmentfrequency.authencation.JwtTokenProvider;
import vn.osp.adfilex.Departmentfrequency.config.MessageSoucreConfig.MessageSourceVi;
import vn.osp.adfilex.Departmentfrequency.constants.ApplicationConstant;
import vn.osp.adfilex.Departmentfrequency.constants.PermissionEnum;
import vn.osp.adfilex.Departmentfrequency.core.AcountManagementService;
import vn.osp.adfilex.Departmentfrequency.core.UserSessionService;
import vn.osp.adfilex.Departmentfrequency.entity.User;
import vn.osp.adfilex.Departmentfrequency.exception.ExpiredResourcesDateException;
import vn.osp.adfilex.Departmentfrequency.exception.LoginFailedException;
import vn.osp.adfilex.Departmentfrequency.exception.PermissionException;
import vn.osp.adfilex.Departmentfrequency.exception.ProxyAuthenticationRequired;
import vn.osp.adfilex.Departmentfrequency.model.JwtAuthDto;
import vn.osp.adfilex.Departmentfrequency.model.ResponseBody;
import vn.osp.adfilex.Departmentfrequency.model.RoleDto;
import vn.osp.adfilex.Departmentfrequency.model.account.UserBasicDto;
import vn.osp.adfilex.Departmentfrequency.model.account.UserChangePass;
import vn.osp.adfilex.Departmentfrequency.model.account.UserLoginDto;
import vn.osp.adfilex.Departmentfrequency.model.account.UserRegisterDto;
import vn.osp.adfilex.Departmentfrequency.model.account.UserSearchDto;
import vn.osp.adfilex.Departmentfrequency.model.account.UserUpdateDto;
import vn.osp.adfilex.Departmentfrequency.repository.UserRoleRepository;
import vn.osp.adfilex.Departmentfrequency.utils.ApplicationUtils;
import vn.osp.adfilex.Departmentfrequency.utils.DateUtils;
import vn.osp.adfilex.Departmentfrequency.utils.EncryptUtils;
import vn.osp.adfilex.Departmentfrequency.utils.StringUtils;

/**
 *
 * @author Nguyen_Toan
 */
@RestController
@RequestMapping("${versionapi}/account-managers")
@Api(basePath = "${versionapi}/account-managers", description = "This API for account management.")
@Validated
@Slf4j
public class AcountManagementAPI {

    @Autowired
    @Qualifier("AcountManagementService_Main")
    private AcountManagementService acountManagement;

    @Autowired
    private UserSessionService userSessionService;

    @Autowired
    private MessageSourceVi messageSourceVi;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Value(value = "${email.username}")
    private String emailUsername;

    @Value(value = "${email.password}")
    private String emailPassword;

    @Value(value = "${email.linkRegister}")
    private String linkRegister;

    @Value(value = "${login.domain.ecocrm}")
    private String domain;

    @Value("${token.ecocrm}")
    private String token;

    private final String OK002 = "OK002";
    private final String OK005 = "OK005";
    private final String ER002 = "ER002";

    private List<String> allRole;

    public AcountManagementAPI() {
        allRole = new ArrayList<>();
        allRole.add(PermissionEnum.ADMIN.getRoleCode());
        allRole.add(PermissionEnum.MODERATOR.getRoleCode());
        allRole.add(PermissionEnum.CONTRACT.getRoleCode());
        allRole.add(PermissionEnum.SALE.getRoleCode());
    }

    /**
     * (Account Management Page) API Initial DataGird
     *
     * @param token
     * @param page
     * @param size
     * @param sortByProperties
     * @param sortBy
     * @return
     */
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp load trang Quản lí tài khoản",
            value = "(Account Management Page) API Initial DataGird",
            authorizations = {
                @Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Data Response Retrieved.", response = UserBasicDto.class)
        ,
      @ApiResponse(code = 500, message = "Internal Server Error.")
        ,
      @ApiResponse(code = 400, message = "Bad Request cause data input.")
        ,
      @ApiResponse(code = 404, message = "Not found.")
        ,
      @ApiResponse(code = 403, message = "Access Denied Or Any More.")
        ,
      @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<Page<UserBasicDto>> initData(
            @RequestHeader(value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME,
                    required = true) String token,
            @ApiParam(value = "Số trang", defaultValue = "0") @RequestParam(name = "page",
                    defaultValue = "0") int page,
            @ApiParam(value = "Số bản ghi / trang", defaultValue = "10") @RequestParam(name = "size",
                    defaultValue = "10") int size,
            @ApiParam(value = "Sắp xếp theo các thuộc tính", defaultValue = "userId") @RequestParam(
                    name = "properties", defaultValue = "userId") String sortByProperties,
            @ApiParam(value = "Loại sắp xếp ", defaultValue = "ASC") @RequestParam(name = "sortBy",
                    defaultValue = "ASC") String sortBy) {

        return ResponseEntity.ok(acountManagement.initDataGirdAccount(page, size, sortByProperties,
                sortBy, ApplicationUtils.getCurrentUser()));
    }

    /**
     * (Account Management Page) API Get Information A User.
     *
     * @param token
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp load thông tin 1 user để sửa tài khoản",
            value = "(Account Management Page) API Get Information A User ",
            authorizations = {
                @Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Data Response Retrieved.",
                response = UserUpdateDto.class)
        ,
      @ApiResponse(code = 500, message = "Internal Server Error.")
        ,
      @ApiResponse(code = 400, message = "Bad Request cause data input.")
        ,
      @ApiResponse(code = 404, message = "Not found.")
        ,
      @ApiResponse(code = 403, message = "Access Denied Or Any More.")
        ,
      @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<?> getUserbyId(
            @RequestHeader(value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME,
                    required = true) String token,
            @ApiParam(value = "User Id", defaultValue = "0") @PathVariable(value = "userId",
                    required = true) Long userId) {

        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(acountManagement.getUserById(userId)).build());
    }

    @GetMapping("/profile")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp load page profile",
            value = "(Account Management Page) API Get Information Profile ",
            authorizations = {
                @Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Data Response Retrieved.",
                response = UserUpdateDto.class)
        ,
      @ApiResponse(code = 500, message = "Internal Server Error.")
        ,
      @ApiResponse(code = 400, message = "Bad Request cause data input.")
        ,
      @ApiResponse(code = 404, message = "Not found.")
        ,
      @ApiResponse(code = 403, message = "Access Denied Or Any More.")
        ,
      @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<?> getProfile(@RequestHeader(
            value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME, required = true) String token) {

        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(acountManagement.getUserById(ApplicationUtils.getCurrentUser().getUserId())).build());
    }

    @GetMapping(value = "/init-sub")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp load sub account ",
            value = "(Account Management Page) API Get Sub Account",
            authorizations = {
                @Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Data Response Retrieved.", response = UserBasicDto.class)
        ,
      @ApiResponse(code = 500, message = "Internal Server Error.")
        ,
      @ApiResponse(code = 400, message = "Bad Request cause data input.")
        ,
      @ApiResponse(code = 404, message = "Not found.")
        ,
      @ApiResponse(code = 403, message = "Access Denied Or Any More.")
        ,
      @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<List<UserBasicDto>> initDataGirdSub(
            @RequestHeader(value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME,
                    required = true) String token,
            @ApiParam(value = "Id của User", defaultValue = "0") @RequestParam(name = "createdByUserId",
                    required = true) Long createdByUserId) {

        return ResponseEntity.ok(acountManagement.initDataGirdSubAccount(createdByUserId));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp tạo mới account ",
            value = "(Account Management Page) API Register Account",
            authorizations = {
                @Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Data Response Retrieved.", response = String.class)
        ,
      @ApiResponse(code = 500, message = "Internal Server Error.")
        ,
      @ApiResponse(code = 400, message = "Bad Request cause data input.")
        ,
      @ApiResponse(code = 404, message = "Not found.")
        ,
      @ApiResponse(code = 403, message = "Access Denied Or Any More.")
        ,
      @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<?> create(
            @ApiParam(value = "JSon Object để tạo tài khoản") @Valid @RequestBody UserRegisterDto userDto,
            @RequestHeader(value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME,
                    required = true) String token)
            throws PermissionException, IllegalArgumentException, IllegalAccessException, ExpiredResourcesDateException, UsernameNotFoundException {

        validateBeforeCreateAccount(userDto);

        // Check Username
        List<User> userCheckUsernames
                = acountManagement.checkAlreadyUsernameExist(userDto.getUsername());
        if (!userCheckUsernames.isEmpty()) {
            return new ResponseEntity<>(ResponseBody.builder()
                    .message(messageSourceVi.getMessageVi(ER002, new Object[]{userDto.getUsername()}))
                    .build(), HttpStatus.CONFLICT);
        }

        if (!ObjectUtils.isEmpty(userDto.getEmail())) {
            // Check email
            List<User> userCheckEmails
                    = acountManagement.checkAlreadyEmailExist(userDto.getEmail().toLowerCase());

            if (!userCheckEmails.isEmpty()) {
                return new ResponseEntity<>(ResponseBody.builder().message(
                        messageSourceVi.getMessageVi(ER002, new Object[]{userDto.getEmail().toLowerCase()}))
                        .build(), HttpStatus.CONFLICT);
            }
        }
        // Check Phone
        if (!ObjectUtils.isEmpty(userDto.getPhoneNumber())) {

            List<User> userCheckPhoness
                    = acountManagement.checkAlreadyPhoneExist(userDto.getPhoneNumber().toLowerCase());
            if (!userCheckPhoness.isEmpty()) {
                return new ResponseEntity<>(
                        ResponseBody.builder()
                                .message(messageSourceVi.getMessageVi(ER002,
                                        new Object[]{userDto.getPhoneNumber().toLowerCase()}))
                                .build(),
                        HttpStatus.CONFLICT);
            }
        }

        User userCreate = acountManagement.createNewAccount(userDto);

        return ResponseEntity
                .ok(ResponseBody.builder().body(userCreate).message(messageSourceVi.getMessageVi("OK001")).build());

    }

    /**
     * @param userDto
     * @throws PermissionException
     */
    private void validateBeforeCreateAccount(UserRegisterDto userDto) throws PermissionException {
        List<String> negativeRoleOfModerator = new ArrayList<>();
        negativeRoleOfModerator.add(PermissionEnum.ADMIN.getRoleCode());
        negativeRoleOfModerator.add(PermissionEnum.MODERATOR.getRoleCode());
        negativeRoleOfModerator.add(PermissionEnum.SALE.getRoleCode());

        List<String> negativeRoleOfContractOwner = new ArrayList<>();
        negativeRoleOfContractOwner.add(PermissionEnum.ADMIN.getRoleCode());
        negativeRoleOfContractOwner.add(PermissionEnum.MODERATOR.getRoleCode());
        negativeRoleOfContractOwner.add(PermissionEnum.CONTRACT.getRoleCode());

        User user = ApplicationUtils.getCurrentUser();
        String role = ApplicationUtils.getRoleCurrentUser().get(0);
        String roleOfNewUser = userDto.getRoleCode().trim();

        if (!allRole.contains(roleOfNewUser)
                || PermissionEnum.ADMIN.getRoleCode().equals(role)
                && PermissionEnum.ADMIN.getRoleCode().equals(roleOfNewUser)
                || PermissionEnum.MODERATOR.getRoleCode().equals(role)
                && negativeRoleOfModerator.contains(roleOfNewUser)
                || PermissionEnum.CONTRACT.getRoleCode().equals(role)
                && negativeRoleOfContractOwner.contains(roleOfNewUser)
                || PermissionEnum.SALE.getRoleCode().equals(role)
                && !PermissionEnum.SALE.getRoleCode().equals(roleOfNewUser)) {
            throw new PermissionException(messageSourceVi.getMessageVi("ER006"));

        } 
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp update account ",
            value = "(Account Management Page) API Update Account",
            authorizations = {
                @Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Data Response Retrieved.", response = String.class)
        ,
      @ApiResponse(code = 500, message = "Internal Server Error.")
        ,
      @ApiResponse(code = 400, message = "Bad Request cause data input.")
        ,
      @ApiResponse(code = 404, message = "Not found.")
        ,
      @ApiResponse(code = 403, message = "Access Denied Or Any More.")
        ,
      @ApiResponse(code = 401, message = "Unauthorized.")})
    @Transactional
    public ResponseEntity<?> update(@ApiParam(
            value = "JSon Object để cập nhật tài khoản") @Valid @RequestBody UserUpdateDto userUpdateDto,
            @RequestHeader(value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME,
                    required = true) String token)
            throws UsernameNotFoundException, PermissionException, ExpiredResourcesDateException {
        User userOld = acountManagement.findUserById(userUpdateDto.getUserId());

        if (null == userOld) {
            throw new UsernameNotFoundException(messageSourceVi.getMessageVi("USERNAME_NOT_FOUND_MSG"));
        }

        // Validate
        if (!ObjectUtils.isEmpty(userUpdateDto.getEmail()) && (null != userOld.getEmail()
                && !userOld.getEmail().equalsIgnoreCase(userUpdateDto.getEmail()))) {
            // Check email
            List<User> userCheckEmail
                    = acountManagement.checkAlreadyEmailExist(userUpdateDto.getEmail().toLowerCase());

            if (!userCheckEmail.isEmpty()) {
                return new ResponseEntity<>(
                        ResponseBody.builder()
                                .message(messageSourceVi.getMessageVi(ER002,
                                        new Object[]{userUpdateDto.getEmail().toLowerCase()}))
                                .build(),
                        HttpStatus.CONFLICT);
            }
        }
        // Check Phone
        if (!ObjectUtils.isEmpty(userUpdateDto.getPhoneNumber()) && (null != userOld.getPhoneNumber()
                && !userOld.getPhoneNumber().equalsIgnoreCase(userUpdateDto.getPhoneNumber()))) {

            List<User> userCheckPhone
                    = acountManagement.checkAlreadyPhoneExist(userUpdateDto.getPhoneNumber().toLowerCase());
            if (!userCheckPhone.isEmpty()) {
                return new ResponseEntity<>(
                        ResponseBody.builder()
                                .message(messageSourceVi.getMessageVi(ER002,
                                        new Object[]{userUpdateDto.getPhoneNumber().toLowerCase()}))
                                .build(),
                        HttpStatus.CONFLICT);
            }
        }

        User userUpdated = acountManagement.updateAccount(userUpdateDto, userOld);
        return ResponseEntity
                .ok(ResponseBody.builder().body(userUpdated).message(messageSourceVi.getMessageVi("OK007")).build());
    }

    @PutMapping("/change-pass")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp update password ",
            value = "(Account Management Page) API Update Password",
            authorizations = {
                @Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Data Response Retrieved.", response = String.class)
        ,
      @ApiResponse(code = 500, message = "Internal Server Error.")
        ,
      @ApiResponse(code = 400, message = "Bad Request cause data input.")
        ,
      @ApiResponse(code = 404, message = "Not found.")
        ,
      @ApiResponse(code = 403, message = "Access Denied Or Any More.")
        ,
      @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<?> changePassword(@ApiParam(
            value = "JSon object để cập nhật mật khẩu") @Valid @RequestBody UserChangePass userChangePass,
            @RequestHeader(value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME,
                    required = true) String token)
            throws PermissionException {

        User user = acountManagement.updatePassword(userChangePass);
        return ResponseEntity
                .ok(ResponseBody.builder().body(user).message(messageSourceVi.getMessageVi(OK002)).build());

    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp delete account ",
            value = "(Account Management Page) API Delete Account",
            authorizations = {
                @Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Data Response Retrieved.", response = String.class)
        ,
      @ApiResponse(code = 500, message = "Internal Server Error.")
        ,
      @ApiResponse(code = 400, message = "Bad Request cause data input.")
        ,
      @ApiResponse(code = 404, message = "Not found.")
        ,
      @ApiResponse(code = 403, message = "Access Denied Or Any More.")
        ,
      @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<?> deleteAccount(
            @ApiParam(
                    value = "JSon object để xóa tài khoản") @Valid @RequestBody UserBasicDto userBasicDto,
            @RequestHeader(value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME,
                    required = true) String token)
            throws PermissionException {

        String role = ApplicationUtils.getRoleCurrentUser().get(0);
        if (role.equalsIgnoreCase(PermissionEnum.ADMIN.getRoleCode())) {
            User user = acountManagement.deleteAccount(userBasicDto);

            return ResponseEntity
                    .ok(ResponseBody.builder().body(user).message(messageSourceVi.getMessageVi("OK003")).build());
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/login")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp login ",
            value = "(Account Management Page) API Login")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Data Response Retrieved.",
                response = JwtAuthenticationResponse.class)
        ,
      @ApiResponse(code = 500, message = "Internal Server Error.")
        ,
      @ApiResponse(code = 400, message = "Bad Request cause data input.")
        ,
      @ApiResponse(code = 404, message = "Not found.")
        ,
      @ApiResponse(code = 403, message = "Access Denied Or Any More.")
        ,
      @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<?> login(
            @ApiParam(value = "JSon object để đăng nhập") @Valid @RequestBody UserLoginDto userLoginDto,
            @RequestHeader(value = "pwd", required = true,defaultValue = "YWRtaW4=") String pwd, HttpServletRequest request)
            throws LoginFailedException {
//        if (userLoginDto.getDomain() != null && userLoginDto.getDomain().startsWith(domain)) {
//            userLoginDto.setPwd(pwd);
//        } else {
//            userLoginDto.setPwd(EncryptUtils.decryptBase64(pwd));
//        }
        userLoginDto.setPwd(EncryptUtils.decryptBase64(pwd));
        userLoginDto.setIp(request.getRemoteAddr());
        userLoginDto.setUserAgent(request.getHeader("User-Agent"));

        log.info(userLoginDto.getUname() + "|login");
        return ResponseEntity.ok(ResponseBody.builder().body(acountManagement.login(userLoginDto))
                .message(messageSourceVi.getMessageVi(OK002)).build());
    }

    @GetMapping("/logout")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp Logout ",
            value = "(Account Management Page) API Logout",
            authorizations = {
                @Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Data Response Retrieved.", response = String.class)
        ,
      @ApiResponse(code = 500, message = "Internal Server Error.")
        ,
      @ApiResponse(code = 400, message = "Bad Request cause data input.")
        ,
      @ApiResponse(code = 404, message = "Not found.")
        ,
      @ApiResponse(code = 403, message = "Access Denied Or Any More.")
        ,
      @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<?> logout(@RequestHeader(
            value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME, required = true) String token) {

        log.info(ApplicationUtils.getCurrentUser().getUsername() + "|logout");
        userSessionService.delete(ApplicationUtils.getCurrentUser().getUserId());
        return ResponseEntity.ok(messageSourceVi.getMessageVi(OK002));
    }

    @GetMapping("/role")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp get list role của User current ",
            value = "(Account Management Page) API Get List<Role>",
            authorizations = {
                @Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Data Response Retrieved.", response = RoleDto.class)
        ,
      @ApiResponse(code = 500, message = "Internal Server Error.")
        ,
      @ApiResponse(code = 400, message = "Bad Request cause data input.")
        ,
      @ApiResponse(code = 404, message = "Not found.")
        ,
      @ApiResponse(code = 403, message = "Access Denied Or Any More.")
        ,
      @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<?> getRolesByUser(@RequestHeader(
            value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME, required = true) String token) {

        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK005))
                .body(acountManagement.getRoleDtoByUser(ApplicationUtils.getCurrentUser())).build());
    }

    @PostMapping("/search")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp Search account ",
            value = "(Account Management Page) API Search account ",
            authorizations = {
                @Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Data Response Retrieved.", response = UserBasicDto.class)
        ,
      @ApiResponse(code = 500, message = "Internal Server Error.")
        ,
      @ApiResponse(code = 400, message = "Bad Request cause data input.")
        ,
      @ApiResponse(code = 404, message = "Not found.")
        ,
      @ApiResponse(code = 403, message = "Access Denied Or Any More.")
        ,
      @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<?> search(
            @ApiParam(value = "Số trang", defaultValue = "0") @RequestParam(name = "page",
                    defaultValue = "0") int page,
            @ApiParam(value = "Số bản ghi / trang", defaultValue = "10") @RequestParam(name = "size",
                    defaultValue = "10") int size,
            @ApiParam(value = "Sắp xếp theo các thuộc tính", defaultValue = "userId") @RequestParam(
                    name = "properties", defaultValue = "userId", required = true) String sortByProperties,
            @ApiParam(value = "Loại sắp xếp ", defaultValue = "ASC") @RequestParam(name = "sortBy",
                    defaultValue = "ASC") String sortBy,
            @ApiParam(
                    value = "Tìm kiếm theo các tiêu chí ") @Valid @RequestBody UserSearchDto userSearchDto,
            @RequestHeader(value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME,
                    required = true) String token) {

        log.info(ApplicationUtils.getCurrentUser().getUsername() + "|search|" + userSearchDto.toString());
        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(acountManagement.searchAccount(page, size, sortByProperties, sortBy, userSearchDto,
                        ApplicationUtils.getCurrentUser().getUserRoleList().get(0).getRole().getRoleLevel()))
                .build());
    }

    @GetMapping("/suggest-name")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp suggest username ",
            value = "(Account Management Page) API Suggest Username ",
            authorizations = {
                @Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Data Response Retrieved.", response = ArrayList.class)
        ,
      @ApiResponse(code = 500, message = "Internal Server Error.")
        ,
      @ApiResponse(code = 400, message = "Bad Request cause data input.")
        ,
      @ApiResponse(code = 404, message = "Not found.")
        ,
      @ApiResponse(code = 403, message = "Access Denied Or Any More.")
        ,
      @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<?> suggestUsername(
            @RequestHeader(value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME,
                    required = true) String token,
            //            @ApiParam(value = "Tên tài khoản", defaultValue = "") @RequestParam(name = "name",
            //                    defaultValue = StringUtils.EMPTY, required = false) String username,
            @ApiParam(value = "Role Code đang được chọn", defaultValue = "") @RequestParam(
                    name = "roleCodeSelected", defaultValue = StringUtils.EMPTY) String roleCodeSelected) {

        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK005))
                .body(acountManagement.suggestUsername(ApplicationUtils.getCurrentUser(), roleCodeSelected))
                .build());

//        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK005))
//                .body(acountManagement.suggestUsername(ApplicationUtils.getCurrentUser(),
//                        ApplicationUtils.getRoleCurrentUser().get(0), username, roleCodeSelected))
//                .build());
    }

    @GetMapping(value = "/refresh-token")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp refresh token. ",
            value = "(Account Management Page) API refresh token.",
            authorizations = {
                @Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Data Response Retrieved.",
                response = JwtAuthenticationResponse.class)
        ,
      @ApiResponse(code = 500, message = "Internal Server Error.")
        ,
      @ApiResponse(code = 400, message = "Bad Request cause data input.")
        ,
      @ApiResponse(code = 404, message = "Not found.")
        ,
      @ApiResponse(code = 403, message = "Access Denied Or Any More.")
        ,
      @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<?> refreshToken(@RequestHeader(
            value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME, required = true) String token,
            @ApiParam(value = "type") @RequestParam(name = "type",
                    defaultValue = "", required = false) String type
    ) {
        String userToken = jwtTokenProvider.getJwtFromHeader(token);
        JwtAuthDto jwtAuthDto = jwtTokenProvider.validateToken(userToken) ? jwtTokenProvider.getJWTInfor(userToken) : new JwtAuthDto();
        return ResponseEntity.ok(
                ResponseBody.builder().body(acountManagement.refreshToken(ApplicationUtils.getPrincipal()))
                        .message(messageSourceVi.getMessageVi(OK002)).build());
    }


    public boolean getAuth(HttpServletRequest request) {
        String bearerToken = request.getHeader(ApplicationConstant.AUTHENTICATION_SCHEME_NAME);
        bearerToken = bearerToken.substring(ApplicationConstant.AUTH_CRM.length(),
                bearerToken.length());
        if (bearerToken.equals(token)) {
            return true;
        } else {
            return false;
        }
    }

}
