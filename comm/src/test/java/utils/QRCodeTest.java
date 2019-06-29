package utils;

import org.junit.Test;

public class QRCodeTest {
	
	@Test
	public  void createQRCode() {
		//If the context starts with 'https://', then  is hyperlink, else it is text
		String context = "https://www.baidu.com";
		try {
			QRCodeUtils.createQRCode(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void decodeQRCode(){
		try {
			String context = QRCodeUtils.decodeQRCode();
			System.out.println(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
