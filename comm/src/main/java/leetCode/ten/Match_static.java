package leetCode.ten;

/**
 * Î´Íê³É
 */
public class Match_static {

    public static void main(String[] args) {
        System.out.println(isMatch("aab", "cca*b"));
    }

    public static boolean isMatch(String s, String p) {
        if(p.startsWith("*") || p.contains("**")) return false;

        int i = s.length()-1, j = p.length()-1;
        while(i >= 0 ) {
            if( j < 0) return false;

            char a = p.charAt(j);
            char b = s.charAt(i);
            if(a != '*') {
                if(a != '.' && b != a) {
                    return false;
                }else{
                    i = i-1;
                    j = j-1;
                }
            }else {
                j = j - 1;
                char c = p.charAt(j);
                if(c != '.' && b != c) {
                    j = j - 1;
                }else {
                    i = i - 1;
                }
            }
        }
        while(j >= 0) {
            if(p.charAt(j) == '*') {
                j = j -2;
            }else {
                return false;
            }
        }
        return true;



       /* boolean[][] sp = new boolean[s.length()+1][p.length()+1];
        sp[0][0] = true;
        for (int i = s.length()-1; i >=0; i--) {
            for (int j = p.length()-1; j >= 0 ; j--) {
                if(p.charAt(j) != '*') {
                    if(p.charAt(j) != '.' && s.charAt(i) != p.charAt(j)) {
                        return false;
                    }else{
                        sp[i][j] = sp[i-1][j-1];
                    }
                }else {
                    if(p.charAt(j-1) != '.' && s.charAt(i) != p.charAt(j-1)) {
                        sp[i][j] = sp[i][j-2];
                    }else {
                        sp[i][j] =  sp[i-1][j];
                    }
                }
            }
        }
        return true;*/
    }
}
