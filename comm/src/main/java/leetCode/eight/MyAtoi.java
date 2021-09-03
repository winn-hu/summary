package leetCode.eight;

/**
 * 8. �ַ���ת������ (atoi)
 *
 * ˼·��
 * 1��ȥ���ַ������˿ո�
 * 2�������ַ����������洢����������
 * 3�����ַ���ת��ΪBigdecimal���͵����֣���ֹ�������
 * 4���жϱ߽�
 */
public class MyAtoi {

    public static void main(String[] args) {
        System.out.println(atoi("  "));
    }

    public static int atoi(String s) {
        StringBuilder numBuilder = new StringBuilder();

        //1. �������õ�ǰ���ո�
        String target = s.trim();

        //2. �жϵ�һλ�ܷ��Ƿ���λ������
        if(target.length() == 0) return 0;
        char[] chars = target.toCharArray();
        if(chars[0] == '+' || chars[0] == '-' || Character.isDigit(chars[0])) {
            if(chars[0] != '+') {
                numBuilder.append(chars[0]);
            }
        }else {
            return 0;
        }
        //3. �ж��Ƿ�����������
        for (int i = 1, len = target.length(); i < len; i++) {
            if(Character.isDigit(chars[i])) {
                numBuilder.append(chars[i]);
            }else {
                break;
            }
        }
        //4. �жϽ���Ƿ񳬹���Χ [?2^31,  2^31 ? 1]
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
