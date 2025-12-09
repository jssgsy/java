package com.univ.thirdutils.bcprov;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Objects;

/**
 * 所谓SM4Mac即基于SM4的消息摘要，因此需要有密钥，即HMAC；
 *
 * 思路和使用SM4对称加密算法是一样的，只是求MAC(消息摘要)用的是Mac对象而不是Cipher对象
 *
 * 似乎好像还推荐直接使用bcprov包中的专有类；但自己更倾向于只使用jdk中的类，更通用
 */
public class SM4MacUtil {

    // 算法名称（BC 标准命名），不要弄错了，好像还有一些其它的名称
    // CMAC 即：Cipher-based Message Authentication Code
    private static final String SM4_MAC_ALGORITHM = "SM4-CMAC";

    // 必须是16 字节
    private static final String sm4Key = "1234567890abcdef";

    private static final byte[] keyBytes = sm4Key.getBytes(StandardCharsets.UTF_8);

    static {
        // 注册BouncyCastle安全提供者
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * 生成SM4-MAC摘要
     * @param data 待计算摘要的原始数据（字符串）
     * @return 十六进制编码的SM4-MAC摘要（大写）
     */
    public static String generateSM4Mac(String data)
            throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException {

        // 构建SM4-MAC密钥对象
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "SM4");

        // 获取SM4-MAC实例
        // 重点：加密算法用的是Cipher，摘要用的是Mac
        Mac sm4Mac = Mac.getInstance(SM4_MAC_ALGORITHM);

        // 初始化MAC对象
        sm4Mac.init(secretKeySpec);

        // 计算MAC摘要（字节数组）
        byte[] macBytes = sm4Mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

        // 转为十六进制字符串（便于存储/传输）
        return Hex.toHexString(macBytes).toUpperCase();
    }

    /**
     * 验证SM4-MAC摘要（校验数据是否被篡改）
     * @param data      原始数据
     * @param expectMac 预期的SM4-MAC摘要（十六进制字符串）
     * @return true=摘要一致（数据未篡改），false=摘要不一致（数据被篡改）
     */
    public static boolean verifySM4Mac(String data, String expectMac) {
        try {
            // 生成当前数据的MAC摘要
            String actualMac = generateSM4Mac(data);
            // 对比摘要（建议使用常量时间比较，防止时序攻击，这里省略）
            return Objects.equals(actualMac, expectMac);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 测试方法
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException {
        String originalData = "SM4-MAC源数据";
        String tamperedData = "SM4-MAC源数据-被篡改";

        // 1. 生成原始数据的SM4-MAC摘要
        String sm4Mac = generateSM4Mac(originalData);
        System.out.println("=== SM4-MAC 生成 ===");
        System.out.println("原始数据：" + originalData);
        System.out.println("SM4-MAC摘要（16进制）：" + sm4Mac);

        // 2. 验证原始数据的摘要（未篡改）
        boolean verifySuccess = verifySM4Mac(originalData, sm4Mac);
        System.out.println("\n=== SM4-MAC 验证（未篡改）===");
        System.out.println("验证结果：" + (verifySuccess ? "通过（数据完整）" : "失败（数据异常）"));

        // 3. 验证篡改后数据的摘要（篡改后）
        boolean verifyFail = verifySM4Mac(tamperedData, sm4Mac);
        System.out.println("\n=== SM4-MAC 验证（已篡改）===");
        System.out.println("篡改后数据：" + tamperedData);
        System.out.println("验证结果：" + (verifyFail ? "通过（异常）" : "失败（数据被篡改）"));
    }

}
