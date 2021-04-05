package vn.osp.adfilex.Departmentfrequency.config.impl;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import vn.osp.adfilex.Departmentfrequency.authencation.CustomUserDetails;

/**
 * 
 * vn.osp.adfilex.config.impl 
 * @author LuongTN : 9:25:14 AM
 * 
 * 
 * AuditorAwareImpl.java
 */
public class AuditorAwareImpl implements AuditorAware<String> {


  @Override
  public Optional<String> getCurrentAuditor() {

    if (null != SecurityContextHolder.getContext().getAuthentication()) {
      return Optional.ofNullable(((CustomUserDetails) SecurityContextHolder.getContext()
          .getAuthentication().getPrincipal()).getUsername());
    }
    return Optional.ofNullable(System.getProperties().get("user.name").toString());

  }

}
