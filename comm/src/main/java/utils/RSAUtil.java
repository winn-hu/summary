package utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

/**
 * 任务:KYEEAPP-1066
 * 描述：RSA 工具类。提供加密，解密，生成密钥对等方法。
 * 需要到http://www.bouncycastle.org下载bcprov-jdk14-123.jar。
 *
 */
public class RSAUtil {

	private static String RSAKeyStore = "RSAKey.txt";
	
	private static final BouncyCastleProvider PROVIDER = new BouncyCastleProvider();
	
	static {
		if(Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
			Security.addProvider(PROVIDER);
		}
	}

	private static  KeyPair getKeyPair() throws Exception {
		String spath = RSAUtil.class.getClassLoader().getResource("").getPath();
		//String spath = this.getClass().getClassLoader().getResource("/").getPath();
		if (!spath.endsWith("/"))
			spath += "/";
		spath +=RSAKeyStore;		
		//解决文件路径中存在空格问题
		spath = URLDecoder.decode(spath, "UTF-8");
		KeyPair kp = null;
		try (FileInputStream fis = new FileInputStream(spath);
			 ObjectInputStream oos = new ObjectInputStream(fis)) {
			kp = (KeyPair) oos.readObject();
		}
		return kp;
	}

	
	/**
	 * <pre>
	 * 描述： 保存文件
	 * @param kp
	 * @throws Exception
	 * returnType：void
	 * </pre>
	*/
	private  void saveKeyPair(KeyPair kp) throws Exception {
		String spath = RSAUtil.class.getClassLoader().getResource("").getPath();
		if (!spath.endsWith("/"))
			spath += "/";
		spath +=RSAKeyStore;		
		//解决文件路径中存在空格问题
		spath = URLDecoder.decode(spath, "UTF-8");
		try (FileOutputStream fos = new FileOutputStream(spath);
			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			// 生成密钥
			oos.writeObject(kp);
		}
	}

	/**
	 * * 生成公钥 *
	 * 
	 * @param modulus *
	 * @param publicExponent *
	 * @return RSAPublicKey *
	 * @throws Exception
	 */
	private RSAPublicKey generateRSAPublicKey(byte[] modulus,
			byte[] publicExponent) throws Exception {
		KeyFactory keyFac = null;
		try {
			keyFac = KeyFactory.getInstance("RSA",PROVIDER);
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex.getMessage());
		}

		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(
				modulus), new BigInteger(publicExponent));
		try {
			return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
		} catch (InvalidKeySpecException ex) {
			throw new Exception(ex);
		}
	}

	/**
	 * * 生成私钥 *
	 * 
	 * @param modulus *
	 * @param privateExponent *
	 * @return RSAPrivateKey *
	 * @throws Exception
	 */
	private  RSAPrivateKey generateRSAPrivateKey(byte[] modulus,
			byte[] privateExponent) throws Exception {
		KeyFactory keyFac = null;
		try {
			keyFac = KeyFactory.getInstance("RSA",PROVIDER);
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex);
		}

		RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(
				modulus), new BigInteger(privateExponent));
		try {
			return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
		} catch (InvalidKeySpecException ex) {
			throw new Exception(ex);
		}
	}

	/**
	 * * 加密 *
	 * 
	 * @param data 待加密的明文数据 *
	 * @return 加密后的数据 *
	 * @throws Exception
	 */
	public static  byte[] encrypt(byte[] data) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("RSA",PROVIDER);
			cipher.init(Cipher.ENCRYPT_MODE,getKeyPair().getPublic());
			int blockSize = cipher.getBlockSize();// 获得加密块大小，如：加密前数据为128个byte，而key_size=1024
			// 加密块大小为127
			// byte,加密后为128个byte;因此共有2个加密块，第一个127
			// byte第二个为1个byte
			int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
			int leavedSize = data.length % blockSize;
			int blocksSize = leavedSize != 0 ? data.length / blockSize + 1
					: data.length / blockSize;
			byte[] raw = new byte[outputSize * blocksSize];
			int i = 0;
			while (data.length - i * blockSize > 0) {
				if (data.length - i * blockSize > blockSize)
					cipher.doFinal(data, i * blockSize, blockSize, raw, i
							* outputSize);
				else
					cipher.doFinal(data, i * blockSize, data.length - i
							* blockSize, raw, i * outputSize);
				// 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到
				// ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了
				// OutputSize所以只好用dofinal方法。

				i++;
			}
			return raw;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * <pre>
	 * 描述： 解密
	 * @param raw
	 * @return
	 * @throws Exception
	 * returnType：String
	 * </pre>
	*/
	public  String decrypt(String raw) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("RSA", PROVIDER);
			cipher.init(Cipher.DECRYPT_MODE, getKeyPair().getPrivate());
			int blockSize = cipher.getBlockSize();
			ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
			int j = 0;

			byte[] en_result = hexStringToBytes(raw);
			String pwd = null;
			if (en_result != null) {
				while (en_result.length - j * blockSize > 0) {
					bout.write(cipher.doFinal(en_result, j * blockSize, blockSize));
					j++;
				}

				StringBuffer sb = new StringBuffer();
				sb.append(new String(bout.toByteArray()));
				pwd = sb.reverse().toString();
				// 描述：“%”、“+”为特殊字符，%在URLDecoder.decode时报错，+解码为空，这里做转码处理
				pwd = pwd.replace("%", "%25");
				pwd = pwd.replace("+", "%2B");
				pwd = URLDecoder.decode(pwd, "UTF-8");
			}
			return pwd;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	/**
     * 16进制 To byte[]<br>
     * <font color='red'> fix byte[] en_pwd = new BigInteger(pwd, 16).toByteArray();bug</font>
     * 
     * @param hexString
     * @return byte[]
     */
    private  byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]) & 0xff);
        }
        return d;
    }


    /**
     * Convert char to byte
     * 
     * @param c char
     * @return byte
     */
    private  byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }	
  //edit end KYEEAPP-1137 2014年12月14日 20:18:26 houhy
    
    public static String byte2Base64StringFun(byte[] b){  
        return org.apache.commons.codec.binary.Base64.encodeBase64String(b);  
    } 
    
    public static String bytesToHexString(byte[] bytes){
        StringBuilder sb = new StringBuilder(bytes.length);
        String temp = null;
		for (byte aByte : bytes) {
			temp = Integer.toHexString(0xFF & aByte);
			if (temp.length() < 2) {
				sb.append(0);
			}
			sb.append(temp);
		}
        return sb.toString();
    }
    
}
