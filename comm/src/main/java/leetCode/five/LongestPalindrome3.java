package leetCode.five;

/**
 * 5. ������Ӵ�
 *
 *
 * ˼·����̬�滮���ݹ�˼�롿
 * 1�� ����<=3��p(i,j) = (s[i] == s[j])
 * 2)  ����>3�� p(i,j) = p(i+1,j-1)*(s[i] == s[j])
 *
 *
 */
public class LongestPalindrome3 {

    public static void main(String[] args) {
        System.out.println(method("a"));
    }

    public static String method(String s) {
        int begin = 0, maxlen = 1;

        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        char[] chars = s.toCharArray();

        //pLen : �����ַ����ĳ���
        for (int pLen = 1; pLen <= length; pLen++) {
            //i : �����ַ�������߽�
            for (int leftBorder = 0; leftBorder < length; leftBorder++) {
                //rightBorder : �����ַ������ұ߽�
                int rightBorder = pLen+leftBorder-1;
                if(rightBorder < length) {
                    if(pLen<=3) {//����<=3ʱ
                        dp[leftBorder][rightBorder] = (chars[leftBorder]  == chars[rightBorder]);
                    }else {//����>3ʱ
                        dp[leftBorder][rightBorder] = dp[leftBorder+1][rightBorder-1] && (chars[leftBorder]  == chars[rightBorder]);
                    }

                    //dp[leftBorder][rightBorder]Ϊtrue,��chars���±�Ϊi ~ j���ַ���ɻ��Ĵ�
                    if(dp[leftBorder][rightBorder] && pLen > maxlen) {
                        begin = leftBorder;
                        maxlen =  pLen;
                    }
                }
            }
        }

        return s.substring(begin,begin+maxlen);
    }
}
