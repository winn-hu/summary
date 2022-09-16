package oom;

import javassist.CannotCompileException;
import javassist.ClassPool;

import java.lang.management.ManagementFactory;
import java.util.List;

public class Metaspace {

    public static void main(String[] args) throws CannotCompileException {
        List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
        System.out.println("JVM Arguments : " + inputArguments);

        for (int i = 0; i < 100000000; i++) {
            ClassPool.getDefault().makeClass("User" + i).toClass();
        }
    }
}
