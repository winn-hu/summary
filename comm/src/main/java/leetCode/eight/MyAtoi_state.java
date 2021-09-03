package leetCode.eight;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 思路：状态机
 *
 * 1）初始状态都为start，需要相应的字符变换相应的状态
 *                    +/-      number       other
 *     start         sign     int_number     end
 *     sign          end      int_number     end
 *     int_number    end      int_number     end
 *
 * 2）遍历字符串，获取状态。遇到“end”状态停止遍历
 *
 * 3）根据每个字符获取的状态执行相应的逻辑
 *
 * 4）计算相应的结果值
 *
 */
public class MyAtoi_state {

    public static void main(String[] args) {
        System.out.println(atoi(" 42"));
    }

    public static int atoi(String s) {
        StateMachine machine = new StateMachine();
        char[] chars = s.trim().toCharArray();
        for (char c : chars) {
            String state = machine.getSate(c);
            if ("end".equals(state)) break;
        }
        return (int) machine.result * machine.sign;
    }

    static class StateMachine {

        long result;
        int sign = 1;
        String state = "start";
        private static Map<String,String[]> stateMap = new HashMap<>();
        static {
            stateMap.put("start",new String[]{"sign","int_number","end"});
            stateMap.put("sign",new String[]{"end","int_number","end"});
            stateMap.put("int_number",new String[]{"end","int_number","end"});
        }

        public String getSate(char c) {
            state = stateMap.get(state)[getCol(c)];
            if("sign".equals(state)) {
                sign = (c == '+' ? 1 : -1);
            }else if ("int_number".equals(state)) {
                result = result * 10 + c - '0';
                result = (sign == 1) ? Math.min(result, Integer.MAX_VALUE) : Math.min(result, -(long)Integer.MIN_VALUE);
            }
            return state;

        }

        public int getCol(char c) {
            if(c == '+' || c == '-') return 0;
            if (Character.isDigit(c)) return 1;
            return 2;
        }

    }
}
