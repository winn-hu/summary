package leetCode.ten;

import java.util.Deque;
import java.util.LinkedList;

public class Match {

    public static void main(String[] args) {
        System.out.println(isMatch("aab", "c*a*b"));
    }

    public static boolean isMatch(String s, String p) {
        if(p.startsWith("*") || p.contains("**")) return false;

        Deque<Character> targetDeque = new LinkedList<>();
        char[] targets = s.toCharArray();
        for (char c : targets) {
            targetDeque.add(c);
        }


        Character preMatch = null;
        char[] matches = p.toCharArray();
        for (int i = 0, len = matches.length; i < len; i++) {
            Character target = targetDeque.pop();
            if (matches[i] != '.') {
                if(target != matches[i] && matches[++i] != '*') {
                    return false;
                }
            }

            while(matches[i] == '*' && targetDeque.size() != 0) {
                target = targetDeque.pop();
                if(preMatch != '.') {
                    if(target != preMatch) {
                        break;
                    }
                }
            }

            preMatch = matches[i];

            if(targetDeque.size() == 0)  {
                Deque<Character> restDeque = new LinkedList<>();
                for (int j = i+1; j < len; j++) {
                    restDeque.add(matches[j]);
                }

                if(restDeque.size() % 2 == 1) return false;

                while(restDeque.size() != 0) {
                    restDeque.pop();
                    if(restDeque.pop() != '*') return false;
                }
                return true;

            }


        }

        return targetDeque.size() == 0;
    }
}
