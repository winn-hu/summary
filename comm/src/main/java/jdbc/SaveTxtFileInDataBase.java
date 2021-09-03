package jdbc;

import java.io.*;
import java.sql.*;

public class SaveTxtFileInDataBase {
	public static void saveTxt(int id,File f){
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "etc1616";
		String password = "123";
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			PreparedStatement ps = conn.prepareStatement("insert into jdbc_clob values(?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, f.getName());
			Clob content = conn.createClob();
			ps.setClob(3, content);
			
			Writer write = content.setCharacterStream(1);
			Reader reader = new FileReader(f);
			io(write, reader);
			ps.execute();
			ps.close();
			conn.close();
		} catch (SQLException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void io(Writer writer,Reader reader){
		int character;
		try {
			while((character = reader.read()) != -1){
				writer.write(character);
			}
			writer.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		saveTxt(8,new File("G:\\ ÈºÆ\\Õ¶æ≠.txt"));
	}
}
