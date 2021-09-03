package leetCode.five;

/**
 * 5. 最长回文子串
 *
 *
 * 思路：动态规划
 * 1） 字符串长度为1，直接返回字符串
 * 2） 先找到长度为2或者长度为3的回文字符串
 *     长度为2或者3时，p(i,j) = (s[i] == s[j])
 * 3)  长度大于3时， p(i,j) = p(i+1,j-1)*(s[i] == s[j])
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
        //长度为1，直接返回字符串
        if(length == 1) return s;

        boolean[][] dp = new boolean[length][length];
        char[] chars = s.toCharArray();

        //pLen : 回文字符串的长度
        for (int pLen = 2; pLen <= length; pLen++) {
            for (int i = 0; i < length; i++) {
                int j = pLen+i-1;
                if(j < length) {
                    if(j-i+1<=3) {//长度为2或者3时
                        dp[i][j] = (chars[i]  == chars[j]);
                    }else {//长度大于3时
                        dp[i][j] = dp[i+1][j-1] && (chars[i]  == chars[j]);
                    }

                    //dp[i][j]为true,则chars中下表为i ~ j的字符组成回文串
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
