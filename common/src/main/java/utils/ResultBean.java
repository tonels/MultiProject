package utils;

import constant.enums.GlobalCodeEnum;
import lombok.Data;

@Data
public class ResultBean {
    /**
     * 信息代码
     */
    private String code;
    /**
     * 信息说明
     */
    private String msg;
    /**
     * 返回数据或jqgrid中的root
     */
    private Object result;

    protected ResultBean() {
        
    }
    
    public ResultBean(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultBean(String code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public static ResultBean ok() {
        return new ResultBean(GlobalCodeEnum.SUCCESS.getCode(), GlobalCodeEnum.SUCCESS.getMsg());
    }

    public static ResultBean ok(Object result) {
        return new ResultBean(GlobalCodeEnum.SUCCESS.getCode(), GlobalCodeEnum.SUCCESS.getMsg(), result);
    }
    
    public Boolean isSuccess() {
        return GlobalCodeEnum.SUCCESS.getCode().equals(this.code);
    }

    public static ResultBean error(){
        return new ResultBean(GlobalCodeEnum.FAILURE.getCode(), GlobalCodeEnum.FAILURE.getMsg());
    }

    public static ResultBean error(String msg){
        return new ResultBean(GlobalCodeEnum.FAILURE.getCode(), msg);
    }

    public static ResultBean error(String code, String msg){
        return new ResultBean(code, msg);
    }

}
