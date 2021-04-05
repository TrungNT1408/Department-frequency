package vn.osp.adfilex.Departmentfrequency.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import vn.osp.adfilex.Departmentfrequency.constants.RegexPatternEnum;


/**
 *
 * @author Nguyen_Toan
 */
public class StringUtils {

    private StringUtils() {

    }

    /* Character */
    public static final String EQUAL = "=";

    public static final String COMMA = ",";

    public static final String SPACE = " ";

    public static final String FIVE_STAR = "*****";

    public static final String MINUS = "-";

    public static final String COLON = ":";

    public static final String GZ = "gz";

    public static final String DOT = ".";

    public static final String AND = "&";

    public static final String SLASH_RIGHT = "/";

    public static final String SLASH_LEFT = "\\";

    public static final String EMPTY = "";

    public static final String NOT_ANSWER_TIME_OUT_480 = "480";

    public static final String NOT_ANSWER_TIME_OUT_487 = "487";

    public static final String HAPPYCALLERROR = "HAPPYCALLERROR";

    public static final String IPCC_NOT_CONNECT_AGENT_ID = "IPCC_NOT_CONNECT_AGENT_ID";

    public static final String IPCC_NOT_CONNECT_CUSTOMER = "IPCC_NOT_CONNECT_CUSTOMER";

    public static final String CUSTOMER = "CUSTOMER";

    public static final String AGENT = "AGENT";

    public static final String OUTCALL_ERROR = "outcallError";

    public static final String OUTCALL_ERROR_CODE = "outcallErrorCode";

    public static final String ERROR_500 = "500";

    public static final String BUSY_ = "486";

    public static final String DAY = " Ngày ";

    public static final String XLSX = ".xlsx";

    public static final String NEWLINE_CHARACTER = "<br/>";

    public static final String APPLICATION = "application";

    /* MESSAGE */
    public static final String TO = "tới";

    public static final String ABOUT = "về";

    public static final String COMPANY_NAME = "CPN";

    public static final String UNAUTHORIZED = "Unauthorized";

    public static final String ACCESS_DENIED = "Access denied";

    public static final String U_TIMEZONE = "user.timezone";

    public static final String GMT_7 = "GMT+7";

    public static final String SOCKET_END = "Websocket is disconnected.";

    public static final String GET = "get";
    public static final String GET_LOGIN = "getlogin";

    public static final String SUBFIX = "A01";

    public static final String APPLICATION_EXCEL
            = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static final String INTERNAL_SERVER_WEBSOCKET = "Error: ";

    public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String DF_YYYY_MM_DD_HH_MM_SS_2 = "yyyy_MM_dd_HH_mm_ss";

    public static final String DF_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static final String DF_YYYY_MM_DD = "yyyy-MM-dd";

    public static final String DF_DD_MM_YYYY = "dd/MM/yyyy";

    public static final String API_KEY = "JWT Authentication";

    public static final String AUTHORIZATION = "Authorization";

    public static final String HEADER = "header";

    public static final String MSG_NOT_ENOUGH_PARAMS = "NOT_ENOUGH_PARAMS";

    public static final String MSG_OVER_QUOTA = "OVER_QUOTA";

    public static final String MSG_SUCCESS = "SUCCESS";

    public static final String MSG_ERROR = "FAILED";

    public static final String TOPIC_CODE = "topic_code";

    public static final String USER_NAME = "username";

    public static final String FAV_DURATION_THRESHOLD = "favorite_duration_threshold";

    public static final String STT = "STT";
    
    public static final String TIME_ZONE = "Asia/Ho_Chi_Minh";

    public static final String USERNAME = "Tên đăng Nhập";
    public static final String CREATED_DATE = "Ngày Khởi Tạo";
    public static final String AVAILABLE_RESOURCES = "Tài Nguyên Khả Dụng";
    public static final String EXPIRATION_DATE = "Ngày Hết Hạn";
    public static final String STATUS_ = "Trạng Thái";
    public static final String ROLE = "Vai trò";
    /* 3C */

    public static final String AUTHTOKEN = "authToken";

    public static final String AUTHENTOKEN = "authenToken";

    public static final String USER_TOKEN = "userToken";

    public static final String DOMAIN = "domain";

    public static final String IP_PHONE = "ipphone";

    public static final String EXPIRED = "expired";

    public static final String CALL_OUT_REQUEST_ID = "2032";

    public static final String OUT_CALL_RESPONSE_ID = "1055";

    public static final String REFRESH_ID = "1010003";

    public static final String LOGIN_RESPONSE_ID = "1000";

    public static final String AGENT_SERVER_URL = "agent_server_url";

    public static final String ACCOUNT = "account";

    public static final String NOT_FOUND_3C = "404 Not Found";

    public static final String UNAUTHORIZED_3C = "401 Unauthorized";

    public static final String DOMAIN_EXPIRED_3C = "405 ";

    public static final String BAD_REQUEST_3C = "400 ";

    public static final String INTERNAL_SERVER_3C = "500 ";

    public static final String CONTENT_DISPOSITION = "Content-Disposition";

    public static final String ATTACHMENT_FILENAME = "attachment; filename=";

    public static final String PREFIX_WSS = "wss://";

    public static final String WEBSOCKET = "/websocket";

    public static final String INIT_CALL_CHANNEL = "initCallChannel";

    public static final String ACCOUNT_OF = "ACCOUNTS_OF_";

    public static final String ACCOUNT_CREATED_BY_ADMIN = "ACCOUNT_CREATED_BY_ADMIN";

    public static final String TELEPHONE_NUMBER = "telephoneNumber";

    public static final String UTM = "utm";

    public static final String PHONE = "phone";

    public static final String CALLER_NAME = "callerName";

