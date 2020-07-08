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
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
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
        Enumeration ss = request.getParameterNames();
        Map<String, Object> tmp = new HashMap<>(10);
        while (ss.hasMoreElements()) {
            String key = ss.nextElement() + "";
            String value = request.getParameter(key);
            if ("djxh".equalsIgnoreCase(key)) {
                tmp.put(key.toLowerCase(), value);
            } else {
                tmp.put(key, value);
            }
        }

        String swjgdm = (String) request.getSession().getAttribute("swjgdm");
        String swjgmc = (String) request.getSession().getAttribute("swjgmc");
        String swrysfdm = (String) request.getSession().getAttribute("swrysfdm");
        String swrysfmc = (String) request.getSession().getAttribute("swrysfmc");
        String swryxm = (String) request.getSession().getAttribute("swryxm");
        String swrydm = (String) request.getSession().getAttribute("swrydm");
        tmp.put("swjgdm", swjgdm);
        tmp.put("swjgmc", swjgmc);
        tmp.put("swryxm", swryxm);
        tmp.put("swrydm", swrydm);
        tmp.put("swrysfdm", swrysfdm);
        tmp.put("swrysfmc", swrysfmc);
        tmp.put("requestIp", getIpAddr(request));
        return tmp;
    }


}
