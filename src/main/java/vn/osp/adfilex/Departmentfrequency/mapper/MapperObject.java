package vn.osp.adfilex.Departmentfrequency.mapper;

import java.util.stream.Collectors;
import vn.osp.adfilex.Departmentfrequency.entity.User;
import vn.osp.adfilex.Departmentfrequency.model.AccountDto;
import vn.osp.adfilex.Departmentfrequency.model.account.UserBasicDto;
import vn.osp.adfilex.Departmentfrequency.model.account.UserRegisterDto;
import vn.osp.adfilex.Departmentfrequency.model.account.UserUpdateDto;
import vn.osp.adfilex.Departmentfrequency.utils.StringUtils;

public class MapperObject {

  private MapperObject() {

  }


  public static User userBuilder(UserRegisterDto userDto) {
    return User.builder().email(userDto.getEmail()).firstName(userDto.getFirstName())
        .lastName(userDto.getLastName()).password(userDto.getPassword())
        .phoneNumber(userDto.getPhoneNumber()).avatar(userDto.getAvatar())
        .userId(userDto.getUserId()).username(userDto.getUsername().toLowerCase())
        .address(userDto.getAddress()).build();
  }


  public static UserBasicDto userBasicDtoBuilder(User user) {
    return UserBasicDto.builder().userId(user.getUserId()).username(user.getUsername())
        .status(user.getStatus()).roleCode(user.getUserRoleList().get(0).getRole().getRoleCode())
        .createdDate(user.getCreatedDate())
        .build();
  }

  public static AccountDto accountDtoBuilder(User user) {
    return AccountDto.builder().userId(user.getUserId()).username(user.getUsername())
        .status(user.getStatus()).roleCode(user.getUserRoleList().get(0).getRole().getRoleCode())
        .createdDate(user.getCreatedDate())
        .build();
  }

  public static UserBasicDto userBasicDtoBuilderBasic(User user) {
    return UserBasicDto.builder().userId(user.getUserId()).username(user.getUsername()).build();
  }

  public static UserUpdateDto userUpdateDtoBuilder(User user) {
    return UserUpdateDto.builder().email(user.getEmail()).userId(user.getUserId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName()).phoneNumber(user.getPhoneNumber()).status(user.getStatus())
        .username(user.getUsername()).password(StringUtils.FIVE_STAR)
        .roleCode(!user.getUserRoleList().isEmpty() ? user.getUserRoleList().get(0).getRole().getRoleCode() : "")
      .usP(user.getCreateByUserId())
      .favDurationThreshold(user.getFavDurationThreshold())
      .build();
  }

}
