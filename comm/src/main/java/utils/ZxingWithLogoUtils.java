package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class ZxingWithLogoUtils {
	
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;
	private static final String CHARACTER = "utf-8";
	private static final String PICTURE_FORMAT = "png";
	private static final String PATH = Constant.IMG_PATH + "zxing.png";
	private static final ErrorCorrectionLevel ERROR_CORRECT_LEVEL = ErrorCorrectionLevel.H;
	
	public static void createZxingWithLog(String context, String logoPath){
		//define
		Map<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, CHARACTER);
		hints.put(EncodeHintType.ERROR_CORRECTION, ERROR_CORRECT_LEVEL);
		hints.put(EncodeHintType.MARGIN, 2);//default margin is 5.
		
		try {
			BitMatrix matrix = new MultiFormatWriter().encode(context, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
			BufferedImage image = toBufferedImage(matrix);
			image = addLogo(image, logoPath);
			File file = new File(PATH);
			boolean result = ImageIO.write(image, PICTURE_FORMAT, file);
			if(result){
				System.out.println("generate successful.");
			}else{
				System.out.println("generate failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static BufferedImage toBufferedImage(BitMatrix matrix) {  
        int width = matrix.getWidth();  
        int height = matrix.getHeight();  
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        for (int x = 0; x < width; x++) {  
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y,  (matrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB()));  
            }  
        }  
        return image;  
    }  
	
	private static BufferedImage addLogo(BufferedImage image, String logoPath) {
		
		Graphics2D graphics = image.createGraphics();
		int width = image.getWidth() / 5 ;
		int height = image.getHeight() / 5;
		try {
			BufferedImage logoImage = ImageIO.read(new File(logoPath));
			
			graphics.drawImage(logoImage, width*2, height*2, width, height, null);
			
			//paint
	         BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);   
	         graphics.setStroke(stroke);
	         
	         //Arc rectangle
	         RoundRectangle2D.Float round = new RoundRectangle2D.Float(width*2, height*2, width, height,20,20);  
	         graphics.setColor(Color.white);  
	         graphics.draw(round);
	         
	         //repaint logo
	         BasicStroke stroke2 = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);   
	         graphics.setStroke(stroke2);
	         RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(width*2+2, height*2+2, width-4, height-4,20,20);  
	         graphics.setColor(new Color(128,128,128));  
	         graphics.draw(round2);
	           
	         graphics.dispose();  
	         image.flush() ;  

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return image;
	}
}
