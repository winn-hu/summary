package algorithm;

/**
 * 最长公共子序列问题LCS
 *    给定两个字符串 x 和 y，求这两个字符串的最长 公共子序列 的内容和长度。
 *
 */
public class LongestSubSequence {

    public static void main(String[] args) {
        String text1 = "ABCBDAB", text2 = "CADB";
        int lcs = getLcs(text1, text2);
        System.out.println("lcs长度: " +lcs);
    }


    public static int getLcs(String x, String y) {
        if (x.equals(y)) {
            System.out.println(x);
            return x.length();
        }
        if(x.length() == 0 || y.length() == 0){
            System.out.println("");
            return 0;
        }

        //记录LCS的最大长度
        int[][] dp = new int[x.length()+1][y.length()+1];
        //记录状态
        int[][] state = new int[x.length()+1][y.length()+1];
        for (int i = 1; i <= x.length(); i++) {
            for (int j = 1; j <= y.length(); j++) {
                if(x.charAt(i-1) == y.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else if (dp[i-1][j] > dp[i][j-1]) {
                    dp[i][j] = dp[i-1][j];
                    state[i][j] = 1;
                } else {
                    dp[i][j] = dp[i][j-1];
                    state[i][j] = 2;
                }
            }
        }

        //获取lcs内容
        getLcs(x, state, x.length(), y.length());
        System.out.println();

        //输出lcs长度
        return dp[x.length()][y.length()];
    }


    public static void getLcs(String x, int[][] state, int i, int j) {
        if(i>=1 && j>=1) {
            if(state[i][j] == 0) {
                getLcs(x,state, i-1, j-1);
                System.out.print(x.charAt(i-1));
            } else if(state[i][j] == 1) {
                getLcs(x,state, i-1, j);
            } else {
                getLcs(x,state, i, j-1);
            }
        }
    }


}
