package com.univ.string;

/**
 * Univ
 * 16/9/11 10:25
 */

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用以记录java.lang.String类的常用方法
 */
public class StringApiTest {

    /**
     * 字符串替换
     * 如果直接使用String进行替换,则一次只能替换一处地方
     */
    @Test
    public void test1() {
        String str = "hello,${userName},welcom to ${city}";
        String result = str.replace("${userName}", "univ");
        System.out.println(result);
    }

    /**
     * 演示如何使用java正则表达式最基本的用法
     * 核心类及其方法:
     * 1. Pattern:表示经编译的正则表达式(及字符串形式正则表达式的表示)
     *      Pattern pattern = Pattern.compile(regx);
     *      Matcher matcher = pattern.matcher(template);
     * 2. Matcher:封装了匹配结果的类
     *      matcher.find();
     *      matcher.group();
     *      matcher.start();
     *      matcher.end();
     *      matcher.replaceAll("...");
     */
    @Test
    public void test3() {
        String template = "Hello,username, welcome username.";//待匹配的字符串
        String regex = "username";//正则表达式

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(template);
        if (matcher.find()) {//进行匹配
            System.out.println("被匹配到的内容是: " + matcher.group());//group方法返回被匹配的内容
            System.out.println("此次匹配的开始位置为: " + matcher.start());//被匹配的第一个字符的索引,从0开始
            System.out.println("此次匹配的开始位置为: " + matcher.end());//被匹配的最后一个字符的索引+1
            String result = matcher.replaceAll("univ");//匹配到的两处均将被替换
            System.out.println("替换后的字符串为: " + result);
        }
    }

    /**
     * java世界中的正则表达式特殊字符的处理
     * 对java的解释器来说，在反斜线字符(/)前的字符有特殊的含义。
     * 在java中，与regex有关的包，并不都能理解和识别反斜线字符(/)。
     * 为避免这一点，即为了让反斜线字符(/)在模式对象中被完全地传递，应该用双反斜线字符(/)。
     * 此外圆括号或者方括号或者大括号等在正则表达中有特殊含义，如果想让它解释为字面上意思，也需要在它前面用双反斜线字符(/)。
     */
    @Test
    public void test4() {
        String template = "Hello, ${username}, welcome .";
        String regex = "\\$\\{username\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(template);
        if (matcher.find()) {//如果匹配到
            System.out.println(matcher.group());
        }
    }

    /**
     * java正则表达式中常用的方法
     */
    @Test
    public void test5() {
        String template = "Hello, ${username}, welcome to ${city}.";
        String regex = "\\$\\{username\\}|\\$\\{city\\}";//即regex:${username}|${city}
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(template);
        while (matcher.find()) {//注意这里是where循环不是if判断
            System.out.println("被匹配到的内容是: " + matcher.group());
            System.out.println("此次匹配的开始位置为: " + matcher.start());
            System.out.println("此次匹配的开始位置为: " + matcher.end());
        }
    }

    /**
     * 利用正则表达式进行多个子串的替换
     *  1.需要对正则表达式有一定基础知识;
     *  2.将待识别文本中的占位符与想替换成的值封装到map中;
     * 可以将子串的替换封装成一个方法,见getFinalContent(...)
     */
    @Test
    public void test6() {
        String template = "Hello, ${username}, welcome to ${city}.";
        Map<String, String> map = new HashMap<>();//被替换关键字的的数据源
        map.put("username", "univ");
        map.put("city", "hangzhou");

        StringBuilder sbRegex = new StringBuilder();
        sbRegex.append("\\$\\{(");
        for (Map.Entry m : map.entrySet()) {//这里一个优化可以是,使用StringUtils将map的所有key的值以|分隔,这样可以省去调用sb1.deleteCharAt()方法
            sbRegex.append(m.getKey()).append("|");
        }
        sbRegex.deleteCharAt(sbRegex.length() - 1);//去掉最后一个多余的字符|
        sbRegex.append(")\\}");
        String regex = sbRegex.toString();//即regex:${(username|city)},注意这里小括号的位置
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(template);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {//注意这里是where循环不是if判断
            matcher.appendReplacement(sb, map.get(matcher.group(1)));
        }
        matcher.appendTail(sb);//不可少
        System.out.println(sb);
    }

    /**
     * @param map 封装了需要替换的key及其想替换成的值.如果template中没有占位符,请传入空map或者null
     * @param template 待识别的文本(如消息模板)
     * @return 返回消息的最终内容
     */
    private String getFinalContent(Map<String, String> map, String template) {
        //生成正则表达式
        StringBuilder sb1 = new StringBuilder();
        sb1.append("\\$\\{(");
        if (map != null) {
            for (Map.Entry m : map.entrySet()) {
                sb1.append(m.getKey()).append("|");
            }
            sb1.deleteCharAt(sb1.length() - 1);//去掉最后一个多余的字符|
        }
        sb1.append(")\\}");
        String regex = sb1.toString();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(template);

        StringBuffer sb2 = new StringBuffer();
        while (matcher.find()) {//注意这里是where循环不是if判断
            matcher.appendReplacement(sb2, map.get(matcher.group(1)));
        }
        matcher.appendTail(sb2);
        return sb2.toString();
    }


}
