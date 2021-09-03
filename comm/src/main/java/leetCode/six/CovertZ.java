package leetCode.six;

import java.util.ArrayList;
import java.util.List;

/**
 * 6. Z 字形变换
 * 思路：
 * 1）为每一行创建一个单独的字符存储器
 * 2）从左向右遍历数组，确定每个字符存在第几行；
 * 3）观察发现：字符从左到右，先从上倒下存到行中，在反向存入行中。
 *      所以需要两个变量：一个用来控制方向，一个用来表示存到第几行。
 */
public class CovertZ {
    public static void main(String[] args) {
        String res = covert("ABCDEF", 4);
        System.out.println(res.equals("ABFCED"));
    }

    public static String covert(String s, int numRows) {

        if(numRows == 1 || numRows > s.length()) return s;

        //行数由字符串的长度及给定的行数值的最小值确定；
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        char[] chars = s.toCharArray();
        //当前行的行数标记
        int  curRow = 0;
        //方向标记
        boolean isDown = false;
        for (char ch : chars) {
            rows.get(curRow).append(ch);

            //第一行及最后一行需要换方向
            if(curRow == 0 || curRow == numRows - 1) {
                isDown = !isDown;
            }
            //向下，行数+1；向上，行数-1.
            //curDown += isDown ? 1 : -1;
            if (isDown) {
                curRow++;
            }else {
                curRow--;
            }
        }

        StringBuilder result  = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }

}
