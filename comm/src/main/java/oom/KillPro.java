package oom;

import java.util.List;

public class KillPro {

    public static void main(String[] args){
        List<int[]> l = new java.util.ArrayList<>();
        for (int i = 10000; i < 100000; i++) {
            try {
                l.add(new int[100000000]);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

}
