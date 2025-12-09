package com.univ.thirdutils.hutool.hmac;

import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.StandardCharsets;
import java.security.Security;

/**
 * 确实比原生使用jdk类要简单些
 */
public class SM4MacUtil {

    // 必须是16 字节
    private static final String sm4Key = "1234567890abcdef";

    private static final byte[] keyBytes = sm4Key.getBytes(StandardCharsets.UTF_8);

    // 注册 BC 提供者
    static {
        // 注册 Bouncy Castle 作为 JDK 加密 Provider
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    public static String generateSM4Mac(String data) {
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);

        // Hutool abstracts the engine creation. passing HmacAlgorithm.SM4CMAC
        // automatically uses the Bouncy Castle SM4 engine underneath.
        HMac sm4Mac = new HMac(HmacAlgorithm.SM4CMAC, keyBytes);

        // 用十六进制，也有返回byte[]的digest方法
        return sm4Mac.digestHex(dataBytes);
    }

    public static void main(String[] args) {
        String text = "SM4-MAC源数据";
        String sm4MacText = generateSM4Mac(text);
        System.out.println("Input: " + text);
        System.out.println("SM4-CMAC (Hex): " + sm4MacText);
    }

}
