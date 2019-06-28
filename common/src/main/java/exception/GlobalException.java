package exception;

public class GlobalException extends Exception {
    private static final long serialVersionUID = - 5583965576801968391L;
    /**
     * 返回错误码
     */
    private String code;

    public GlobalException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public GlobalException(String code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public GlobalException (String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    protected GlobalException(String code, String msg, Throwable cause,
                            boolean enableSuppression,
                            boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
