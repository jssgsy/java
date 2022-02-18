package com.univ.thirdutils.jackson.advance;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author univ
 * 2022/2/18 10:38 上午
 */
public class PolyJsonTest {

    /**
     * 注：这里的BeanInterface是一个接口，最终肯定要找到一个具体的子类来承载，如何找子类的逻辑肯定要在BeanInterface上
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStrOne = "{\"identify\":\"one\",\"age\":20,\"list\":[\"aaa\",\"bbb\",\"ccc\"]}";
        String jsonStrTwo = "{\"identify\":\"two\",\"name\":\"this is name of two\",\"done\":true}";

        BeanInterface beanInterfaceOne = objectMapper.readValue(jsonStrOne, BeanInterface.class);
        System.out.println(beanInterfaceOne);

        BeanInterface beanInterfaceTwo = objectMapper.readValue(jsonStrTwo, BeanInterface.class);
        System.out.println(beanInterfaceTwo);

        /*String jsonStrOne = "{\"identify\":\"com.univ.thirdutils.jackson.advance.BeanInterfaceImplOne\",\"age\":20,\"list\":[\"aaa\",\"bbb\",\"ccc\"]}";
        String jsonStrTwo = "{\"identify\":\"com.univ.thirdutils.jackson.advance.BeanInterfaceImplTwo\",\"name\":\"this is name of two\",\"done\":true}";*/
    }

}
