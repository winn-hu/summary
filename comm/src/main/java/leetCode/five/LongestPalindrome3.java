package leetCode.five;

/**
 * 5. 最长回文子串
 *
 *
 * 思路：动态规划【递归思想】
 * 1） 长度<=3，p(i,j) = (s[i] == s[j])
 * 2)  长度>3， p(i,j) = p(i+1,j-1)*(s[i] == s[j])
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

        //pLen : 回文字符串的长度
        for (int pLen = 1; pLen <= length; pLen++) {
            //i : 回文字符串的左边界
            for (int leftBorder = 0; leftBorder < length; leftBorder++) {
                //rightBorder : 回文字符串的右边界
                int rightBorder = pLen+leftBorder-1;
                if(rightBorder < length) {
                    if(pLen<=3) {//长度<=3时
                        dp[leftBorder][rightBorder] = (chars[leftBorder]  == chars[rightBorder]);
                    }else {//长度>3时
                        dp[leftBorder][rightBorder] = dp[leftBorder+1][rightBorder-1] && (chars[leftBorder]  == chars[rightBorder]);
                    }

                    //dp[leftBorder][rightBorder]为true,则chars中下表为i ~ j的字符组成回文串
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
