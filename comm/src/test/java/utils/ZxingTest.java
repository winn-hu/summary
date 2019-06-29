package utils;

import org.junit.Test;

import com.google.zxing.Result;

public class ZxingTest {

	@Test
	public void createZxing() {
		//context
		StringBuilder builder = new StringBuilder(200);
		builder.append("BEGIN:VCARD\n")
			.append("VERSION:1.0\n")
			.append("N:Winn;Tiger\n")
			.append("ORG:eBaoTech\n")
			.append("TEL;WORK;VOICE:15751005439\n")
			.append("END:VCARD");
		
		ZxingUtils.createZxing(builder.toString());
	}
	
	@Test
	public void decodeZxing() {
		Result result = ZxingUtils.decodeZxing();
		if(result != null){
			System.out.println("Result : "+result.toString());			
		}
	}
	
	@Test
	public void createZxingWithLogo() {
		//String context = "https://www.baidu.com";
		StringBuilder builder = new StringBuilder(200);
		builder.append("BEGIN:VCARD\n")
			.append("VERSION:1.0\n")
			.append("N:Blue Star\n")
			.append("ORG:eBaoTech\n")
			.append("TEL;WORK;VOICE:157****5439\n")
			.append("X:1147*****14\n")
			.append("REV:1147*****14\n")
			.append("EMAIL;PREF;INTERNET:157****5439@163.com\n")
			.append("END:VCARD");
		ZxingWithLogoUtils.createZxingWithLog(builder.toString(),Constant.IMG_PATH+"email.jpg");
	}
}