    public static final String OBJECT_ID = "objectId";

    public static final String TOKEN_3C = "token3C";

    public static final String STATUS = "status";

    public static final String TYPE = "type";

    public static final String SIP_CODE = "sipCode";

    public static final String ERROR_CODE = "errorCode";

    public static final String CALL_OUT_ERR = "Callout";

    public static final String BUSY_CODE = "486";

    public static final String NOT_REACH_SUB_CODE = "487";

    public static final String LOGIN_ERR = "Login";

    public static final String FORWARD_ERR = "Forward";

    public static final String STARTING = "STARTING";

    public static final String RINGING = "RINGING";

    public static final String ANSWER = "ANSWER";

    public static final String ENDCALL = "ENDCALL";

    public static final String MSG_ENDCALL = "Kết thúc cuộc gọi";

    public static final String BUSY = "BUSY";

    public static final String ERROR = "ERROR";

    public static final String CALL_ID = "callid";
    public static final String CALL_ID_1 = "callId";

    public static final String CALL_ID_2 = "callID";

    public static final String ID = "id";

    public static final String HOBBY_ID = "hobby_id";

    public static final String MASKED_ISDN = "masked_isdn";

    public static final String NOTE = "note";

    public static final String RATING = "rating";

    public static final String FREQ = "freq";

    public static final String CTX = "ctx";

    public static final String CATEGORY = "category";

    public static final Integer TERMINATE_RESPOND_ID = 2000000001;

    public static final Integer NOTIFY_RESPOND_ID = 2000000002;

    /* OTHER */
    public static final String ACCOUNT_NOT_CREATE_BY_ADM = "Tài khoản không tạo bởi Admin. ";

    public static final String ACCOUNT_CREATE_BY_ADM = "Tài khoản tạo bởi Admin. ";

    public static final String ACCOUNT_CREATED = "Tài khoản đã tạo. ";

    public static final String ACCOUNT_MANAGEMENT = "Account_Management_";

    public static final String TIME_NEW_ROMAN = "Times New Roman";

    public static final String QLTK = "QUẢN LÍ TÀI KHOẢN";


    /* REGEX */
    public static final String REGEX_CHARACTER_VN = "^[^~!@#$%^&*()_+|\\/\\\"',.:;\\d<>?`\\\\=-]+$";

    public static final String REGEX_FULLNAME = "^[^\\s]*$";

    public static final String REGEX_USERNAME = "^([a-zA-Z0-9]*[a-zA-Z])[a-zA-Z0-9_]*$";

    public static final String REGEX_NUMBER = "^[0-9]+$";

    public static final String REGEX_NUMBER_BRANCH = "^[1-9]\\d*$";

    public static final String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    //Stringee
    public static final String BODY = "body";
    public static final String CODE = "code";
    public static final String SUCCESS = "success";
    public static final String FROMNUMBER = "fromNumber";
    public static final String TONUMBER = "toNumber";
    public static final String SERVICE = "service";
    public static final String DATA = "data_osp";
    public static final String JTI = "jti";
    public static final String ISS = "iss";
    public static final String EXP = "exp";
    public static final String USERID = "userId";
    public static final String REQUESTID = "requestId";

    public static final String HASH_SALT = "Adf1lex!@#";

    public static boolean validateEmail(Object value) {
        String email = value.toString();
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(email);
        boolean check = matcher.matches();
        return check;
    }

    public static boolean validateFullName(Object value) {
        String fullname = value.toString();
        Pattern pattern = Pattern.compile(REGEX_FULLNAME);
        Matcher matcher = pattern.matcher(fullname);
        boolean check = matcher.matches();
        return check;
    }

    public static boolean validateSdt(Object value) {
        String sdt = value.toString();
        Pattern pattern = Pattern.compile(REGEX_NUMBER);
        Matcher matcher = pattern.matcher(sdt);
        boolean check = matcher.matches();
        return check;
    }

    /* FUNCTION */
    public static final String reverseString(String input) {
        return new StringBuffer(input).reverse().toString();
    }

    public static final String generatedString(int lenght, String prefix, String suffixe) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd/hhmm");
        StringBuilder stringBuilder = new StringBuilder(dateFormat.format(new Date()).substring(2));

        return stringBuilder.append(MINUS).append(prefix)
                .append(UUID.randomUUID().toString().substring(0, lenght)).append(MINUS).append(suffixe)
                .toString().toUpperCase();
    }

    public static String getMethodName(String fieldName) {
        return new StringBuilder(GET + fieldName.substring(0, 1).toUpperCase())
                .append(fieldName.substring(1)).toString();
    }

    /**
     * Convert string camel case to snake case
     *
     * @param camelcase
     * @return string camel case
     */
    public static String convertCamelcaseToSnakeCase(String camelcase) {

        return camelcase.replaceAll(RegexPatternEnum.REPLACE_CAMELCASE_TO_SNAKE_CASE.getPattern(),
                RegexPatternEnum.REPLACE_CAMELCASE_TO_SNAKE_CASE.getReplacement()).toLowerCase();
    }

    /**
     * Get time allow From String format 8h -> 21h
     *
     * @param string
     * @return
     */
    public static Integer getTimeAllow(String string, int index) {
        return Integer.valueOf(string.split("->")[index].trim().split("h")[0].trim());
    }

    //Convert Date to String
    public static String date2str(Date input, String oFormat) {
        String result = "";
        if (input != null) {
            try {
                DateFormat df = new SimpleDateFormat(oFormat);
                result = df.format(input);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    //Convert String to Date
    public static Date str2date(String input, String format) throws java.text.ParseException {
        Date result = null;
        if (!input.isEmpty()) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                result = formatter.parse(input);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
