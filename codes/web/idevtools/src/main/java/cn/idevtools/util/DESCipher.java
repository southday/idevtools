package cn.idevtools.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;
/**
 * DES加密
 * @author 王沁宽
 * @date 2019/3/10
 */
public class DESCipher {
    private volatile static DESCipher instance = null;
    private static final String ALGORITHM = "DES";
    private static final String CHARSET = "UTF-8";
    private static final String key = "12345678";
    private Cipher cipher = null;

    private DESCipher(){

    }

    /**
     * 初始化加密工具
     * @param model 加密或解密
     * @param key
     * @throws Exception
     */
    private void initCipher(int model, String key) throws Exception {
        // 创建可信任的随机数源
        SecureRandom secureRandom = new SecureRandom();
        // 根据原始密钥创建DESKeySpec对象
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(CHARSET));
        // 创建密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        // cipher完成解密操作
        this.cipher = Cipher.getInstance(ALGORITHM);
        this.cipher.init(model, secretKey, secureRandom);
    }

    public String encrypt(String plaintext) throws Exception{
        return  encrypt(plaintext,key);
    }

    public String decrypt(String plaintext) throws Exception{
        return  decrypt(plaintext,key);
    }

    public String encrypt(String plaintext, String key) throws Exception {
        this.initCipher(Cipher.ENCRYPT_MODE,key);
        return new String(Base64.encodeBase64(cipher.doFinal(plaintext.getBytes(CHARSET))));
    }

    public String decrypt(String ciphertext, String key) throws Exception {
        this.initCipher(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.decodeBase64(ciphertext.getBytes())), CHARSET);
    }

    public static DESCipher getInstance(){
        if(instance == null){
            synchronized (DESCipher.class){
                if(instance == null) instance = new DESCipher();
            }
        }
        return instance;
    }
}
