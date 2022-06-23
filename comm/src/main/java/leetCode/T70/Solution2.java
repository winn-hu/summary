package leetCode.T70;

/**
 *
 * 思路：转换成斐波那契额数列【循环实现】： 1 1 2 3 5 8
 * 难点在于没有想到 ： f(x) = f(x-1) + f(x-2)
 *
 */

public class Solution2 {

    public int climbStairs(int n) {
        int x=1, y=1, tmp=0;
        for(int i=2; i<=n; i++) {
            tmp = y;
            y = x + y;
            x = tmp;
        }
        return y;
    }
}
