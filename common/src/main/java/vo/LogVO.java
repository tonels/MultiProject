package vo;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import model.SysLog;

import java.io.Serializable;

@Data
public class LogVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private SysLog sysLog;
    private String userName;
    private String responseStatusCode;

    public LogVO(SysLog sysLog, String userName, String responseStatusCode) {
        this.sysLog = sysLog;
        this.userName = userName;
        this.responseStatusCode = responseStatusCode;
    }

    public String toJson() {
        return JSONUtil.toJsonStr(this);
    }
}
