package leetCode.T70;

/**
 *
 * ˼·��ת����쳲����������С��ݹ�ʵ�֡��� f(x) = f(x-1) + f(x-2)
 * �ѵ�����û���뵽 �� f(x) = f(x-1) + f(x-2)
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
