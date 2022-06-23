package algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *背包问题：与0-1背包问题类似，但是每个物品允许部分放入背包。
 */
public class Package {

    public static void main(String[] args) {
        int[] weight = new int[]{30, 10, 20, 50, 40};
        int[] price  = new int[]{65, 20, 30, 60, 40};
        int count = price.length;
        int volume = 100;

        //记录单位重量的价值
        int[] pricePerWeight = new int[count];
        for (int i = 0; i < count; i++) {
            pricePerWeight[i] = price[i] / weight[i];
        }

        //根据单位价值排序[使用map是为了必须有重复的单价]
        Map<Double,String> ppw = new HashMap<>();
        for (int i = 0; i < count; i++) {
            double unitPrice = 1.0 * price[i] / weight[i];
            if(ppw.containsKey(unitPrice)) {
                ppw.put(unitPrice, ppw.get(unitPrice)+","+i);
            } else {
                ppw.put(unitPrice, i+"");
            }
        }
        Object[] array = ppw.keySet().toArray();
        Arrays.sort(array);

        //记录放入背包的数量
        double[] x = new double[count];
        int aidx = array.length-1;
        while (volume > 0) {//剩余容量大于0
            //获取物品的下标
            double unitPrice = (double)array[aidx--];
            String[] strings = ppw.get(unitPrice).split(",");
            for (int i = 0; i < strings.length; i++) {
                int idx = Integer.valueOf(strings[i]);
                if(weight[idx] < volume) {//如果物品重量小于容量，直接放入
                    x[idx] = 1;
                    volume -= weight[idx];
                } else {//如果物品重量大于容量，部分放入
                    x[idx] = 1.0 * volume / weight[idx];
                    volume = 0;
                }
            }
        }
        System.out.println(Arrays.toString(x));

    }
}
