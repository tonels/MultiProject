package demo1.log;

/**
 * 操作模块
 */
public enum OperateModule {
    USER("用户管理");

    private String text;

    OperateModule(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}