package utils.pojo;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;

public class MyQRCodeImage implements QRCodeImage{
	
	private BufferedImage image;
	
	public MyQRCodeImage(BufferedImage image) {
		this.image = image;
	}

	@Override
	public int getHeight() {
		return image.getHeight();
	}

	@Override
	public int getPixel(int arg0, int arg1) {
		return image.getRGB(arg0, arg1);
	}

	@Override
	public int getWidth() {
		return image.getWidth();
	}

}
