package com.univ.thirdutils.bcprov;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.Security;
import java.util.Base64;

/**
 * SM4是国产的对称加密算法
 * 对称加密算法通常有ECB和CBC两种模式
 *
 * 很简单，其实就是使用jdk加解密而已
 */
public class SM4Util {

    // 密钥和IV必须为16字节
    private static final String key = "1234567890123456";
    private static final String iv = "abcdefghijklmnop";

    private static final byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
    private static final byte[] ivBytes = iv.getBytes(StandardCharsets.UTF_8);

    // 算法名称
    private static final String ALGORITHM_NAME = "SM4";

    // 算法/模式/填充方式（ECB模式）
    private static final String ALGORITHM_ECB_PADDING = "SM4/ECB/PKCS7Padding";

    // 算法/模式/填充方式（CBC模式）
    private static final String ALGORITHM_CBC_PADDING = "SM4/CBC/PKCS7Padding";

    static {
        // 注册BouncyCastle安全提供者
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * SM4 ECB模式加密，不需要IV
     * @param plainText 明文（字符串）
     * @return 加密后Base64编码的字符串
     * @throws Exception 加密异常
     */
    public static String encryptECB(String plainText) throws Exception {
        // 构建密钥对象
        Key sm4Key = new SecretKeySpec(keyBytes, ALGORITHM_NAME);
        // 初始化加密器
        Cipher cipher = Cipher.getInstance(ALGORITHM_ECB_PADDING, BouncyCastleProvider.PROVIDER_NAME);
        // 重点：加密模式
        cipher.init(Cipher.ENCRYPT_MODE, sm4Key);

        // 加密并转Base64
        byte[] cipherBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(cipherBytes);
    }

    /**
     * SM4 ECB模式解密，不需要IV
     * @param cipherText 加密后的Base64字符串
     * @return 解密后的明文
     * @throws Exception 解密异常
     */
    public static String decryptECB(String cipherText) throws Exception {
        // 构建密钥对象
        Key sm4Key = new SecretKeySpec(keyBytes, ALGORITHM_NAME);
        // 初始化解密器
        Cipher cipher = Cipher.getInstance(ALGORITHM_ECB_PADDING, BouncyCastleProvider.PROVIDER_NAME);
        // 重点：解密模式
        cipher.init(Cipher.DECRYPT_MODE, sm4Key);

        // 解密
        byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
        byte[] plainBytes = cipher.doFinal(cipherBytes);
        return new String(plainBytes, StandardCharsets.UTF_8);
    }

    /**
     * SM4 CBC模式加密（推荐生产环境使用），需要IV
     * @param plainText 明文
     * @return 加密后Base64编码的字符串
     * @throws Exception 加密异常
     */
    public static String encryptCBC(String plainText) throws Exception {
        // 构建密钥和IV对象
        Key sm4Key = new SecretKeySpec(keyBytes, ALGORITHM_NAME);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        // 初始化加密器
        Cipher cipher = Cipher.getInstance(ALGORITHM_CBC_PADDING, BouncyCastleProvider.PROVIDER_NAME);
        // 指定为加密
        cipher.init(Cipher.ENCRYPT_MODE, sm4Key, ivSpec);

        // 加密并转Base64
        byte[] cipherBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(cipherBytes);
    }

    /**
     * SM4 CBC模式解密
     * @param cipherText 加密后的Base64字符串
     * @return 解密后的明文
     * @throws Exception 解密异常
     */
    public static String decryptCBC(String cipherText) throws Exception {
        // 构建密钥和IV对象
        Key sm4Key = new SecretKeySpec(keyBytes, ALGORITHM_NAME);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        // 初始化解密器
        Cipher cipher = Cipher.getInstance(ALGORITHM_CBC_PADDING, BouncyCastleProvider.PROVIDER_NAME);
        // 指定为解密
        cipher.init(Cipher.DECRYPT_MODE, sm4Key, ivSpec);

        // 解密
        byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
        byte[] plainBytes = cipher.doFinal(cipherBytes);
        return new String(plainBytes, StandardCharsets.UTF_8);
    }

    // 测试方法
    public static void main(String[] args) throws Exception {
        String plainText = "SM4加密源数据";

        // === ECB模式测试，ECB模式不需要IV ===
        String ecbCipher = SM4Util.encryptECB(plainText);
        String ecbPlain = SM4Util.decryptECB(ecbCipher);
        System.out.println("=== ECB模式 ===");
        System.out.println("明文：" + plainText);
        System.out.println("加密后：" + ecbCipher);
        System.out.println("解密后：" + ecbPlain);
        System.out.println("ECB解密结果是否一致：" + plainText.equals(ecbPlain));

        // === CBC模式测试，ECB模式需要IV ===
        String cbcCipher = SM4Util.encryptCBC(plainText);
        String cbcPlain = SM4Util.decryptCBC(cbcCipher);
        System.out.println("\n=== CBC模式 ===");
        System.out.println("明文：" + plainText);
        System.out.println("加密后：" + cbcCipher);
        System.out.println("解密后：" + cbcPlain);
        System.out.println("CBC解密结果是否一致：" + plainText.equals(cbcPlain));
    }

}
