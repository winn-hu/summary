package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class ZxingUtils {

	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;
	private static final String PICTURE_FORMAT = "png";
	private static final String PATH = Constant.IMG_PATH + "zxing.png";
	private static final String CHARACTER = "utf-8";
	private static final ErrorCorrectionLevel ERROR_CORRECT_LEVEL = ErrorCorrectionLevel.H;
	
	public static void createZxing(String context){
		//define
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, CHARACTER);
		hints.put(EncodeHintType.ERROR_CORRECTION, ERROR_CORRECT_LEVEL);
		hints.put(EncodeHintType.MARGIN, 2);//�߿�߾࣬Ĭ��Ϊ5
		
		try {
			BitMatrix matrix = new MultiFormatWriter().encode(context, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
			Path file = new File(PATH).toPath();
			MatrixToImageWriter.writeToPath(matrix, PICTURE_FORMAT, file);
		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Result decodeZxing(){
		Map<DecodeHintType,Object> hints = new HashMap<>();
		hints.put(DecodeHintType.CHARACTER_SET, CHARACTER);
		
		try {
			BufferedImage image = ImageIO.read(new File(PATH));
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
