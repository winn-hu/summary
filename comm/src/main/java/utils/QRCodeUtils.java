package utils;

import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;
import utils.pojo.MyQRCodeImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 生成二维码
 */
public class QRCodeUtils {

	private static final char ERROR_CORRECT_LEVEL = 'M';
	private static final int VERSION = 7;
	private static final int OFFSET = 2;
	private static final String PICTURE_FORMAT = "png";
	private static final String PATH = Constant.IMG_PATH + "qrcode.png";
	private static final String CHARACTER = "utf-8";
	
	public static void createQRCode(String context) throws Exception{
		
		Qrcode qrCode = new Qrcode();
		qrCode.setQrcodeErrorCorrect(ERROR_CORRECT_LEVEL);
		qrCode.setQrcodeEncodeMode('B');//N:number   A:letter  B:other character
		qrCode.setQrcodeVersion(VERSION);
		
		int width = 67 + 12 * (VERSION - 1);
		int height = 67 + 12 * (VERSION - 1);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();
		graphics.setBackground(Color.WHITE);
		graphics.setColor(Color.BLACK);
		graphics.clearRect(0, 0, width, height);
		
		byte[] bytes = context.getBytes(CHARACTER);
		if(bytes.length > 0 && bytes.length < 120) {
			boolean[][] bs = qrCode.calQrcode(bytes);
			for (int i = 0; i < bs.length; i++) {
				for (int j = 0; j < bs[i].length; j++) {
					if(bs[j][i]){
						//If has not offset, system will throw Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: -1 when encoding.
						graphics.fillRect(j*3+OFFSET, i*3+OFFSET, 3, 3);
					}
				}
			}
		}
		
		graphics.dispose();
		image.flush();
		
		ImageIO.write(image, PICTURE_FORMAT, new File(PATH));
	}
	
	public static String decodeQRCode() throws Exception{
		QRCodeDecoder decoder = new QRCodeDecoder();
		
		File file = new File(PATH);
		BufferedImage image = ImageIO.read(file);
		MyQRCodeImage myImage = new MyQRCodeImage(image);
		
		byte[] bytes = decoder.decode(myImage);
		return new String(bytes,CHARACTER);
	}
}
