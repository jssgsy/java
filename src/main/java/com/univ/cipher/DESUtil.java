package com.univ.cipher;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author univ
 * date 2024/9/26
 */
public class DESUtil {
    
    // 指定密钥
    static String DES_KEY = "12345678";

    public static void main(String[] args) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        useFixedKeyToEncrypt();

//        useGeneratedKeyToEncrypt();
    }

    /**
     * 指定固定密钥
     */
    public static void useFixedKeyToEncrypt() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 待加密的明文
        String plainText = "hello des。一种对称加密算法";
        String encryptedBase64 = fixedKeyEncrypt(plainText);
        System.out.println("加密后密文的Base64: " + encryptedBase64);

        String bytes = fixedKeyDecrypt(encryptedBase64);
        System.out.println("解密后的明文: " + bytes);
    }

    /**
     * 使用随机生成的密钥
     * 实际使得要要注意保存密钥，一个加密的数据一个密钥
     */
    public static void useGeneratedKeyToEncrypt() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 待加密的明文
        String plainText = "hello des。一种对称加密算法";
        // 随机生成一个密钥
        SecretKey secretKey = generateSecretKey();
        String encryptedBase64 = encrypt(plainText, secretKey);
        System.out.println("加密后密文的Base64: " + encryptedBase64);
        String bytes = decrypt(encryptedBase64, secretKey);
        System.out.println("解密后的明文: " + bytes);
    }

    public static String fixedKeyEncrypt(String plainText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 因为是指定密钥，所以这里可直接使用SecretKeySpec，最简单
        SecretKeySpec secretKeySpec = new SecretKeySpec(DES_KEY.getBytes(StandardCharsets.UTF_8), "DES");

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] bytes = cipher.doFinal(plainText.getBytes());
        // 加密场景中一般将字节转成可视的base64
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String fixedKeyDecrypt(String encryptedBase64) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(DES_KEY.getBytes(StandardCharsets.UTF_8), "DES"));
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(encryptedBase64));
        return new String(bytes);
    }


    public static SecretKey generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        // 注意DES的密钥长度本质是56位
        keyGenerator.init(56);
        return keyGenerator.generateKey();
    }
    public static String encrypt(String plainText, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] bytes = cipher.doFinal(plainText.getBytes());
        // 加密场景中一般将字节转成可视的base64
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String decrypt(String encryptedBase64, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(encryptedBase64));
        return new String(bytes);
    }
    
}
