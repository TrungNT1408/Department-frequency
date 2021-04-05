package vn.osp.adfilex.Departmentfrequency.constants;

/**
 *
 * @author Nguyen_Toan
 */
public class ApplicationConstant {

    private ApplicationConstant() {

    }

    public static final String AUTHENTICATION_SCHEME_NAME = "Authorization";
    
    public static final String CONTENT_TYPE = "Content-Type";

    public static final String PARTNER_ID = "Partner-Id";
    public static final String TOKEN_SCHEME_NAME = "Bearer ";
    public static final String AUTH_CRM = "Basic ";
    public static final String DOMAIN = "ecocrm";

    public static final String ADMIN = "ADM";
    public static final String MODERATOR = "MOD";
    public static final String CONTRACT = "CON";
    public static final String SALE = "SAL";

    public static final short STARTING = 0;
    public static final short RINGING = 1;
    public static final short ANSWER = 2;

    public static final short ENDCALL = 3;
    public static final short ERROR = 4;
    public static final short NOT_ANSWER_TIME_OUT = 5;

    public static final short BUSY = 6;
    public static final short SOCKET_END = 7;
    public static final short USER_CANCEL = 8;

}
