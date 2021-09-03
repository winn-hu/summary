package leetCode.six;

import java.util.ArrayList;
import java.util.List;

/**
 * 6. Z ���α任
 * ˼·��
 * 1��Ϊÿһ�д���һ���������ַ��洢��
 * 2���������ұ������飬ȷ��ÿ���ַ����ڵڼ��У�
 * 3���۲췢�֣��ַ������ң��ȴ��ϵ��´浽���У��ڷ���������С�
 *      ������Ҫ����������һ���������Ʒ���һ��������ʾ�浽�ڼ��С�
 */
public class CovertZ {
    public static void main(String[] args) {
        String res = covert("ABCDEF", 4);
        System.out.println(res.equals("ABFCED"));
    }

    public static String covert(String s, int numRows) {

        if(numRows == 1 || numRows > s.length()) return s;

        //�������ַ����ĳ��ȼ�����������ֵ����Сֵȷ����
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        char[] chars = s.toCharArray();
        //��ǰ�е��������
        int  curRow = 0;
        //������
        boolean isDown = false;
        for (char ch : chars) {
            rows.get(curRow).append(ch);

            //��һ�м����һ����Ҫ������
            if(curRow == 0 || curRow == numRows - 1) {
                isDown = !isDown;
            }
            //���£�����+1�����ϣ�����-1.
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
