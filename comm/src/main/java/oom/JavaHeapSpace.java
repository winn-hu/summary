package oom;

import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * ≥¨¥Û∂‘œÛ
 */
public class JavaHeapSpace {

    private static final int SIZE = 2 * 1024 * 2014;

    public static void main(String[] args){
        List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
        System.out.println("JVM Arguments : " + inputArguments);

        int[] arr = new int[SIZE];
    }
}
