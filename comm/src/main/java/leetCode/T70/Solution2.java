package leetCode.T70;

/**
 *
 * ˼·��ת����쳲����������С�ѭ��ʵ�֡��� 1 1 2 3 5 8
 * �ѵ�����û���뵽 �� f(x) = f(x-1) + f(x-2)
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
