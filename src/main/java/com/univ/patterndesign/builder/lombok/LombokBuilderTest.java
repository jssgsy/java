package com.univ.patterndesign.builder.lombok;

/**
 * @author univ
 * 2021/10/20 8:14 下午
 */
public class LombokBuilderTest {

    public static void main(String[] args) {
        LombokProduct lombokProduct = LombokProduct.builder()
                .name1("name1")
                .name2("name2")
                .name3("name3")
                .build();
        System.out.println(lombokProduct);
    }
}
