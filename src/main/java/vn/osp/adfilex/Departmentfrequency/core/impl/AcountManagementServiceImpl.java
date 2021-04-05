package vn.osp.adfilex.Departmentfrequency.core.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import vn.osp.adfilex.Departmentfrequency.authencation.CustomUserDetails;
import vn.osp.adfilex.Departmentfrequency.authencation.JwtAuthenticationResponse;
import vn.osp.adfilex.Departmentfrequency.authencation.JwtTokenProvider;
import vn.osp.adfilex.Departmentfrequency.config.MessageSoucreConfig.MessageSourceVi;
import vn.osp.adfilex.Departmentfrequency.constants.ApplicationConstant;
import vn.osp.adfilex.Departmentfrequency.constants.PermissionEnum;
import vn.osp.adfilex.Departmentfrequency.constants.StatusAccountEnum;
import vn.osp.adfilex.Departmentfrequency.core.AcountManagementService;
import vn.osp.adfilex.Departmentfrequency.entity.Role;
import vn.osp.adfilex.Departmentfrequency.entity.User;
import vn.osp.adfilex.Departmentfrequency.entity.UserRole;
import vn.osp.adfilex.Departmentfrequency.entity.UserSession;
import vn.osp.adfilex.Departmentfrequency.exception.ExpiredResourcesDateException;
import vn.osp.adfilex.Departmentfrequency.exception.LoginFailedException;
import vn.osp.adfilex.Departmentfrequency.exception.PermissionException;
import vn.osp.adfilex.Departmentfrequency.mapper.MapperObject;
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
import vn.osp.adfilex.Departmentfrequency.repository.RoleRepository;
import vn.osp.adfilex.Departmentfrequency.repository.UserRepository;
import vn.osp.adfilex.Departmentfrequency.repository.UserRepositoryCustom;
import vn.osp.adfilex.Departmentfrequency.repository.UserRoleRepository;
import vn.osp.adfilex.Departmentfrequency.repository.UserSessionRepository;
import vn.osp.adfilex.Departmentfrequency.utils.ApplicationUtils;
import vn.osp.adfilex.Departmentfrequency.utils.DateUtils;
import vn.osp.adfilex.Departmentfrequency.utils.StringUtils;

/**
 *
 * @author Nguyen_Toan
 */
@Service
@Primary
@Slf4j
@Qualifier("AcountManagementService_Main")
public class AcountManagementServiceImpl implements AcountManagementService {

    @Value(value = "${account.management}")
    private String accountManager;

    @Value(value = "${password.management}")
    private String passwordManager;

    @Value(value = "${email.management}")
    private String emailManager;

    @Value(value = "${firstname.management}")
    private String firstNameManager;

    @Value(value = "${lastname.management}")
    private String lastNameManager;

    @Value(value = "${organization.management}")
    private String organizationManager;

    @Value(value = "${phonenumber.management}")
    private String phoneNumberManager;

    @Value(value = "${timeAllow}")
    private String timeAllow;

    @Value(value = "${login.domain.ecocrm}")
    private String domain;

    @Value("${url_ecocrm}")
    private String urlEcocrm;

    @Value("${api_ecocrm_update_user}")
    private String apiEcocrmUpdateUser;

    @Autowired
    private MessageSourceVi messageSourceVi;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepositoryCustom userRepositoryCustom;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    private final long dateNow = DateUtils.getDateAllEndDate(new Date()).getTime();

    @Bean
    public CommandLineRunner initialAccountAdminAndApplication() {

        return (args) -> {
            if (Optional.empty().equals(userRepository.findById(Long.valueOf(1)))) {
                Role role = roleRepository.findByRoleCode(PermissionEnum.ADMIN.getRoleCode());
                User user = User.builder().email(emailManager).firstName(firstNameManager).lastName(lastNameManager)
                        .password(passwordEncoder.encode(passwordManager)).phoneNumber(phoneNumberManager)
                        .createByUserId(Long.valueOf(0))
                        .status(StatusAccountEnum.ACTIVE.getStatus()).username(accountManager)
                        //                        .expiredResourcesDate(new SimpleDateFormat(StringUtils.DF_DD_MM_YYYY).parse("31/12/9999"))
                        .userId(Long.valueOf(1)).build();

                UserRole userRole = UserRole.builder().role(role).user(userRepository.save(user))
                        .userRoleId(Long.valueOf(1)).build();

                userRoleRepository.save(userRole);
                log.info("Created account management.");
            } else {
                log.info("Account management already exsit.");
            }

        };
    }

