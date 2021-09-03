package leetCode.five;

/**
 * 5. ������Ӵ�
 *
 *
 * ˼·����̬�滮
 * 1�� �ַ�������Ϊ1��ֱ�ӷ����ַ���
 * 2�� ���ҵ�����Ϊ2���߳���Ϊ3�Ļ����ַ���
 *     ����Ϊ2����3ʱ��p(i,j) = (s[i] == s[j])
 * 3)  ���ȴ���3ʱ�� p(i,j) = p(i+1,j-1)*(s[i] == s[j])
 *
 *
 *
 *
 */
public class LongestPalindrome2 {

    public static void main(String[] args) {
        System.out.println(method("a"));
    }

    public static String method(String s) {
        int begin = 0, maxlen = 1;

        int length = s.length();
        //����Ϊ1��ֱ�ӷ����ַ���
        if(length == 1) return s;

        boolean[][] dp = new boolean[length][length];
        char[] chars = s.toCharArray();

        //pLen : �����ַ����ĳ���
        for (int pLen = 2; pLen <= length; pLen++) {
            for (int i = 0; i < length; i++) {
                int j = pLen+i-1;
                if(j < length) {
                    if(j-i+1<=3) {//����Ϊ2����3ʱ
                        dp[i][j] = (chars[i]  == chars[j]);
                    }else {//���ȴ���3ʱ
                        dp[i][j] = dp[i+1][j-1] && (chars[i]  == chars[j]);
                    }

                    //dp[i][j]Ϊtrue,��chars���±�Ϊi ~ j���ַ���ɻ��Ĵ�
                    if(dp[i][j] && pLen > maxlen) {
                        begin = i;
                        maxlen =  pLen;
                    }
                }

            }
        }

        return s.substring(begin,begin+maxlen);
    }
}
