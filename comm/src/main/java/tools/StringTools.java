package tools;

import java.io.BufferedReader;
import java.io.FileReader;

public class StringTools {

    enum Type {
        IS_APPEND
    }

    /**
     * Éú³Ésql×Ö¶Î´Ü´úÂë
     * @param type
     * @param path
     */
    public static void sqlBuilder(Type type, String path){
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            if(type == Type.IS_APPEND){
                System.out.println("StringBuilder sql = new StringBuilder();");
                System.out.println("sql");
                String context;
                while((context = br.readLine()) != null){
                    System.out.println(String.format(".append(\" %s \\n\")", context));
                }
            }else{
                String context;
                while((context = br.readLine()) != null){
                    System.out.println(String.format("\" %s \\n\"+", context));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
