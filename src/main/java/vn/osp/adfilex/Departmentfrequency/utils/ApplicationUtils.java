package vn.osp.adfilex.Departmentfrequency.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
import vn.osp.adfilex.Departmentfrequency.authencation.CustomUserDetails;
import vn.osp.adfilex.Departmentfrequency.entity.User;
import vn.osp.adfilex.Departmentfrequency.exception.PermissionException;

@Slf4j
public class ApplicationUtils {

  private ApplicationUtils() {

  }

  public static User getCurrentUser() {
    try {
      return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
          .getPrincipal()).getUser();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return null;
    }

  }

  public static CustomUserDetails getPrincipal() {
    try {
      return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
          .getPrincipal());
    } catch (Exception e) {
      log.error("Here get null: " + e.getMessage(), e);
      return null;
    }

  }

  public static List<String> getRoleCurrentUser() {
    List<String> roles = new ArrayList<>();

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    for (GrantedAuthority auth : authentication.getAuthorities()) {
      roles.add(auth.getAuthority());
    }
    return roles;
  }

  public static Sort getSort(String sortByProperties, String sortBy) {
    Sort sort = null;
    if (Direction.ASC.toString().equals(sortBy)) {
      sort = Sort.by(sortByProperties.split(StringUtils.COMMA)).ascending();
    } else {
      sort = Sort.by(sortByProperties.split(StringUtils.COMMA)).descending();
    }
    return sort;
  }

  public static void checkHasRole(final String roleCode) throws PermissionException {
    if (null != roleCode
        && !roleCode.equalsIgnoreCase(ApplicationUtils.getRoleCurrentUser().get(0))) {
      throw new PermissionException("Xin lỗi bạn không có quyền.");
    }
  }

}
