package constant;

public interface CommonConstant {
    /**
     * token请求头名称
     */
    String REQ_HEADER = "Authorization";

    /**
     * token分割符
     */
    String TOKEN_SPLIT = "Bearer ";

    /**
     * 删除
     */
    String STATUS_DEL = "1";
    /**
     * 正常
     */
    String STATUS_NORMAL = "0";

    /**
     * 锁定
     */
    String STATUS_LOCK = "9";

    /**
     * 编码
     */
    String UTF8 = "UTF-8";

    /**
     * zuul key serviceId
     */
    String SERVICE_ID = "serviceId";

    /**
     * zuul key UA
     */
    String RESPONSE_STATUS_CODE = "responseStatusCode";

    /**
     * zuul key UA
     */
    String USER_AGENT = "user-agent";

    /**
     * zuul key startTime
     */
    String START_TIME = "startTime";

    /**
     * &
     */
    String STR_WITH = "&";

    /**
     * =
     */
    String STR_EQUAL = "=";
}
