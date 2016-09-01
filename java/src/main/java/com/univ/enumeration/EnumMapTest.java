package com.univ.enumeration;

/**
 * Univ
 * 16/9/1 16:31
 */

import org.junit.Test;

import java.util.EnumMap;
import java.util.Map;

/**
 * 演示EnumMap的使用方法
 *
 * 1.与EnumSet一样,EnumMap也是一个集合,继承自AbstractMap类,只是其key只能是枚举类型(同一种),如此而已;
 * 2.不允许插入null值;
 * 3.EnumMap的顺序同其key在枚举中定义的顺序;
 *      (Enum maps are maintained in the natural order of their keys (the order in which the enum constants are declared))
 * 4.内部以数组形式存在,使得性能很好。
 *      (Enum maps are represented internally as arrays. This representation is extremely compact and efficient.)
 * 5.EnumMap是非线程安全的,要想得到线程安全的EnumMap,使用Collections.synchronizedSet包装一下:
 *      Map<EnumKey, V> m = Collections.synchronizedMap(new EnumMap<EnumKey, V>(...));
 */
public class EnumMapTest {

    @Test
    public void test1(){

        Map<FontConstant,String> m1 = new EnumMap(FontConstant.class);
        m1.put(FontConstant.Bold, "bold...");
        m1.put(FontConstant.Plain, "plain...");

        String s = m1.get(FontConstant.Bold);//获取指定key对应的value,这里为"bold..."

        showEnumMap(m1);
    }

    private void showEnumMap(Map<FontConstant, String> m1) {
        /**
         * 这里输出的顺序是m1的key在FontConstant中定义的顺序
         */
        for (Map.Entry<FontConstant, String> m: m1.entrySet()){
            FontConstant key = m.getKey();
            String value = m.getValue();
            System.out.println(key + "  " + value);
        }
    }
}

enum FontConstant {
    Plain, Bold, Italic;
}
