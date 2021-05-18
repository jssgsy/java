package com.univ.javassit;

import org.junit.Test;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * @author univ
 * @date 2021/5/18 4:47 下午
 * @description
 */
public class JavassitTest {

    /**
     * 修改类的字节码：修改类的方法
     */
    @Test
    public void modifyMethod() throws NotFoundException, CannotCompileException {

        /**
         * 注，这里不能有这两行代码，否则下面调用toClass方法时会抛异常。
         * 因为此时编译会被类加载器所加载，而
         * 同一个Class对象不能被同一个类加载器多次加载
         */
        /*JavassistDemo javassistDemo = new JavassistDemo();
        javassistDemo.show();*/

        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("com.univ.javassit.JavassistDemo");
        // 获取某个已存在的方法
        CtMethod showMethod = ctClass.getDeclaredMethod("show");
        showMethod.setBody("System.out.println(\"show方法被javassit修改后的输出\");");

        // 将CtClass转换成Class对象，言外之意就是会交给类加载器进行加载
        ctClass.toClass();
        JavassistDemo javassistDemo = new JavassistDemo();
        javassistDemo.show();
    }
}


class JavassistDemo {

    public void show() {
        System.out.println("show方法的原生输出");
    }
}
