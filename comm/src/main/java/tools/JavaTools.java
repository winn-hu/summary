package tools;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class JavaTools {

	public static void romoveAnnotation(String srcPath, String destPath){

		try(BufferedWriter writer = new BufferedWriter(new FileWriter(destPath));
			BufferedReader br = new BufferedReader(new FileReader(srcPath))) {
			String context;
			while((context = br.readLine()) != null){
				//remove annotation line & empty line
				if(!context.startsWith("#") && !StringUtils.hasText(context)){
					writer.write(context);
					writer.newLine();
				}
			}
			System.out.println("success...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getHeadCharbyChinses(String chinese) throws BadHanyuPinyinOutputFormatCombination {
		StringBuffer result = new StringBuffer();
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		char[] chars = chinese.toCharArray();
		for (char val : chars) {
			char headChar = PinyinHelper.toHanyuPinyinStringArray(val, format)[0].charAt(0);
			result.append(headChar);
		}
		return result.toString();
	}

}
