package com.univ.jdkutils.introspector;

import lombok.Data;

/**
 * @author univ
 * 2021/7/22 4:58 下午
 */
@Data
public class Person extends Base1 {
    private String name;
}

@Data
class Base {
    private String baseName;
}

@Data
class Base1 extends Base {
    private String base1Name;
}
