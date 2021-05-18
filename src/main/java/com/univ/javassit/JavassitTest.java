package com.univ.javassit;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
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

    /**
     * 从无到有创建一个类出来
     */
    @Test
    public void buildFromStr() throws CannotCompileException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, NotFoundException,
            NoSuchFieldException {
        // 包名包含在类名中，这样就不需要包名了
        String qualifiedClassName = "com.univ.javassit.JavassistDemoV2";

        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass(qualifiedClassName);
        ctClass.setModifiers(Modifier.PUBLIC);

        // 新增一个字段(字符串形式)
        /*String field = "private String name = \"javassist_name\";";
        CtField ctField = CtField.make(field, ctClass);
        ctClass.addField(ctField);*/
        // 等价于(非字符串形式)：注，此时没法给字段赋值初始值，但可以通过构造函数来实现此目的
        CtClass strCtClass = classPool.get("java.lang.String");
        CtField ctField1 = new CtField(strCtClass, "name", ctClass);
        ctField1.setModifiers(Modifier.PRIVATE);
        ctClass.addField(ctField1);

        // 新增一个方法(字符串形式)
        String method = "public String getName() { return this.name;}";
        CtMethod ctMethod = CtMethod.make(method, ctClass);
        ctClass.addMethod(ctMethod);
        // 等价于(非字符串形式)
        /*CtClass strCtClass2 = classPool.get("java.lang.String");
        CtMethod ctMethod1 = new CtMethod(strCtClass2, "getName", new CtClass[]{}, ctClass);
        ctMethod1.setModifiers(Modifier.PUBLIC);
        ctMethod1.setBody("return this.name;");
        ctClass.addMethod(ctMethod1);*/

        // 转成Class对象：让类加载器加载
        Class<?> clz = ctClass.toClass();

        // 用反射调用来验证下
        Object instance = clz.newInstance();
        Field field = clz.getDeclaredField("name");
        field.setAccessible(true);
        field.set(instance, "javassit_new_name");
        Method getName = clz.getDeclaredMethod("getName");
        String result = (String) getName.invoke(instance);
        System.out.println(result); // javassist_name
    }
}


class JavassistDemo {

    public void show() {
        System.out.println("show方法的原生输出");
    }
}
