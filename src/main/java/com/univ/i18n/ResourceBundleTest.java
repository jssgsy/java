package com.univ.i18n;

import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author univ
 * date 2023/8/28
 */
public class ResourceBundleTest {

    @Test
    public void f1() {
        String bundleFile = "i18n/default";
        ResourceBundle bundle = ResourceBundle.getBundle(bundleFile);
        bundle.keySet().forEach(key -> {
            System.out.println(key + " : " + bundle.getString(key));
        });
    }

    @Test
    public void f2() {
        String bundleFile = "i18n/usa";
        // 获取指定Locale对应的资源包文件
        ResourceBundle bundle = ResourceBundle.getBundle(bundleFile, Locale.US);
        bundle.keySet().forEach(key -> {
            System.out.println(key + " : " + bundle.getString(key));
        });
    }
}
