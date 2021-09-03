package leetCode.five;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 5. 最长回文子串
 *
 *
 * 思路：
 * 1） 获取每一个字符出现的下标
 * 2） 如果字符出现不止一次，依次比较两个下标，判断区间内字符是否属于回文；
 * 3)  保存长度最长的回文字符串到结果里
 * 4） 如果结果长度为0，则返回字符串的第一个字符
 *
 *该方式效率低
 *
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(method("jkexvzsqshsxyytjmmhauoyrbxlgvdovlhzivkeixnoboqlfemfzytbolixqzwkfvnpacemgpotjtqokrqtnwjpjdiidduxdprngvitnzgyjgreyjmijmfbwsowbxtqkfeasjnujnrzlxmlcmmbdbgryknraasfgusapjcootlklirtilujjbatpazeihmhaprdxoucjkynqxbggruleopvdrukicpuleumbrgofpsmwopvhdbkkfncnvqamttwyvezqzswmwyhsontvioaakowannmgwjwpehcbtlzmntbmbkkxsrtzvfeggkzisxqkzmwjtbfjjxndmsjpdgimpznzojwfivgjdymtffmwtvzzkmeclquqnzngazmcfvbqfyudpyxlbvbcgyyweaakchxggflbgjplcftssmkssfinffnifsskmsstfclpjgblfggxhckaaewyygcbvblxypduyfqbvfcmzagnznquqlcemkzzvtwmfftmydjgvifwjoznzpmigdpjsmdnxjjfbtjwmzkqxsizkggefvztrsxkkbmbtnmzltbchepwjwgmnnawokaaoivtnoshywmwszqzevywttmaqvncnfkkbdhvpowmspfogrbmuelupcikurdvpoelurggbxqnykjcuoxdrpahmhiezaptabjjulitrilkltoocjpasugfsaarnkyrgbdbmmclmxlzrnjunjsaefkqtxbwoswbfmjimjyergjygzntivgnrpdxuddiidjpjwntqrkoqtjtopgmecapnvfkwzqxilobtyzfmeflqobonxiekvizhlvodvglxbryouahmmjtyyxshsqszvxekj"));
    }

    public static String method(String s) {
        StringBuilder result = new StringBuilder();

        //获取每一个字符出现的下标
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            String key = s.charAt(i)+"";
            String value  = map.get(key) != null ? map.get(key) : "";
            map.put(key,value+i+",");
        }

        Iterator<String> keys = map.keySet().iterator();
        while(keys.hasNext()) {
            String value = map.get(keys.next());

            //字符出现不止一次
            if(value.lastIndexOf(",") != 1) {
                String[] idx = value.split(",");
                for (int i = 0; i < idx.length-1; i++) {
                    for (int j = i+1; j < idx.length; j++) {
                        //current、tail保持每一轮初始都为空
                        StringBuilder current = new StringBuilder();
                        StringBuilder tail = new StringBuilder();

                        //比较两个下标，判断区间内字符是否属于回文
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
