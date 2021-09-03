package jdbc;

import utils.JDBCUtil;

import java.io.*;
import java.sql.*;
import java.util.Objects;

/**
 * ���ܴ��ı��ļ�
 * @author HuWei
 *
 */

public class BlobUtil {

	static String URL = "jdbc:oracle:thin:@localhost:1521:XE"; // �����ַ���
	static String USER_NAME = "etc1616"; // ���ݿ��û���
	static String PASSWORD = "123"; // ����

	static void write(OutputStream os, InputStream is) {
		int len;
		int filesize=0;
		byte[] b = new byte[102400];
		try {
			while((len=is.read(b))!=-1){
				os.write(b,0,len);
				filesize+=len;
			}
			System.out.println("����ɹ����ļ����ֽ���Ϊ��"+filesize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readBlobToFile(int id,File f){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// ������Ӷ���
			con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			//����SQL��䣬����ѯ
			ps = con.prepareStatement("select * from jdbc_blob where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			Blob content = null;
			if(rs.next()){
				content = rs.getBlob(3);
			}

			InputStream is= Objects.requireNonNull(content).getBinaryStream();
			OutputStream os = new FileOutputStream(f);
			write(os, is);
			is.close();
			os.close();
		} catch (SQLException | IOException sqle) {
			sqle.printStackTrace();
		} finally {
			JDBCUtil.close(con,ps,rs);
		}
	}

	public static void save(int id,File f){
		PreparedStatement ps = null;
		Connection con = null;
		try { 
			// ������Ӷ���
			con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			//����SQL��䣬����ѯ
			ps = con.prepareStatement("insert into jdbc_blob values(?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, f.getName());
			//����Blob����
			Blob content = con.createBlob();
			ps.setBlob(3, content);

			OutputStream os = content.setBinaryStream(0);
			InputStream is = new FileInputStream(f);
			write(os, is);
			ps.execute();

			is.close();
			os.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con,ps,null);
		}
	}


	public static void main(String[] args) {
		File f = new File("G:\\MV\\��������.mp4");
		save(7,f);

		File f1 = new File("G:\\�鼮\\ͦ��111.txt");
		int id=5;
		readBlobToFile(id,f1);
	}
}
