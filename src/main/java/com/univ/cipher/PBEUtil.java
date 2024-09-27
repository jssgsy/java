package com.univ.cipher;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * 这里固定了密码与salt，实际使用中要动态替换
 * 一般是一条数据一个salt；salt实际上要随机生成
 * 使用的是PBEWithMD5AndDES(不是特别安全)，可使用PBEWithHmacSHA256AndAES_128
 * 注：使用不同的加密算法，则这里
 *
 * @author univ
 * date 2024/9/27
 */
public class PBEUtil {

    private static final String SALT = "12345678";

    private static final String PASSWORD = "abcd";
    private static final String ALGORITHM = "PBEWithMD5AndDES";

    private static final int ITERATION_COUNT = 100;

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        // 待加密消息
        String plainText = "hello pbe, 基于口令的密码";
        String encryptedBase64 = encrypt(plainText);
        System.out.println("加密后的Base64：" + encryptedBase64);

        String decrypt = decrypt(encryptedBase64);
        System.out.println("解密后： " + decrypt);
    }

    public static SecretKey getPBEKey() throws InvalidKeySpecException, NoSuchAlgorithmException {
        PBEKeySpec keySpec = new PBEKeySpec(PASSWORD.toCharArray(), SALT.getBytes(StandardCharsets.UTF_8), ITERATION_COUNT);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return keyFactory.generateSecret(keySpec);
    }

    public static String encrypt(String plainText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        // 获取密钥
        SecretKey secretKey = getPBEKey();
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(SALT.getBytes(), ITERATION_COUNT);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 这里千万不要少了第三个参数，因为PBE需要盐和口令，是特定算法
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParameterSpec);
        byte[] bytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String decrypt(String encryptedBase64) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        byte[] bytes = Base64.getDecoder().decode(encryptedBase64);
        SecretKey secretKey = getPBEKey();
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(SALT.getBytes(), ITERATION_COUNT);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 这里千万不要少了第三个参数，因为PBE需要盐和口令，是特定算法
        cipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParameterSpec);
        byte[] bytes1 = cipher.doFinal(bytes);
        return new String(bytes1);
    }
}
