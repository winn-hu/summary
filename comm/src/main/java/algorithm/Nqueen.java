package algorithm;

/**
 *
 */
public class Nqueen {

    private static final int N = 4;
    private static final int[] row = new int[N+1];

    public static void main(String[] args) {
        backtracking(1);


    }

    private static void backtracking(int rowNum) {
        for (int colNum = 1; colNum <= N; colNum++) {
            row[rowNum] = colNum;
            if(isPlace(rowNum)) {
                if(rowNum == N) {
                    print();
                } else {
                    backtracking(rowNum+1);
                }
            }
        }
    }

     private static boolean isPlace(int rowNum) {
         for (int i = 1; i < rowNum; i++) {
             boolean isSlash = Math.abs(row[i] - row[rowNum]) == (rowNum - i);
             //判断是否在同一列或者在同一斜线上
             if(row[i] == row[rowNum] || isSlash) {
                 return false;
             }
         }
         return true;
     }

     private static void print() {
         StringBuilder builder = new StringBuilder("[");
         for (int i = 1; i < row.length; i++) {
             builder.append(row[i]+",");
         }
         String result = builder.substring(0, builder.length()-1) + "]";
         System.out.println(result);
     }

}
