package utils;

import java.io.File;

public class  FileUtil {

    public static void findFile(File file,Method method) throws Exception {
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            if(files == null ) return;

            for (File subFile : files) {
                findFile(subFile,method);
            }
        } else {
            method.invoke(file.getPath());
        }
    }
}
