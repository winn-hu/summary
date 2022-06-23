package algorithm;

import java.util.Arrays;

/**
 *��̬�滮��
 *
 * 0-1�������⣺
 *   ���ŵ�i����Ʒ��=��ǰ��Ʒ�۸�+�ϸ��������ܼ۸񡿣� tmp[i][j] = price[i-1] + tmp[i-1][j-weight[i-1]]��
 *   �����ŵ�i����Ʒ��=�ϸ��������ܼ۸񡿣� tmp[i][j] = tmp[i-1][j]��
 *   �Ż򲻷�ȡ����֮ǰ�Ľ������ȡ������������Ž����tmp[i][j] = Math.max(price[i-1]+tmp[i-1][j-weight[i-1]],tmp[i-1][j]);
 */
public class Package01 {

    private static int bestPrice;
    private static int[] bestX;

    public static void main(String[] args) {
        int[] weight = new int[]{3,4,7, 8, 9};
        int[] price  = new int[]{4,5,10,11,13};
        int volume = 17;

        dynamicProgram(weight, price, volume);
        System.out.println("=================");
        backtracking(weight, price, volume);
    }

    private static void dynamicProgram(int[] weight, int[] price, int volume) {
        int count = weight.length;
        int[][] tmp = new int[count+1][volume+1];

        for (int i = 1; i <= count; i++) {
            for (int j = 1; j <= volume; j++) {
                if(weight[i-1] <= j) {
                    tmp[i][j] = Math.max(price[i-1]+tmp[i-1][j-weight[i-1]],tmp[i-1][j]);
                } else {
                    tmp[i][j] = tmp[i-1][j];
                }
            }
        }

        int[] x = new int[count+1];
        for (int i = count; i > 0; i--) {
            if(tmp[i][volume] == tmp[i-1][volume]) {
                x[i-1] = 0;
            } else {
                x[i-1] = 1;
                volume -= weight[i-1];
            }
        }

        for (int i = 0; i < count; i++) {
            if (x[i] == 1) {
                System.out.printf("Weight:%d, Value:%d\n",weight[i],price[i]);
            }
        }
    }

    private static void backtracking(int[] weight, int[] price, int volume) {
        int count = price.length;
        bestX = new int[count];
        int[] x = new int[count];
        backtracking(volume, weight, price, x, 0, 0);
        System.out.println(bestPrice);
        System.out.println(Arrays.toString(bestX));
    }

    private static void backtracking(int volume, int[] weights, int[] princes, int[] x, int idx, int totalPrice) {
        if(idx == weights.length) {
            if(bestPrice < totalPrice) {
                bestPrice = totalPrice;
                bestX = Arrays.copyOf(x, x.length);
            }
        } else {
            for (int i = 0; i < 2; i++) {//ѭ��0-1
                x[idx] = i;
                if(i == 1 && weights[idx] <= volume) {
                    totalPrice += princes[idx];
                    volume -= weights[idx];
                }
                backtracking(volume, weights, princes, x, idx+1,totalPrice);
            }
        }
    }


}
