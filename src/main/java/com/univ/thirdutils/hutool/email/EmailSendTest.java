package com.univ.thirdutils.hutool.email;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import org.junit.Test;

/**
 * @Copyright: Unicom (Zhejiang) Industrial Internet Co., Ltd. 2025 <br/>
 * @Desc: <br/>
 * @ProjectName: java <br/>
 * @Date: 2025-11-26 14:51 <br/>
 * @Author: univ
 */

public class EmailSendTest {

    // STMP服务器信息配置用MailAccount来标识
    @Test
    public void sendEmail() {
        String to = "yyy@163.com";
        String subject = "haha11";
        String content = "文本内容";
        String from = "dailyplanc@163.com";
        String pass = "xxx";

        // STMP服务器信息配置用MailAccount来标识
        MailAccount mailAccount = new MailAccount().setFrom(from).setAuth(true)
                .setUser(from).setPass(pass)
                .setHost("smtp.163.com").setPort(465)
                .setSslEnable(true);

        MailUtil.send(mailAccount, to, subject, content, false);
        System.out.println("发送完毕");
    }

    // STMP服务器信息配置在mail.setting中
    @Test
    public void sendUseMailSetting() {
        String to = "yyy@163.com";
        String subject = "haha11";
        String content = "文本内容";
        MailUtil.sendText(to, subject, content);
        System.out.println("发送完毕");
    }

}
