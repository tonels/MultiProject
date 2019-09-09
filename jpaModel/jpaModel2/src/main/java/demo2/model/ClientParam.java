package demo2.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;

/**
 * 客户端Client接口参数封装
 * Created by wangfan on 2018-12-11 下午 3:50.
 */

@Data
public class ClientParam {
    public final static String DEFAULT_REDIRECT_URL = "urn:ietf:wg:oauth:2.0:oob";

    private String clientName;

    private Set<String> scope;


    @Transient
    private Set<String> redirectUri;

}
