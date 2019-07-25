package tonels.mbdemo3.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    Log log = LogFactory.getLog(BaseController.class);


    public Map<String, String> getParameterMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();// 调用nextElement方法获得元素
            String value = request.getParameter(name);
            map.put(name, value);
        }
        return map;
    }

    /**
     * 向响应流中写入数据 writeResponse
     *
     * @param msg    ,发送的内容
     * @param header ,请求头，可以为空，默认为text/json void
     * @since 1.0.0
     */
    protected void writeResponse(String msg, String header) {
        if ("".equals(header)) {
            header = "text/json";
        }
        try {
            if (msg != null) {
                getResponse().setContentType(header);
                getResponse().getWriter().write(msg);
                getResponse().getWriter().flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    protected HttpSession getSession() {
        return getRequest().getSession();
    }
}