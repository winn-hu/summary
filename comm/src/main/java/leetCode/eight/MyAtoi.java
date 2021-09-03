package leetCode.eight;

/**
 * 8. 字符串转换整数 (atoi)
 *
 * 思路：
 * 1，去除字符串两端空格
 * 2，创建字符串构造器存储连续的数字
 * 3，将字符串转换为Bigdecimal类型的数字（防止溢出报错）
 * 4，判断边界
 */
public class MyAtoi {

    public static void main(String[] args) {
        System.out.println(atoi("  "));
    }

    public static int atoi(String s) {
        StringBuilder numBuilder = new StringBuilder();

        //1. 丢弃无用的前导空格
        String target = s.trim();

        //2. 判断第一位受否是符号位、数字
        if(target.length() == 0) return 0;
        char[] chars = target.toCharArray();
        if(chars[0] == '+' || chars[0] == '-' || Character.isDigit(chars[0])) {
            if(chars[0] != '+') {
                numBuilder.append(chars[0]);
            }
        }else {
            return 0;
        }
        //3. 判断是否是连续数字
        for (int i = 1, len = target.length(); i < len; i++) {
            if(Character.isDigit(chars[i])) {
                numBuilder.append(chars[i]);
            }else {
                break;
            }
        }
        //4. 判断结果是否超过范围 [?2^31,  2^31 ? 1]
        if (numBuilder.length() > 0) {
            if(numBuilder.length() == 1 && numBuilder.toString().compareTo("-") == 0) {
                return 0;
            }
            java.math.BigDecimal minValue = new java.math.BigDecimal(Math.pow(-2, 31));
            java.math.BigDecimal maxValue = new java.math.BigDecimal(Math.pow(2, 31)-1);

            java.math.BigDecimal value = new java.math.BigDecimal(numBuilder.toString());
            if(value.compareTo(minValue) < 0) {
                value = minValue;
            }else if (value.compareTo(maxValue) > 0) {
                value = maxValue;
            }
            return value.intValue();
        }

        return 0;

    }
}
