package com.univ.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.junit.Test;

/**
 * @author univ
 * @date 2019/12/10 8:36 PM
 * @description base64编码、解码。java8后引入了Base64类，可方便的使用base64进行编码与解码
 */
public class Base64Test {

    /**
     * 编码
     * @throws UnsupportedEncodingException
     */
    @Test
    public void encode() throws UnsupportedEncodingException {
        String str = "明朝那些事";
        byte[] messageBytes = str.getBytes("utf-8");
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedStr = encoder.encodeToString(messageBytes);
        System.out.println(encodedStr);
    }

    /**
     * 解码
     * @throws UnsupportedEncodingException
     */
    @Test
    public void decode() throws UnsupportedEncodingException {
        String encodedStr = "5piO5pyd6YKj5Lqb5LqL";

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodeBytes = decoder.decode(encodedStr);
        String str = new String(decodeBytes, "utf-8");
        System.out.println(str);
    }
}
