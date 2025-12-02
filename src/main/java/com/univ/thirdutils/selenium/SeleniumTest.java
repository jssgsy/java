package com.univ.thirdutils.selenium;

import cn.hutool.core.lang.Assert;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @Copyright: Unicom (Zhejiang) Industrial Internet Co., Ltd. 2025 <br/>
 * @Desc: <br/>
 * @ProjectName: java <br/>
 * @Date: 2025-11-26 17:18 <br/>
 * @Author: univ
 */
public class SeleniumTest {

    @Test
    public void fn() throws IOException, InterruptedException {
        // 重点：chromedriver必须和chrome浏览器的大版本一致
        // 此句必须在WebDriver实例化之前，当然也可以通过如下两者设置：
        //  1. 启动时使用-Dwebdriver.chrome.driver来指定；
        //  2. 放到PATH路径下则不用设置；
        System.setProperty("webdriver.chrome.driver", "/Users/univ/Downloads/chromedriver-mac-x64/chromedriver");
        SeleniumTest seleniumTest = new SeleniumTest();
        String targetUrl = "https://baidu.com";
        String saveDir = "data";

        seleniumTest.captureAndSave(targetUrl, saveDir);
    }

    /**
     * 核心方法：访问目标URL，截图并保存到指定目录
     * @param targetUrl 要截图的网址（如 https://www.baidu.com）
     * @param saveDir 保存目录（如 /tmp/screenshots 或 D:/screenshots）
     * @return 保存后的文件绝对路径（如 /tmp/screenshots/20251126153000_screenshot.png）
     * @throws IOException 保存失败（目录创建失败、权限不足等）
     */
    public String captureAndSave(String targetUrl, String saveDir) throws IOException, InterruptedException {

        WebDriver webDriver = chromeDriver();

        /**
         * 1. 访问目标URL，会等待页面加载完成，但
         *  内部使用的是document.readyState来决定页面是否加载完成；
         *  如果前端使用的的是vue、react等单页应用框架(SPA)，则这里不会等到整个渲染完成
         */
        webDriver.get(targetUrl);

        // 2. 等待页面加载完成（关键：避免截图空白，如前端使用vue、react等框架）
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 15);
        /*webDriverWait.until(driver -> {
            Long height = (Long) ((JavascriptExecutor) driver)
                    .executeScript("return document.body.scrollHeight");
            // 等待页面高度大于 1000px（根据实际页面调整，避免空白页高度）
            return height != null && height > 1000;
        });*/
        // 标准做法：用页面渲染后一定会出现的元素来判断
        /*WebElement myButton = webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("xxx"))
        );*/

        // 3. 强制等待 2-3 秒（确保渲染完成，最后兜底）
        Thread.sleep(3000);

        // 3. 生成唯一文件名（时间戳 + 固定前缀，避免覆盖）
        String fileName = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                + "_screenshot.png";

        // 4. 确保保存目录存在（不存在则创建）
        File dir = new File(saveDir);
        if (!dir.exists()) {
            boolean created = dir.mkdirs(); // 递归创建目录（如 /tmp/a/b 不存在则自动创建）
            if (!created) {
                throw new IOException("创建保存目录失败：" + saveDir);
            }
        }

        // 5. 截图并保存文件
        // 注，接口WebDriver并没有继承至TakesScreenshot，这里之所以可以转换是因为webDriver的实际类型(如ChromeDriver)实现了TakesScreenshot
        File screenshotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        File targetFile = new File(dir, fileName); // 最终保存的文件
        copy(screenshotFile, targetFile); // Spring 工具类，简化文件拷贝

        // 6. 返回文件绝对路径（后续可用于邮件附件）
        return targetFile.getAbsolutePath();

        // 此时文件还需要显示删除
    }

    // 实例化WebDriver前系统变量webdriver.chrome.driver一定要先设置
    public WebDriver chromeDriver() {
        ChromeOptions options = new ChromeOptions();

        // 1. 无头模式（核心：服务端无 GUI 运行）
        options.addArguments("--headless=new"); // Selenium 4.x 推荐，比旧模式稳定
        // 2. 禁用 GPU + 权限优化（Linux 必需）
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox"); // 解决 Linux 权限不足
        options.addArguments("--disable-dev-shm-usage"); // 避免共享内存不足
        // 3. 配置窗口大小（截图分辨率，按需调整）
        options.addArguments("--window-size=1920,1080");
        // 4. 忽略证书错误（避免部分网站截图失败）
        options.addArguments("--ignore-certificate-errors");

        // （可选）若 ChromeDriver 不在系统路径，手动指定路径
        // System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // 创建 WebDriver 并设置超时（避免卡死）
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(309, TimeUnit.SECONDS); // 页面加载30s超时
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS); // 元素查找10s超时

        return driver;
    }

    public static int copy(File in, File out) throws IOException {
        Assert.notNull(in, "No input File specified");
        Assert.notNull(out, "No output File specified");
        return copy(Files.newInputStream(in.toPath()), Files.newOutputStream(out.toPath()));
    }
    public static final int BUFFER_SIZE = 4096;

    public static int copy(InputStream in, OutputStream out) throws IOException {
        Assert.notNull(in, "No InputStream specified");
        Assert.notNull(out, "No OutputStream specified");

        int byteCount = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
            byteCount += bytesRead;
        }
        out.flush();
        return byteCount;
    }
}
