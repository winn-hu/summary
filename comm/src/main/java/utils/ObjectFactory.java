package utils;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class ObjectFactory {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdfLong = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Object createObject(String name) {
        Object o = null;
        try {
            Class c = Class.forName(name);
            o = c.newInstance();
        } catch (Exception e) {
			e.printStackTrace();
        }
        return o;
    }

    public static void setValue(Object object, String colName, Object value) {
        colName = colName.toLowerCase();
        try {
            Class c = object.getClass();
            Method[] all = c.getMethods();
            for (Method m : all) {
                String methodName = m.getName().toLowerCase();
                if (methodName.startsWith("set") && methodName.endsWith(colName)) {
                    Object[] p1 = m.getParameterTypes();
                    if (p1.length == 1) {
                        if (value == null || ((Class) p1[0]).isInstance(value)) {
                            m.invoke(object, value);
                        }
                        return;
                    }
                }
            }
        } catch (Exception e) {
			e.printStackTrace();
        }
    }

    public static void setValue(Object object, String colName, Object value,String colType) {
        try {
            Class c = object.getClass();
            Method[] all = c.getMethods(); //get all public methods
            for (Method method : all) {
                String methodName = method.getName().toLowerCase();
                if (methodName.startsWith("set") && methodName.endsWith(colName)) {
                    Object[] p1 = method.getParameterTypes();
                    if (p1.length == 1) {//the method is getter method.
                        if (value == null) {
                            method.invoke(object, new Object[]{null});
                        }else {
                            if (colType.equalsIgnoreCase("int")) {
                                method.invoke(object, Integer.parseInt(value.toString()));
                            } else if (colType.equalsIgnoreCase("long")) {
                                method.invoke(object, Long.parseLong(value.toString()));
                            } else {
                                method.invoke(object, value);
                            }
                        }
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取getter返回值（无参）
     * @param object
     * @param colName
     * @return
     */
    public static Object getValue(Object object, String colName) {
        Object o = null;
        try {
            if (object == null) {
                return null;
            }
            Class c = object.getClass();
            Method[] all = c.getMethods();
            for (Method m : all) {
                String methodName = m.getName().toLowerCase();
                if ((methodName.startsWith("get") || methodName.startsWith("is"))
                        && methodName.endsWith(colName.toLowerCase())) {
                    Object[] p1 = m.getParameterTypes();
                    if (p1.length == 0) {
                        o = m.invoke(object);
                        if (o instanceof Timestamp) {
                            return formatTimeStamp(o);
                        }
                        if (o instanceof Date) {
                            return formatDate(o);
                        } else {
                            return o;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    /**
     * 获取多个参数的getter返回值
     * @param object 实例对象
     * @param colName 属性名
     * @param parameterValues 参数
     * @return
     */
    public static Object getValue(Object object, String colName,Object[] parameterValues) {
        Object o = null;
        try {
            Class c = object.getClass();
            Method[] all = c.getMethods();
            for (Method m : all) {
                String methodName = m.getName().toLowerCase();
                if ((methodName.startsWith("get") || methodName.startsWith("is"))
                        && methodName.endsWith(colName.toLowerCase())) {
                    Object[] p1 = m.getParameterTypes();
                    if (p1.length == parameterValues.length) {
                        o = m.invoke(object, parameterValues);
                        if (o instanceof Date) {
                            return formatDate(o);
                        } else {
                            return o;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    public static HashMap getcolValueMap(Object object) {
        HashMap<String,String> theReturn = new HashMap<>();
        Object o;
        try {
            Class c = object.getClass();
            Method[] all = c.getMethods();
            for (Method m : all) {
                String methodName = m.getName();
                System.out.println("method : "+methodName);
                if (methodName.startsWith("get") || methodName.startsWith("is")) {
                    Object[] p1 = m.getParameterTypes();
                    if (p1.length == 0) {
                        o = m.invoke(object);
                        String colName = methodName.substring(3).toUpperCase();
                        if (o == null) {
                            theReturn.put(colName, "");
                        } else {
                            if (o instanceof Date) {
                                theReturn.put(colName, formatDate(o));
                            } else {
                                theReturn.put(colName, o + "");
                            }

                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return theReturn;
    }

    public static void dataTransfer(Object source, Object dest) {
        Object o;
        try {
            Class c = source.getClass();
            Method[] all = c.getMethods();
            for (Method m : all) {
                String methodName = m.getName();
                if (methodName.startsWith("get") && methodName != "getClass") {
                    Object[] p1 = m.getParameterTypes();
                    if (p1.length == 0) {
                        Class returnClass = m.getReturnType();
                        o = m.invoke(source);
                        try {
                            Method setMethod = c.getDeclaredMethod(
                                    "set" + methodName.substring(3),
                                    returnClass);
                            setMethod.invoke(dest, o);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else if (methodName.startsWith("is")) {
                    Object[] p1 = m.getParameterTypes();
                    if (p1.length == 0) {
                        Class returnClass = m.getReturnType();
                        o = m.invoke(source);
                        try {
                            Method setMethod = c.getDeclaredMethod(
                                    "set" + methodName.substring(2),
                                    returnClass);
                            setMethod.invoke(dest, o);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        } catch (Exception e) {
			e.printStackTrace();
        }
    }

    public static String formatDate(Object in) {
        return sdf.format(in);
    }

    public static String formatTimeStamp(Object in) {
        return sdfLong.format(in);
    }

    /**
     * @jira: HRPCOMMDEVJAVA-2198
     * @Title: execMethod
     * @Description: 执行无参方法
     * @author: sjl
     * @date 2018年3月16日 下午3:24:16
     * @param object
     * @param methodName
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Object execMethod(Object object, String methodName) {
        Object theReturn = null;
        try {
            Class c = object.getClass();
            Method m = c.getMethod(methodName);
            Object[] p1 = m.getParameterTypes();
            if (p1.length == 0) {
                theReturn = m.invoke(object);
            } else if (p1.length == 1) {
                throw new Exception("调用方法必须为无参方法，请检查传入的方法名称！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return theReturn;
    }

}
