package constant.enums;

/**
 * 系统全局编码
 * @Description -1-99, 避开HTTP状态码100-600
 */
public enum GlobalCodeEnum {
    UNKNOW_ERROR("-1", "未知错误"),
    SUCCESS("0", "成功"),
    FAILURE("1", "失败"),
    INVALID_PARAMS("2", "请求参数无效"),
    UNSUPPORTED_URI("3", "未知URI"),
    TOUCH_API_LIMIT("4", "接口调用次数已达到设定的上限"),
    NO_AUTHORIZATION("6", "无访问权限"),
    //持久层错误
    DB_ERROR("20", "数据库异常"),
    SOLR_ERROR("21", "搜索引擎异常"),
	
	//消息中间件错误
	MQ_ERROR("30", "消息中间件异常");

    private String code;
    private String msg;

    private GlobalCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
