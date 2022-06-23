package leetCode.ten;

/**
 * Î´Íê³É
 */
public class Match2 {

    public static void main(String[] args) {
        System.out.println(isMatch("aab", "c*a*b"));
    }

    public static boolean isMatch(String s, String p) {
        if(p.startsWith("*") || p.contains("**")) return false;


        int sidx = 0;
        String[] ps = p.split("\\*");
        for (int i = 0; i < ps.length; i++) {
            if(ps[i].length() == 1) {
                while(s.charAt(sidx) == ps[i].charAt(0)) {
                    if(sidx == s.length()-1) {
                        break;
                    }
                    sidx++;
                }
            }else {
                int pidx = 0;



                while(s.charAt(sidx) == ps[i].charAt(pidx)) {
                    if(sidx == s.length()-1) {
                        break;
                    }
                    sidx++;
                }
            }
        }

        return sidx == s.length()-1;
    }
}
