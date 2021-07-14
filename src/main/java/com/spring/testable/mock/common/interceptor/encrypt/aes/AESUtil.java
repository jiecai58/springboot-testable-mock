package com.spring.testable.mock.common.interceptor.encrypt.aes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @version V1.0
 * @desc AES 加密工具类
 * @Auther: caijie
 * @Date: 2018/10/19 15:49
 * @Description:
 */
public class AESUtil {
    static Logger logger = LoggerFactory.getLogger(AESUtil.class);
    // 密钥
    public static  final String KEY = "AD42F6697B035B7580E4FEF93BE20BAD";
    private static final String CHARSET = "utf-8";
    // 偏移量
    private static final int OFFSET = 16;
    // 加密器类型:加密算法为AES,加密模式为CBC,补码方式为PKCS5Padding,"算法/模式/补码方式"
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    // 算法类型：用于指定生成AES的密钥
    private static final String KEY_ALGORITHM = "AES";

    /**
     * 加密
     *
     * @param content
     * @return
     */
    public static String encrypt(String content) {
        return encrypt(content, KEY);
    }

    /**
     * 解密
     *
     * @param content
     * @return
     */
    public static String decrypt(String content) {
        return decrypt(content, KEY);
    }

    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @param password    解密密钥
     * @return
     */
    public static String encrypt(String content, String password) {
        try {
            //创建AES加密器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            byte[] byteContent = content.getBytes(CHARSET);
            //使用加密器的加密模式
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password), zeroIv(password));
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            //使用BASE64对加密后的二进制数组进行编码
            return Base64Utils.encodeToString(result);
        } catch (Exception e) {
            logger.info("", e);
        }
        return null;
    }

    /**
     * AES（256）解密
     *
     * @param content 待解密内容
     * @param password    解密密钥
     * @return 解密之后
     * @throws Exception
     */
    public static String decrypt(String content, String password) {
        try {

            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //解密时使用加密器的解密模式
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password), zeroIv(password));
            byte[] result = cipher.doFinal(Base64Utils.decodeFromString(content));
            // 解密
            return new String(result, CHARSET);
        } catch (Exception e) {
            logger.info("", e);
        }
        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(String password) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            //AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(password.getBytes()));
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            logger.info("", ex);
        }
        return null;
    }


    private static IvParameterSpec zeroIv (String password){
        //创建初始向量iv用于指定密钥偏移量(可自行指定但必须为128位)，因为AES是分组加密，下一组的iv就用上一组加密的密文来充当
        return new IvParameterSpec(password.getBytes(), 0, OFFSET);
    }

    public static void main(String[] args) {
        String s = "hello World!123.加解密";
        String encryptResultStr = encrypt(s);
        // 加密
        System.out.println("加密前：" + s);
        System.out.println("加密后：" + encryptResultStr);
        // 解密
        System.out.println("解密后：" + decrypt(encryptResultStr));
    }
}
