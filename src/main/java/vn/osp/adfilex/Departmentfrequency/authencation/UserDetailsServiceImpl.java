package vn.osp.adfilex.Departmentfrequency.authencation;

import java.util.HashSet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.osp.adfilex.Departmentfrequency.config.MessageSoucreConfig.MessageSourceVi;
import vn.osp.adfilex.Departmentfrequency.entity.Role;
import vn.osp.adfilex.Departmentfrequency.entity.User;
import vn.osp.adfilex.Departmentfrequency.entity.UserRole;
import vn.osp.adfilex.Departmentfrequency.repository.UserRepository;

/**
 *
 * @author Nguyen_Toan
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageSourceVi messageSourceVi;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value(value = "${login.domain.ecocrm}")
    private String domain;

    @Transactional
    public CustomUserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
                messageSourceVi.getMessageVi("USERNAME_NOT_FOUND_MSG") + id));

        HashSet<Role> rolesOfUser = new HashSet<>();
        user.getUserRoleList().stream().map(UserRole::getRole).forEach(rolesOfUser::add);

        return new CustomUserDetails(user, rolesOfUser);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.findByUsernameIgnoreCase(username);
        
//        if (user.getDomain() != null && user.getDomain().startsWith(domain)) {     
        if (user != null && user.getCheckDomain() != null) {
            user.setCheckPassword(passwordEncoder.encode("123456"));
        }
        if (null == user) {
            throw new UsernameNotFoundException(
                    messageSourceVi.getMessageVi("USERNAME_NOT_FOUND_MSG") + username);
        }

        HashSet<Role> rolesOfUser = new HashSet<>();
        user.getUserRoleList().stream().map(UserRole::getRole).forEach(rolesOfUser::add);
        
        return new CustomUserDetails(user, rolesOfUser);
    }

}
