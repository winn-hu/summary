package leetCode.five;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 5. ������Ӵ�
 *
 *
 * ˼·��
 * 1�� ��ȡÿһ���ַ����ֵ��±�
 * 2�� ����ַ����ֲ�ֹһ�Σ����αȽ������±꣬�ж��������ַ��Ƿ����ڻ��ģ�
 * 3)  ���泤����Ļ����ַ����������
 * 4�� ����������Ϊ0���򷵻��ַ����ĵ�һ���ַ�
 *
 *�÷�ʽЧ�ʵ�
 *
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(method("jkexvzsqshsxyytjmmhauoyrbxlgvdovlhzivkeixnoboqlfemfzytbolixqzwkfvnpacemgpotjtqokrqtnwjpjdiidduxdprngvitnzgyjgreyjmijmfbwsowbxtqkfeasjnujnrzlxmlcmmbdbgryknraasfgusapjcootlklirtilujjbatpazeihmhaprdxoucjkynqxbggruleopvdrukicpuleumbrgofpsmwopvhdbkkfncnvqamttwyvezqzswmwyhsontvioaakowannmgwjwpehcbtlzmntbmbkkxsrtzvfeggkzisxqkzmwjtbfjjxndmsjpdgimpznzojwfivgjdymtffmwtvzzkmeclquqnzngazmcfvbqfyudpyxlbvbcgyyweaakchxggflbgjplcftssmkssfinffnifsskmsstfclpjgblfggxhckaaewyygcbvblxypduyfqbvfcmzagnznquqlcemkzzvtwmfftmydjgvifwjoznzpmigdpjsmdnxjjfbtjwmzkqxsizkggefvztrsxkkbmbtnmzltbchepwjwgmnnawokaaoivtnoshywmwszqzevywttmaqvncnfkkbdhvpowmspfogrbmuelupcikurdvpoelurggbxqnykjcuoxdrpahmhiezaptabjjulitrilkltoocjpasugfsaarnkyrgbdbmmclmxlzrnjunjsaefkqtxbwoswbfmjimjyergjygzntivgnrpdxuddiidjpjwntqrkoqtjtopgmecapnvfkwzqxilobtyzfmeflqobonxiekvizhlvodvglxbryouahmmjtyyxshsqszvxekj"));
    }

    public static String method(String s) {
        StringBuilder result = new StringBuilder();

        //��ȡÿһ���ַ����ֵ��±�
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            String key = s.charAt(i)+"";
            String value  = map.get(key) != null ? map.get(key) : "";
            map.put(key,value+i+",");
        }

        Iterator<String> keys = map.keySet().iterator();
        while(keys.hasNext()) {
            String value = map.get(keys.next());

            //�ַ����ֲ�ֹһ��
            if(value.lastIndexOf(",") != 1) {
                String[] idx = value.split(",");
                for (int i = 0; i < idx.length-1; i++) {
                    for (int j = i+1; j < idx.length; j++) {
                        //current��tail����ÿһ�ֳ�ʼ��Ϊ��
                        StringBuilder current = new StringBuilder();
                        StringBuilder tail = new StringBuilder();

                        //�Ƚ������±꣬�ж��������ַ��Ƿ����ڻ���
                        int headIdx = Integer.valueOf(idx[i]);
                        int tailIdx = Integer.valueOf(idx[j]);
                        while(headIdx < tailIdx) {
                            if(s.charAt(headIdx) == s.charAt(tailIdx)) {
                                current.append(s.charAt(headIdx));
                                tail.append(s.charAt(headIdx));
                                headIdx++;
                                tailIdx--;
                            }else {
                                break;
                            }
                        }
                        if(headIdx >= tailIdx) {
                            if(headIdx == tailIdx) {
                                current.append(s.charAt(headIdx));
                            }
                            current.append(tail.reverse());
                            if(result.length() < current.length()) {
                                result = current;
                            }
                        }
                    }
                }
            }
        }
        if(result.length() == 0) return s.charAt(0)+"";

        return result.toString();
    }
}
