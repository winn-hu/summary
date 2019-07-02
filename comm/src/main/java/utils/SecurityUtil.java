package utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {


    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String CHARACTOR_FORMAT = "GBK";


    /**
     * MD5 加密类型
     */
    public enum MD5_LENGTH{

        LENGTH_16(16),
        LENGTH_32(21);

        int length;
        MD5_LENGTH(int length){

            this.length = length;
        }
    }

    /**
     * 编码为 base64
     *
     * @param src
     * @return
     */
    public static String encodeForBase64(String src){

        try {
            return Base64.encodeBase64String(src.getBytes(CHARACTOR_FORMAT));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 解码为 base64
     *
     * @param src
     * @return
     */
    public static String decodeForBase64(String src){

        try {
            return new String(Base64.decodeBase64(src), CHARACTOR_FORMAT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * MD5 摘要算法
     *
     * @param source
     * @param length
     * @return
     */
    private static String MD5(String source, MD5_LENGTH length) {

        String result = "";
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();

            return length == MD5_LENGTH.LENGTH_32 ? result : buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * MD5 摘要算法（16 位）
     *
     * @return
     */
    public static String MD5_16(String source) {
        return MD5(source, MD5_LENGTH.LENGTH_16);
    }

    /**
     * MD5 摘要算法（32 位）
     *
     * @return
     */
    public static String MD5_32(String source) {
        return MD5(source, MD5_LENGTH.LENGTH_32);
    }

    /**
     * 生成密钥
     */
    public static String generateKey(){
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            SecretKey secretKey = kg.generateKey();

            return Base64.encodeBase64String(secretKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 加密数据
     *
     * @param content
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String key){

        try {

            Key keyObj = new SecretKeySpec(Base64.decodeBase64(key), "AES");
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keyObj);

            return Base64.encodeBase64String(cipher.doFinal(content.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 解密数据
     *
     * @param content
     * @param key
     * @return
     * @throws Exception
     */
    public static String decrypt(String content, String key){
        try {
            Key keyObj = new SecretKeySpec(Base64.decodeBase64(key), "AES");
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keyObj);

            return new String(cipher.doFinal(Base64.decodeBase64(content)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