    /**
     * @param session
     * @param userLogin
     */
    private synchronized UserSession getUserSession(String session, User userLogin) {
        UserSession userSession = userSessionRepository.findAllByUserUserId(userLogin.getUserId());
        if (null != userSession) {
            userSession.setSession(session);
        } else {
            userSession = UserSession.builder().session(session).user(userLogin).build();
        }
        return userSession;

    }

    @Override
    @Transactional
    public synchronized User createNewAccount(UserRegisterDto userBasicDto) throws PermissionException {

        User userRegister = checkAndUpdateBranches(userBasicDto);

        User newUserSaved = userRepository.save(userRegister);

        userRoleRepository.save(UserRole.builder().user(newUserSaved)
                .role(roleRepository.findByRoleCode(userBasicDto.getRoleCode().trim())).build());

        return newUserSaved;
    }

    /**
     * @param userBasicDto
     * @return
     * @throws PermissionException
     */
    private User checkAndUpdateBranches(UserRegisterDto userBasicDto) throws PermissionException {
        User userRegister = MapperObject.userBuilder(userBasicDto);

        userRegister.setStatus(userBasicDto.getStatus());
        userRegister.setPassword(passwordEncoder.encode(userBasicDto.getPassword()));
        userRegister.setCreateByUserId(ApplicationUtils.getCurrentUser().getUserId());

        String roleCurrent = ApplicationUtils.getRoleCurrentUser().get(0);
        User userCurrent = ApplicationUtils.getCurrentUser();

        return userRegister;
    }

