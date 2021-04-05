package vn.osp.adfilex.Departmentfrequency.authencation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import vn.osp.adfilex.Departmentfrequency.constants.StatusAccountEnum;
import vn.osp.adfilex.Departmentfrequency.entity.Role;
import vn.osp.adfilex.Departmentfrequency.entity.User;


/**
 *
 * @author Nguyen_Toan
 */
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@Transactional
public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private User user;
    private Set<Role> roleOfUser;
    private String ip;
    private String usAgent;
    private String session;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
//        if (!roleOfUser.isEmpty()) {
            roleOfUser.stream().map(role -> new SimpleGrantedAuthority(role.getRoleCode()))
                    .forEach(authorities::add);
//        }
        return authorities;
    }

    @Override
    public String getPassword() {
        if (user.getCheckPassword() != null) {
            return user.getCheckPassword();
        }
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getStatus().equals(StatusAccountEnum.DELETE.getStatus());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus().equals(StatusAccountEnum.ACTIVE.getStatus());
    }

    /**
     * @param user
     * @param roleOfUser
     */
    public CustomUserDetails(User user, Set<Role> roleOfUser) {
        super();
        this.user = user;
        this.roleOfUser = roleOfUser;

    }

}
