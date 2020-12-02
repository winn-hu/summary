package utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.util.Base64;

public class CipherUtil {

    //密钥
    private static final String KEY_STR = "sadfgrgv";
    //字符集
    private static final String CHARSET = "UTF-8";
    //加密类型
    private static final String ALGORITHM = "DES";
    //加密/解密算法 - 工作模式 - 填充模式
    private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";
    //偏移变量，固定占8位字节
    private static final String IV_PARA = "12345678";



    /**
     * 生成key
     * @return key
     *
     */
    private static Key generateKey() throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec(KEY_STR.getBytes(CHARSET));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return keyFactory.generateSecret(desKeySpec);
    }

    private static String process(Type type, String str) {
        try {
            Key key = generateKey();
            IvParameterSpec spec = new IvParameterSpec(IV_PARA.getBytes(CHARSET));
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Type.ENCRYPT.equals(type) ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, key, spec);
            byte[] bytes = str.getBytes(CHARSET);
            if(Type.ENCRYPT.equals(type)){
                bytes = Base64.getEncoder().encode(cipher.doFinal(bytes));
            }else {
                bytes = cipher.doFinal(Base64.getDecoder().decode(bytes));
            }
            return new String(bytes);
        }catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String encrypt(String str) {
        return process(Type.ENCRYPT, str);
    }

    public static String decrypt(String str) {
        return process(Type.DECRYPT, str);
    }

    enum Type {
        ENCRYPT,DECRYPT
    }

    public static void main(String[] args) {
        String root = encrypt("root");
        System.out.println(root);
        String decrypt = decrypt(root);
        System.out.println(decrypt);

    }
}
