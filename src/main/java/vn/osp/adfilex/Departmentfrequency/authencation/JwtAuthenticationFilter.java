package vn.osp.adfilex.Departmentfrequency.authencation;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import lombok.extern.slf4j.Slf4j;
import vn.osp.adfilex.Departmentfrequency.config.MessageSoucreConfig.MessageSourceVi;
import vn.osp.adfilex.Departmentfrequency.constants.ApplicationConstant;
import vn.osp.adfilex.Departmentfrequency.model.JwtAuthDto;
import vn.osp.adfilex.Departmentfrequency.repository.UserSessionRepository;

/**
 *
 * @author Nguyen_Toan
 */
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    @Qualifier("JwtTokenProvider_Main")
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserDetailsServiceImpl customUserDetailsService;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Autowired
    private MessageSourceVi messageSourceVi;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // log.info(new StringBuilder("Request from :::::::::: " + request.getRemoteAddr() + ":"
        // + request.getLocalPort() + request.getRequestURI()).toString());
        // log.info(request.getHeader("User-Agent"));
        // String jwt = vn.osp.adfilex.utils.StringUtils.reverseString(getJwtFromRequest(request));
        String auth = request.getHeader(ApplicationConstant.AUTHENTICATION_SCHEME_NAME);
        try {
            if (auth != null && auth.startsWith(ApplicationConstant.TOKEN_SCHEME_NAME)) {
                String jwt = tokenProvider.getJwtFromRequest(request);
                JwtAuthDto jwtAuthDto = null;
                if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                    jwtAuthDto = tokenProvider.getJWTInfor(jwt);

                    // UserDetails userDetails=
                    // CustomUserDetails.builder().user(User.builder().userId(jwtAuthDto.getUi()).username(jwtAuthDto.getUname()).)
                    CustomUserDetails customUserDetails
                            = customUserDetailsService.loadUserById(jwtAuthDto.getUi());
                    customUserDetails.setIp(jwtAuthDto.getIp());
                    customUserDetails.setSession(jwtAuthDto.getSs());
                    customUserDetails.setUsAgent(jwtAuthDto.getUsAgent());

                    if (null != customUserDetails) {
                        // Nếu người dùng hợp lệ, set thông tin cho Seturity Context
                        UsernamePasswordAuthenticationToken authentication
                                = new UsernamePasswordAuthenticationToken(customUserDetails,
                                        customUserDetails.getPassword(), customUserDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authentication);

                        // Auto generate token when token <= 5p
                        // final Date expirationDateOfJWT = jwtAuthDto.getExpi();
                        // final Date dateCurrent = new Date();
                        // if (expirationDateOfJWT.after(dateCurrent)
                        // && DateUtils.getTimeSpecificDate(expirationDateOfJWT,
                        // Calendar.DAY_OF_MONTH) == DateUtils.getTimeSpecificDate(dateCurrent,
                        // Calendar.DAY_OF_MONTH)
                        // && DateUtils.getTimeSpecificDate(expirationDateOfJWT,
                        // Calendar.HOUR_OF_DAY) == DateUtils.getTimeSpecificDate(dateCurrent,
                        // Calendar.HOUR_OF_DAY)
                        // && DateUtils.getTimeSpecificDate(expirationDateOfJWT, Calendar.MINUTE)
                        // - DateUtils.getTimeSpecificDate(dateCurrent, Calendar.MINUTE) <= 5) {
                        //
                        // String tokenNew =
                        // tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
                        // response.setHeader(ApplicationConstant.AUTHENTICATION_SCHEME_NAME,
                        // ApplicationConstant.TOKEN_SCHEME_NAME + tokenNew);
                        // }
//                        UserSession userSession = userSessionRepository.findAllByUserUserId(jwtAuthDto.getUi());
//                        if ( //                  !ApplicationConstant.ADMIN.equalsIgnoreCase(jwtAuthDto.getRol())&& 
//                                (!ObjectUtils.isEmpty(jwtAuthDto.getSs()) && !ObjectUtils.isEmpty(userSession))
//                                && !ObjectUtils.isEmpty(userSession.getSession())
//                                && !jwtAuthDto.getSs().equalsIgnoreCase(userSession.getSession())) {
//
//                            response.sendError(HttpStatus.NOT_ACCEPTABLE.value(),
//                                    messageSourceVi.getMessageVi("ACCOUNT_IS_LOGGING_ELSE_WHERE"));
//
//                        }

                    }
                }
            }
            
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