    @Override
    public User lockAccount(UserBasicDto userBasicDto) {
        User user = modelMapper.map(userBasicDto, User.class);
        user.setStatus(StatusAccountEnum.LOCK.getStatus());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updatePassword(UserChangePass userChangePass) throws PermissionException {

        try {
            User userCurrent = ApplicationUtils.getCurrentUser();
            String role = ApplicationUtils.getCurrentUser().getUserRoleList().get(0).getRole().getRoleCode();
            User user = null;

            if (!PermissionEnum.ADMIN.getRoleCode().equals(role)
                    && !passwordEncoder.matches(userChangePass.getPasswordCurrent(), userCurrent.getPassword())) {

                throw new PermissionException(messageSourceVi.getMessageVi("MSG_PW_OLD_INVALID"));

            } else if (passwordEncoder.matches(userChangePass.getPasswordNew(), userCurrent.getPassword())) {

                throw new PermissionException(messageSourceVi.getMessageVi("MSG_PW_OLD_NEW_INVALID"));
            }

            user = userRepository.findByUsernameIgnoreCase(userChangePass.getUsername());
            user.setPassword(passwordEncoder.encode(userChangePass.getPasswordNew()));
            User userChange = userRepository.save(user);

//            sendAddFavoriteToCrm(sendUserCrm);
            return userChange;
        } catch (Exception ex) {
            Logger.getLogger(AcountManagementServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    @Transactional
    public User deleteAccount(UserBasicDto userBasicDto) {
        User user = userRepository.findByIdCustom(userBasicDto.getUserId());
        if (user != null) {
            try {
                user.setStatus(StatusAccountEnum.DELETE.getStatus());
                User user1 = userRepository.save(user);
//                sendAddFavoriteToCrm(sendUserCrm);

                return user1;
            } catch (Exception ex) {
                Logger.getLogger(AcountManagementServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return null;
        //userRepository.deleteByUserIdAndUsername(userBasicDto.getUserId(), userBasicDto.getUsername());
    }

    @Override
    public List<RoleDto> getRoleDtoByUser(User user) {
        return roleRepository
                .findByUserCurrent(ApplicationUtils.getCurrentUser().getUserRoleList().get(0).getRole().getRoleLevel())
                .stream().map(aRole -> modelMapper.map(aRole, RoleDto.class)).collect(Collectors.toList());
    }

    @Override
    public Page<UserBasicDto> initDataGirdAccount(int pageNumber, int size, String sortByProperties, String sortBy,
            User user) {

        Sort sort = ApplicationUtils.getSort(sortByProperties, sortBy);
        Pageable pageable = PageRequest.of(pageNumber, size, sort);
        String roleCode = user.getUserRoleList().get(0).getRole().getRoleCode();
        Page<User> page = null;
        List<UserBasicDto> userBasicDtos = null;
        switch (roleCode) {
            case ApplicationConstant.ADMIN:
                page = userRepository.findAll(StatusAccountEnum.DELETE.getStatus(), pageable);
                userBasicDtos = page.getContent().stream().map(MapperObject::userBasicDtoBuilder)
                        .collect(Collectors.toList());
                return new PageImpl<>(userBasicDtos, pageable, page.getTotalElements());
            default:
                return new PageImpl<>(Collections.emptyList());
        }

    }

    @Override
    public List<UserBasicDto> initDataGirdSubAccount(Long createdByUserId) {
        return userRepository.findAllUserByCreateByUserId(createdByUserId).stream()
                .map(MapperObject::userBasicDtoBuilder).collect(Collectors.toList());
    }

    @Override
    public Page<UserBasicDto> searchAccount(int pageNumber, int size, String sortByProperties, String sortBy,
            UserSearchDto userSearchDto, Integer roleLevel) {

        Sort sort = ApplicationUtils.getSort(sortByProperties, sortBy);
        Pageable pageable = PageRequest.of(pageNumber, size, sort);
        List<UserBasicDto> userBasicDtos = null;
        Page<User> page = null;

        String role = ApplicationUtils.getRoleCurrentUser().get(0);
        boolean findByListUsername = (null != userSearchDto.getUsernameList())
                ? !userSearchDto.getUsernameList().isEmpty()
                : false;

        page = ApplicationConstant.ADMIN.equals(role)
                ? searchForAdmin(userSearchDto, roleLevel, pageable, findByListUsername)
                : searchForModAndCon(userSearchDto, roleLevel, pageable, findByListUsername);

        userBasicDtos = page.getContent().stream().map(MapperObject::userBasicDtoBuilder).collect(Collectors.toList());

        return new PageImpl<>(userBasicDtos, pageable, page.getTotalElements());

    }

    private Page<User> searchForModAndCon(UserSearchDto userSearchDto, Integer roleLevel, Pageable pageable,
            boolean findByListUsername) {

        return findByListUsername
                ? userRepository.searchByUserCurrent(StatusAccountEnum.DELETE.getStatus(), ApplicationUtils.getCurrentUser().getUserId(),
                        userSearchDto.getUsernameList(), roleLevel, userSearchDto.getStatus(),
                        userSearchDto.getCreatedDateFrom(), userSearchDto.getCreatedDateTo(),
                        userSearchDto.getRoleCode(), pageable)
                : userRepository.searchUser(StatusAccountEnum.DELETE.getStatus(), ApplicationUtils.getCurrentUser().getUserId(), roleLevel,
                        userSearchDto.getStatus(), userSearchDto.getCreatedDateFrom(), userSearchDto.getCreatedDateTo(),
                        userSearchDto.getRoleCode(), pageable);
    }

    private Page<User> searchForAdmin(UserSearchDto userSearchDto, Integer roleLevel, Pageable pageable,
            boolean findByListUsername) {

        return findByListUsername
                ? userRepository.searchAllByUserCurrent(StatusAccountEnum.DELETE.getStatus(), userSearchDto.getUsernameList(), roleLevel,
                        userSearchDto.getStatus(), userSearchDto.getCreatedDateFrom(), userSearchDto.getCreatedDateTo(),
                        userSearchDto.getRoleCode(), pageable)
                : userRepository.searchAllUser(StatusAccountEnum.DELETE.getStatus(), roleLevel, userSearchDto.getStatus(), userSearchDto.getCreatedDateFrom(),
                        userSearchDto.getCreatedDateTo(), userSearchDto.getRoleCode(), pageable);
    }

    @Override
    public List<Map<String, String>> suggestUsername(User userCurrent, String roleUserCurrent, String username,
            String roleCodeSelected) {

        switch (roleUserCurrent) {
            case ApplicationConstant.ADMIN:

                Set<Map<String, String>> results = new HashSet<>(userRepository.suggestAllUser(userCurrent.getUserId(),
                        username.toLowerCase(),
                        !StringUtils.EMPTY.equalsIgnoreCase(roleCodeSelected) ? roleCodeSelected.toLowerCase() : null));
                if (ApplicationConstant.MODERATOR.equalsIgnoreCase(roleCodeSelected)) {

                    results.addAll(
                            userRepository.suggestUserByCreateByUserId(null, userCurrent.getUserId(), StringUtils.EMPTY));
                }

                return new ArrayList<>(results.stream().sorted(Comparator.comparing(map -> map.get(StringUtils.USER_NAME)))
                        .collect(Collectors.toList()));

            case ApplicationConstant.MODERATOR:

                return userRepository.suggestUserByCreateByUserId(
                        !StringUtils.EMPTY.equalsIgnoreCase(roleCodeSelected) ? roleCodeSelected.toLowerCase() : null,
                        userCurrent.getUserId(), username.toLowerCase());
            case ApplicationConstant.CONTRACT:

                return userRepository.suggestUserByCreateByUserId(
                        !StringUtils.EMPTY.equalsIgnoreCase(roleCodeSelected) ? roleCodeSelected.toLowerCase() : null,
                        userCurrent.getUserId(), username.toLowerCase());
            default:
                return Collections.emptyList();
        }
    }

    @Override
    public User updateAccount(UserUpdateDto userUpdateDto, User userOld)
            throws ExpiredResourcesDateException, PermissionException {

        try {
            // String roleUserCurrent = ApplicationUtils.getRoleCurrentUser().get(0);
            // Check password not change
            if (!StringUtils.FIVE_STAR.equals(userUpdateDto.getPassword())) {
                // if (!ApplicationConstant.ADMIN.equals(roleUserCurrent) || ) {
                // throw new PermissionException(StringUtils.CAN_NOT_UPDATE_PASSW);
                // }
                userOld.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));
            }

            String roleCodeUserNeedUpdate = userOld.getUserRoleList().get(0).getRole().getRoleCode();

            userOld.setFirstName(userUpdateDto.getFirstName());
            userOld.setLastName(userUpdateDto.getLastName());
            userOld.setEmail(userUpdateDto.getEmail());
            userOld.setPhoneNumber(userUpdateDto.getPhoneNumber());
            userOld.setStatus(userUpdateDto.getStatus());

        } catch (Exception ex) {
            Logger.getLogger(AcountManagementServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userRepository.save(userOld);
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> optional = userRepository.findById(userId);
        return optional.isPresent() ? optional.get() : null;

    }

    @Override
    public UserUpdateDto getUserById(Long userId) {

        User userForUpdate = userRepository.findByIdCustom(userId);
//        if (null != userForUpdate && userForUpdate.getUserRoleList().get(0).getRole().getRoleLevel() >= ApplicationUtils
//                .getCurrentUser().getUserRoleList().get(0).getRole().getRoleLevel()) {
        if (null != userForUpdate) {
            UserUpdateDto userUpdateDto = MapperObject.userUpdateDtoBuilder(userForUpdate);
            return userUpdateDto;
        }
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void disableOrLockAccountSub(User userParent, Integer status) {
        String role = userParent.getUserRoleList().get(0).getRole().getRoleCode();
        switch (role) {
            case ApplicationConstant.MODERATOR:

                userRepository.findAllUserByCreateByUserId(userParent.getUserId()).stream().forEach(us -> {
                    us.setStatus(status);
                    userRepository.findAllUserByCreateByUserId(us.getUserId()).stream()
                            .forEach(usSub -> usSub.setStatus(status));
                });

                break;
            case ApplicationConstant.CONTRACT:
                userRepository.findAllUserByCreateByUserId(userParent.getUserId()).stream()
                        .forEach(us -> us.setStatus(status));
                break;

            default:
                break;
        }

    }

    @Override
    public List<User> checkAlreadyEmailExist(String email) {
        return userRepository.checkAlreadyEmailExsit(email);
    }

    @Override
    public List<User> checkAlreadyPhoneExist(String phoneNumber) {
        return userRepository.checkAlreadyPhoneExsit(phoneNumber);
    }

    @Override
    public List<User> checkAlreadyUsernameExist(String username) {
        return userRepository.checkAlreadyUsernameExsit(username);
    }

    @Override
    public User findUserParentFromIdSub(Long userId) {
        return userRepository.findUserParentFromIdSub(userId);
    }

    @Override
    public JwtAuthenticationResponse refreshToken(CustomUserDetails customUserDetails) {
        // Kiểm tra nếu user tồn tại
        customUserDetails.setUser(userRepository.findByIdCustom(customUserDetails.getUser().getUserId()));
        // Kiểm tra nếu session tồn tại
        customUserDetails.setSession(userSessionRepository.findAllByUserUserId(customUserDetails.getUser().getUserId()).getSession());

        return JwtAuthenticationResponse.builder().accessToken(tokenProvider.generateToken(customUserDetails)).build();
    }

    @Override
    public List<UserDto> findAllAccount() {
        String role = ApplicationUtils.getRoleCurrentUser().get(0);
        User user = ApplicationUtils.getCurrentUser();
        if (!role.equalsIgnoreCase(PermissionEnum.ADMIN.getRoleCode())) {
            List<UserDto> userDtos = new ArrayList<>();
            List<User> list = userRepository.findUserByCreateByUser(user.getUserId(), StatusAccountEnum.ACTIVE.getStatus());
            userDtos = list.stream().map(au -> modelMapper.map(au, UserDto.class)).collect(Collectors.toList());
            userDtos.add(new UserDto(user.getUserId(), user.getUsername()));

            return userDtos;
        }
        return userRepository
                .findAllAccount(StatusAccountEnum.ACTIVE.getStatus())
                .stream().map(au -> modelMapper.map(au, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public JwtAuthenticationResponse login(UserLoginDto userLoginDto) throws LoginFailedException {
        Authentication authentication = null;

        try {
            User user = userRepository.findByUsernameIgnoreCase(userLoginDto.getUname());
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userLoginDto.getUname().toLowerCase(), userLoginDto.getPwd()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (BadCredentialsException e) {
            log.error(userLoginDto.getUname() + "|" + e.getMessage());
            throw new LoginFailedException(messageSourceVi.getMessageVi("MSG_LOGIN_FAILED"));
        } catch (LockedException ex) {
            log.error(userLoginDto.getUname() + "|" + ex.getMessage());
            throw new LoginFailedException(messageSourceVi.getMessageVi("MSG_LOCKED_ACCOUNT"));
        } catch (DisabledException exx) {
            log.error(userLoginDto.getUname() + "|" + exx.getMessage());
            throw new LoginFailedException(messageSourceVi.getMessageVi("MSG_DISABLE_ACCOUNT"));
        }

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        String session = servletRequestAttributes.getSessionId();

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        User userLogin = customUserDetails.getUser();
        customUserDetails.setSession(session);
        customUserDetails.setIp(userLoginDto.getIp());
        customUserDetails.setUsAgent(userLoginDto.getUserAgent());

        UserSession userSession = getUserSession(session, userLogin);
        userSessionRepository.save(userSession);

        return JwtAuthenticationResponse.builder().accessToken(tokenProvider.generateToken(customUserDetails)).build();
    }

    @Override
    public String getPassword(String username) {
        User user = userRepository.findByUsernameIgnoreCase(username);
        return user.getPassword();
    }

    @Override
    public List<UserForSuggest> suggestUsername(User user, String roleCode) {
        String roleCodeQ = null;
        if (roleCode.equals("")) {
            roleCodeQ = null;
        } else {
            roleCodeQ = roleCode;
        }
        String role = ApplicationUtils.getRoleCurrentUser().get(0);
        List<UserForSuggest> userForSuggests = new ArrayList<>();

        if (!Strings.isNullOrEmpty(roleCodeQ)) {
            if (role.equals(PermissionEnum.ADMIN.getRoleCode())) {
                userForSuggests = userRepository.findAllUserAdmin(roleCodeQ, StatusAccountEnum.DELETE.getStatus(), PermissionEnum.ADMIN.getRoleCode()).stream().map(aRole -> modelMapper.map(aRole, UserForSuggest.class))
                        .collect(Collectors.toList());
                return userForSuggests;

            } else {
                if (role.equals(PermissionEnum.MODERATOR.getRoleCode())) {
                    List<UserForSuggest> forSuggestsCon = userRepository.findAllUser(null, user.getUserId().intValue(), StatusAccountEnum.DELETE.getStatus()).stream().map(aRole -> modelMapper.map(aRole, UserForSuggest.class))
                            .collect(Collectors.toList());
                    if (roleCodeQ.equalsIgnoreCase(PermissionEnum.CONTRACT.getRoleCode())) {
                        return forSuggestsCon;
                    } else {
                        for (UserForSuggest userIdContract : forSuggestsCon) {
//                            // Add userId Contract to list
//                            userForSuggests.add(userIdContract);
                            // Add userId Sales to list
                            userForSuggests.addAll(userRepository.findAllUser(null, userIdContract.getUserId().intValue(), StatusAccountEnum.DELETE.getStatus()).stream().map(aRole -> modelMapper.map(aRole, UserForSuggest.class))
                                    .collect(Collectors.toList()));
                        }
                        return userForSuggests;
                    }
                } else {
                    return userRepository.findAllUser(null, user.getUserId().intValue(), StatusAccountEnum.DELETE.getStatus()).stream().map(aRole -> modelMapper.map(aRole, UserForSuggest.class))
                            .collect(Collectors.toList());
                }
            }
        } else {
            if (role.equals(PermissionEnum.ADMIN.getRoleCode())) {
                userForSuggests = userRepository.findAllUserAdmin(null, StatusAccountEnum.DELETE.getStatus(), PermissionEnum.ADMIN.getRoleCode()).stream().map(aRole -> modelMapper.map(aRole, UserForSuggest.class))
                        .collect(Collectors.toList());
                return userForSuggests;

            } else {
                if (role.equals(PermissionEnum.MODERATOR.getRoleCode())) {
                    List<UserForSuggest> forSuggestsCon = userRepository.findAllUser(null, user.getUserId().intValue(), StatusAccountEnum.DELETE.getStatus()).stream().map(aRole -> modelMapper.map(aRole, UserForSuggest.class))
                            .collect(Collectors.toList());

                    for (UserForSuggest userIdContract : forSuggestsCon) {
                        // Add userId Contract to list
                        userForSuggests.add(userIdContract);
                        // Add userId Sales to list
                        userForSuggests.addAll(userRepository.findAllUser(null, userIdContract.getUserId().intValue(), StatusAccountEnum.DELETE.getStatus()).stream().map(aRole -> modelMapper.map(aRole, UserForSuggest.class))
                                .collect(Collectors.toList()));
                    }
                    return userForSuggests;
                } else {
                    return userRepository.findAllUser(null, user.getUserId().intValue(), StatusAccountEnum.DELETE.getStatus()).stream().map(aRole -> modelMapper.map(aRole, UserForSuggest.class))
                            .collect(Collectors.toList());
                }
            }
        }
    }

    private List<User> getPersons(Long userId) {
        return userRepository.findAllUserByCreateByUserIdActive(userId, StatusAccountEnum.DELETE.getStatus());
    }

    private List<User> getPersons1(Long userId, Date createdDateFrom, Date createdDateTo, List<String> usernameList) {
        return userRepository.findAllUserByCreateByUserIdActive1(userId, StatusAccountEnum.DELETE.getStatus(), createdDateFrom, createdDateTo, usernameList);
    }

    private List<User> getPersons2(Long userId, Date createdDateFrom, Date createdDateTo, List<String> usernameList) {
        return userRepository.findAllUserByCreateByUserIdActive2(userId, StatusAccountEnum.DELETE.getStatus(), createdDateFrom, createdDateTo, usernameList);
    }

}
