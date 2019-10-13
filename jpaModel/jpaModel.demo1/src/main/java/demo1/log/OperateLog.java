package demo1.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 操作日志实体类
 */
@Data
@Entity
@Table(name = "ope_log")
public class OperateLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recordId; // 操作数据id

    private String module;// 模块名称

    private String business;// 业务方法描述

    private String opType;// 操作类型

    private Long userId;// 操作人

    private String userName;// 操作人姓名

    @Lob
    private String data;// 操作数据

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}