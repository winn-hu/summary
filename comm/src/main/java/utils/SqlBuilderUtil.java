package utils;

import java.io.BufferedReader;
import java.io.FileReader;

public class SqlBuilderUtil {

	private static final String PATH = "./src/main/resources/sql.txt";

	protected static void printBuider(){
		try(BufferedReader br = new BufferedReader(new FileReader(PATH))) {
			System.out.println("StringBuilder sql = new StringBuilder();");
			System.out.print("sql");
			String context;
			int line = 0;
			while((context = br.readLine()) != null){
				if(line == 0){
					System.out.println(String.format(".append(\" %s \\n\")", context));
				}else{
					System.out.println(String.format("   .append(\" %s \\n\")", context));
				}
				line++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected static void printSplicer(){
		try(BufferedReader br = new BufferedReader(new FileReader(PATH))) {
			String context;
			while((context = br.readLine()) != null){
				System.out.println(String.format("\" %s \\n\"+", context));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
