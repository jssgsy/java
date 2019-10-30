package com.univ.regx;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author univ
 * @datetime 2019/1/2 2:11 PM
 * @description 正则表达式
 */
public class RegularTest {

    /**
     * 转义字符是：反斜杠 \
     */
    @Test
    public void test() {
        String str = "\"{\"name\":\"univ\",\"age\":19}\"";
        
        System.out.println(str);
    }

    @Test
    public void testt() {
        String content = "【有赞】${时间}${城市}第${几}期“开店辅导班”报名中，帮助新商家快速开张卖货，现场更有专家当面解答经营难题，期待您的参与，点击报名>>";
        List<String> places = Arrays.asList("aaa", "bbb", "ccc");
        System.out.println(xxxxxx(content, places));

        /*String a = "a8888b7c";
        String[] split = a.split("\\d+");
        System.out.println(Arrays.toString(split));*/
    }

    @Test
    public void test20() {

        Pattern pattern = Pattern.compile("\\d{3}");
        Matcher matcher = pattern.matcher("1趄4");
        if (matcher.find()) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

    }

    public String xxxxxx(String content, List<String> placeholders) {
        if (StringUtils.isEmpty(content)) {
            return "";
        }
        String p = "\\$\\{.*?\\}";
        // list转数组
        String[] ps = placeholders.stream().toArray(String[]::new);

        String[] split = content.split(p);

        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(content);
        // 占位符出现的次数
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        // 占位符出现的次数必须和要替换的数量一致
        if (count != ps.length) {
            System.out.println("count != ps.length");
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            stringBuilder.append(split[i]);
            // 针对边界条件处理
            if (i < ps.length) {
                stringBuilder.append(ps[i]);
            }
        }

        return stringBuilder.toString();
    }

    /**
     * 字符串本身相关的正则表达式的方法
     */
    @Test
    public void test1() {
        String str = "hello good world this is a good world";
        
        // 是否匹配,注意，只有在全匹配时才返回为true
        boolean matched = str.matches("world");
        System.out.println(matched);    // false，因为没有部分匹配，只有当这里的regex能完全匹配str时才返回true,一般不用

        // 根据正则表达式转换成String[]
        String[] goods = str.split("good");
        System.out.println(Arrays.toString(goods)); // [hello ,  world this is a ,  world]

        // 所有的or都会被替换
        String replace = str.replace("or", "Xxx");
        System.out.println(str);    // hello, world, this is a good world
        System.out.println(replace);    // hello, wXxxld, this is a good wXxxld

        // 类似的有
        // str.replaceFirst();
    }

    @Test
    public void basic() {
        // 正则表达式字符串
        String regx = "world";

        String template = "hello good world this is a good world";
        
        // 用Patter对象表示原正则表达式对象
        Pattern pattern = Pattern.compile(regx);

        // matcher的参数是原字符串(要匹配的源字符串)
        Matcher matcher = pattern.matcher(template);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }

        

        
    }
    
    @Test
    public void test2() {


        String str = "我是${0},我来自${1},今年${2}岁";
        String[] arr={"中国人","北京","22"};
        Matcher m= Pattern.compile("\\$\\{(\\d)\\}").matcher(str);
        while(m.find()){
            str=str.replace(m.group(),arr[Integer.parseInt(m.group(1))]);
        }


        System.out.println(str);

    }

    private String buildFinalContent(String content, List<String> placeholders) {
        
        String[] ps = placeholders.stream().toArray(String[]::new);

        Pattern pattern = Pattern.compile("\\$\\{\\}");
        Matcher matcher = pattern.matcher(content);
        int count = 0;
        while (matcher.find()) {
            count++;
        }

        String[] split = content.split("\\$\\{\\}");
        if (count != ps.length) {
            System.out.println("0 0");
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            stringBuilder.append(split[i]);
            if (i < ps.length) {
                stringBuilder.append(ps[i]);
            }

        }

        return stringBuilder.toString();
    }
}
