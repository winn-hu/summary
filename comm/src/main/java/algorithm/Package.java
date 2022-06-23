package algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *�������⣺��0-1�����������ƣ�����ÿ����Ʒ�����ַ��뱳����
 */
public class Package {

    public static void main(String[] args) {
        int[] weight = new int[]{30, 10, 20, 50, 40};
        int[] price  = new int[]{65, 20, 30, 60, 40};
        int count = price.length;
        int volume = 100;

        //��¼��λ�����ļ�ֵ
        int[] pricePerWeight = new int[count];
        for (int i = 0; i < count; i++) {
            pricePerWeight[i] = price[i] / weight[i];
        }

        //���ݵ�λ��ֵ����[ʹ��map��Ϊ�˱������ظ��ĵ���]
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

        //��¼���뱳��������
        double[] x = new double[count];
        int aidx = array.length-1;
        while (volume > 0) {//ʣ����������0
            //��ȡ��Ʒ���±�
            double unitPrice = (double)array[aidx--];
            String[] strings = ppw.get(unitPrice).split(",");
            for (int i = 0; i < strings.length; i++) {
                int idx = Integer.valueOf(strings[i]);
                if(weight[idx] < volume) {//�����Ʒ����С��������ֱ�ӷ���
                    x[idx] = 1;
                    volume -= weight[idx];
                } else {//�����Ʒ�����������������ַ���
                    x[idx] = 1.0 * volume / weight[idx];
                    volume = 0;
                }
            }
        }
        System.out.println(Arrays.toString(x));

    }
}
