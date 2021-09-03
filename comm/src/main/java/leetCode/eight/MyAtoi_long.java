package leetCode.eight;

/**
 * 8. �ַ���ת������ (atoi)
 *
 * ˼·��
 * 1��ȥ���ַ������˿ո�
 * 2���жϵ�һλ�Ƿ��Ƿ���λ��ȷ�����ţ�
 * 3�������ַ������������֣�long�ͣ���ֹint���������result = result * 10 + chars[0] - '0';
 * 4���жϱ߽磺 (sign == 1) ? Math.min(result,Integer.MAX_VALUE) : Math.min(result,-(long)Integer.MIN_VALUE);
 */
public class MyAtoi_long {

    public static void main(String[] args) {
        System.out.println(atoi("  -2000"));
        System.out.println(Integer.MAX_VALUE);
    }

    public static int atoi(String s) {
        long result = 0;
        int sign = 1;

        //1. �������õ�ǰ���ո�
        String target = s.trim();
        //2. �жϵ�һλ�ܷ��Ƿ���λ������
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
        //3. ��ȡ��������
        for (int i = 1, len = target.length(); i < len; i++) {
            if(chars[i] >= '0' && chars[i]<='9') {
                result = result * 10 + chars[i] - '0';
                //4. �жϽ���Ƿ񳬹���Χ [?2^31,  2^31 ? 1]
                result = (sign == 1) ? Math.min(result,Integer.MAX_VALUE) : Math.min(result,-(long)Integer.MIN_VALUE);
            }else {
                break;
            }
        }

        return  (int)result * sign;

    }
}
