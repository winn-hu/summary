package tools;

import utils.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;

public class JavaTools {

	public static final int BUILDER_TYPE_APPEND = 1;
	public static final int BUILDER_TYPE_PLUS = 2;

	public static void printBuilder(int type, String path){
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			if(type == BUILDER_TYPE_APPEND){
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

	public static void romoveAnnotation(String srcPath, String destPath){

		try(BufferedWriter writer = new BufferedWriter(new FileWriter(destPath));
			BufferedReader br = new BufferedReader(new FileReader(srcPath))) {
			String context;
			while((context = br.readLine()) != null){
				//remove annotation line & empty line
				if(!context.startsWith("#") && !StringUtils.isEmpty(context)){
					writer.write(context);
					writer.newLine();
				}
			}
			System.out.println("success...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
