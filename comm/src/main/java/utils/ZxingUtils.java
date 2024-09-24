package utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ZxingUtils {

	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;
	private static final String PICTURE_FORMAT = "png";
	private static final String PATH = Constant.IMG_PATH + "zxing.png";
	private static final String CHARACTER = "utf-8";
	private static final ErrorCorrectionLevel ERROR_CORRECT_LEVEL = ErrorCorrectionLevel.H;
	
	public static void createZxing(String context){
		//define
		Map<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, CHARACTER);
		hints.put(EncodeHintType.ERROR_CORRECTION, ERROR_CORRECT_LEVEL);
		hints.put(EncodeHintType.MARGIN, 2);
		
		try {
			BitMatrix matrix = new MultiFormatWriter().encode(context, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
			Path file = new File(PATH).toPath();
			MatrixToImageWriter.writeToPath(matrix, PICTURE_FORMAT, file);
		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Result decodeZxing(String file){
		Map<DecodeHintType,Object> hints = new HashMap<>();
		hints.put(DecodeHintType.CHARACTER_SET, CHARACTER);
		
		try {
			BufferedImage image = ImageIO.read(new File(file));
			BufferedImageLuminanceSource luminanceSource = new BufferedImageLuminanceSource(image);
			HybridBinarizer hybridBinarizer = new HybridBinarizer(luminanceSource);
			BinaryBitmap binaryBitmap = new BinaryBitmap(hybridBinarizer);
			return new MultiFormatReader().decode(binaryBitmap , hints);
		} catch (NotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
