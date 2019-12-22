package querydsl.tonels.config;


/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymousUser";
    public static final String DEFAULT_LANGUAGE = "en";
    public static final String SUCCESS = "SUCCESS";
    public static final String NO_AUTHORIZE = "permitAll";
    public static final String REDIS_USERINFO = "USERINFO_";
    public static final String USER_REDIS_RESOURCE = "RESOURCE_";
    public static final String USER_DEFAULT_PASSWD = "11111111";

    private Constants() {
    }


}
