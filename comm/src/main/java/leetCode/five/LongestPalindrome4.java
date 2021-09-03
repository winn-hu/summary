package leetCode.five;

/**
 * 5. 最长回文子串
 *
 *
 * 思路：
 *       中间向两边扩展   p(i,j) -> p(i-1,j+1) -> p(i-2,j+2)
 * 1） 长度为奇数，p(i,i)
 * 2)  长度为偶数，p(i,i+1)
 * 3)  获取上面两种情况中，长度最长的；
 *
 */
public class LongestPalindrome4 {

    public static void main(String[] args) {
        System.out.println(method("dcabmba"));
    }

    public static String method(String s) {
        int begin = 0, maxLen = 0;
        for (int i = 0, slen = s.length(); i < slen; i++) {
            int len1 = extend2Border(s, i, i);
            int len2 = extend2Border(s, i, i + 1);
            int len = Math.max(len1, len2);
            if(len > maxLen) {
                maxLen = len;
                begin = len1 > len2 ? i-len/2 : i-len/2+1;
            }


        }
        return s.substring(begin,begin+maxLen);
    }

    public static int extend2Border(String target, int left, int right) {
        while (left >= 0 && right < target.length() && target.charAt(left) == target.charAt(right)) {
            left--;
            right++;
        }
        return right-left-1;
    }
}
