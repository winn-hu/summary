package leetCode.T70;

/**
 *
 * 思路：转换成斐波那契额数列【递归实现】： f(x) = f(x-1) + f(x-2)
 * 难点在于没有想到 ： f(x) = f(x-1) + f(x-2)
 *
 */

public class Solution {

    public int climbStairs(int n) {
        if(n == 1) {
            return 1;
        }else if(n == 2) {
            return 2;
        } else {
            return climbStairs(n-1) + climbStairs(n-2);
        }
    }
}
