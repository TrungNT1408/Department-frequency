package vn.osp.adfilex.Departmentfrequency.authencation;

import com.google.common.base.Strings;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import vn.osp.adfilex.Departmentfrequency.constants.ApplicationConstant;
import vn.osp.adfilex.Departmentfrequency.model.JwtAuthDto;
import vn.osp.adfilex.Departmentfrequency.repository.UserRepository;

/**
 *
 * @author Nguyen_Toan
 */
@Component
@Slf4j
@Qualifier("JwtTokenProvider_Main")
@Primary
public class JwtTokenProvider {

    @Autowired
    private UserRepository userRepository;
    private static final String USER_ID = "ui";
    private static final String USER_NAME = "uname";
    private static final String ROLE = "rol";
    private static final String US_AGENT = "us-agent";
    private static final String IP = "ip";
    private static final String HOBBY = "hobby";
    private static final String EXPIRATION = "exp";
    private static final String UI_SUBS = "ui_subs";
    private static final String SESSION = "ss";
    private static final String VOICE_RESOURCE = "v_res";
    private static final String SMS_RESOURCE = "s_res";
    private static final String EXPI_RESOURCE = "exp_res";

    @Value("${jwt.iss}")
    private String jwtIss;

    @Value("${jwt.ttlInDays}")
    private int jwtTtlInDays;

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    /**
     * Caching signing key
     */
    private Key signingKey;

    public String generateToken(CustomUserDetails userDetails) {

        LocalDateTime currentTime = LocalDateTime.now();
        final Date expiryDate
                = Date.from(currentTime.plusDays(jwtTtlInDays).atZone(ZoneId.systemDefault()).toInstant());
        String roleCode = "";
        if (!userDetails.getRoleOfUser().isEmpty()) {
            roleCode = userDetails.getUser().getUserRoleList().get(0).getRole().getRoleCode();
        }
        List<Long> listUserIdSub = new ArrayList<>();

        return Jwts.builder().setIssuer(jwtIss)
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(expiryDate).claim(USER_ID, userDetails.getUser().getUserId())
                .claim(USER_NAME, userDetails.getUser().getUsername())
                .claim(ROLE, roleCode)
                .claim(US_AGENT, userDetails.getUsAgent()).claim(IP, userDetails.getIp())
                .claim(SESSION, userDetails.getSession())
                .signWith(SignatureAlgorithm.HS256, getSigningKey())
                .compact();
    }

    @SuppressWarnings("unchecked")
    public JwtAuthDto getJWTInfor(String authToken) {

        Claims body = Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(authToken).getBody();

        return JwtAuthDto.builder().issuer(body.getIssuer()).ui(body.get(USER_ID, Long.class))
                .rol(body.get(ROLE, String.class)).expi(body.get(EXPIRATION, Date.class))
                .uname(body.get(USER_NAME, String.class)).ip(body.get(IP, String.class))
                .usAgent(body.get(US_AGENT, String.class))
                .ss(body.get(SESSION, String.class))
                .build();
    }

    public boolean validateToken(String authToken) {

        try {
            Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(authToken);
            return true;
        } catch (ExpiredJwtException e) {
            log.error(" Expired and must be rejected: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported Jwt:" + e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Malformed Jwt : " + e.getMessage());
        } catch (SignatureException e) {
            log.error("Signature Exception: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("Illegal Argument: " + e.getMessage());
        }
        return false;
    }

    /**
     * Create and get signing key.
     *
     * @return key for signing and verify jwt
     */
    private Key getSigningKey() {

        if (signingKey == null) {
            // Create key for sign the jwt
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
            signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
        }
        return signingKey;
    }

    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(ApplicationConstant.AUTHENTICATION_SCHEME_NAME);
        return getJwtFromHeader(bearerToken);
    }

    public String getJwtFromHeader(String bearerToken) {
        if (StringUtils.hasText(bearerToken)
                && bearerToken.startsWith(ApplicationConstant.TOKEN_SCHEME_NAME)) {
            return bearerToken.substring(ApplicationConstant.TOKEN_SCHEME_NAME.length(),
                    bearerToken.length());
        }
        return org.apache.commons.lang3.StringUtils.EMPTY;
    }

}
