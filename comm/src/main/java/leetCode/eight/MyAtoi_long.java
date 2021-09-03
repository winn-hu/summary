package leetCode.eight;

/**
 * 8. 字符串转换整数 (atoi)
 *
 * 思路：
 * 1，去除字符串两端空格
 * 2，判断第一位是否是符号位，确定符号；
 * 3，遍历字符串，计算数字（long型，防止int型溢出）：result = result * 10 + chars[0] - '0';
 * 4，判断边界： (sign == 1) ? Math.min(result,Integer.MAX_VALUE) : Math.min(result,-(long)Integer.MIN_VALUE);
 */
public class MyAtoi_long {

    public static void main(String[] args) {
        System.out.println(atoi("  -2000"));
        System.out.println(Integer.MAX_VALUE);
    }

    public static int atoi(String s) {
        long result = 0;
        int sign = 1;

        //1. 丢弃无用的前导空格
        String target = s.trim();
        //2. 判断第一位受否是符号位、数字
        if(target.length() == 0) return 0;
        char[] chars = target.toCharArray();
        if(chars[0] == '+' || chars[0] == '-' || (chars[0] >= '0' && chars[0]<='9')) {
            if(chars[0] == '-') {
                sign = -1;
            }else if(chars[0] != '+'){
                result = result * 10 + chars[0] - '0';
            }
        }else {
            return 0;
        }
        //3. 获取连续数字
        for (int i = 1, len = target.length(); i < len; i++) {
            if(chars[i] >= '0' && chars[i]<='9') {
                result = result * 10 + chars[i] - '0';
                //4. 判断结果是否超过范围 [?2^31,  2^31 ? 1]
                result = (sign == 1) ? Math.min(result,Integer.MAX_VALUE) : Math.min(result,-(long)Integer.MIN_VALUE);
            }else {
                break;
            }
        }

        return  (int)result * sign;

    }
}
