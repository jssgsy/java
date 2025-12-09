package com.univ.thirdutils.hutool.encrypt;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.SM4;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * 需引入bcprov-jdk18on
 * @Copyright: Unicom (Zhejiang) Industrial Internet Co., Ltd. 2025 <br/>
 * @Desc: <br/>
 * @ProjectName: java <br/>
 * @Date: 2025-12-04 13:59 <br/>
 * @Author: univ
 */
public class SM4Test {

    // 须为16位
    private static final String SM4_KEY = "f2sKrg8VtUcpHYxr";

    private static final String SM4_IV = "S7eTVs8NjVLJ0F4h";

    // 使用CBC模式：需要向量，生产用
    @Test
    public void cbcEncrypt() {
        // 重点：使用CBC模式构造SM4
        SM4 sm4 = new SM4(Mode.CBC , Padding.PKCS5Padding , SM4_KEY.getBytes() , SM4_IV.getBytes());

        // 加密：明文 -> 密文（base64编码）
        String plainText = "今天是周三";
        // 还有sm4.encryptHex()等方法
        String cipherText = sm4.encryptBase64(plainText);
        System.out.println("ECB 加密结果（base64编码）：" + cipherText);

        // 解密：密文（base64编码）-> 明文
        String decryptedText = sm4.decryptStr(cipherText);
        System.out.println("ECB 解密结果：" + decryptedText);
    }

    // ecb模式：不需要向量，不建议生产环境用
    @Test
    public void ecbEncrypt() throws UnsupportedEncodingException {

        // 重点：使用ECB模式构造SM4
        SM4 sm4 = new SM4(SM4_KEY.getBytes(StandardCharsets.UTF_8));

        // 加密：明文 -> 密文（base64编码 编码）
        String plainText = "今天是周三";
        // 还有sm4.encryptHex()等方法
        String cipherText = sm4.encryptBase64(plainText);
        System.out.println("ECB 加密结果（base64编码）：" + cipherText);

        // 解密：密文（base64编码）-> 明文
        String decryptedText = sm4.decryptStr(cipherText);
        System.out.println("ECB 解密结果：" + decryptedText);
    }

}
