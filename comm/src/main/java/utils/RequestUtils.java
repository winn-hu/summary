package utils;


import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangkang
 * @date 2019/10/22 0022
 */
public class RequestUtils {

    /**
     *
     * X-Forwarded-For：Squid 服务代理
     *
     * proxy-Client-IP：apache 服务代理
     *
     * WL-proxy-Client-IP：weblogic 服务代理
     *
     * HTTP_CLIENT_IP：有些代理服务器
     *
     * X-Real-IP：nginx服务代理
     *
     * 记录非法请求ip
     */
    private Map<String, Integer> ipCountMap = new HashMap<>(20);

    /**
     * 获取登录用户IP地址
     *
     * @param request xx
     * @return xx
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    public static Map<String, Object> getParametersMap(HttpServletRequest request) {
        Map<String, Object> tmp = new HashMap<>();

        Enumeration ss = request.getParameterNames();
        while (ss.hasMoreElements()) {
            String key = ss.nextElement() + "";
            String value = request.getParameter(key);
            tmp.put(key, value);
        }

        Enumeration<String> attributeNames = request.getSession().getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String key = attributeNames.nextElement();
            Object value = request.getSession().getAttribute(key);
            tmp.put(key, value);
        }

        return tmp;
    }


}
