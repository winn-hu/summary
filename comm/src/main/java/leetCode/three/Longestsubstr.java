package leetCode.three;

import java.util.ArrayList;
import java.util.List;

/**
 * 3. 无重复字符的最长子串
 *
 */
public class Longestsubstr {

    public static void main(String[] args) {
        System.out.println(num("aabaab!bb"));
    }

    public static int num(String s) {
        int result = 0;
        int length = s.length();
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if(list.contains(c)){
                result = Math.max(result, list.size());
                list = list.subList(list.indexOf(c)+1,list.size());
            }
            list.add(c);
        }
        return Math.max(result, list.size());
    }
}
