package com.univ.thirdutils.jsch;

import com.jcraft.jsch.*;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Arrays;

/**
 * @author univ
 * date 2024/7/26
 */
public class JschDemo {

    private Session session = null;
    private ChannelShell channel = null;

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


    InputStream inputStream = null;
    PrintStream printStream = null;

    static String username = "root";
    static String password = "/*1Univ2aliyun*/";
    static String host = "121.41.101.19";
    static int port = 22;

    public JschDemo() throws JSchException, IOException, InterruptedException {
        session = new JSch().getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        channel = (ChannelShell) session.openChannel("shell");
        channel.setPty(true);
        channel.connect();
        inputStream = channel.getInputStream();
        printStream = new PrintStream(channel.getOutputStream(), true);
        Thread.sleep(1000);
    }

    public static void main(String[] args) throws Exception {
        JschDemo jschDemo = new JschDemo();
        new Thread(() -> {
            logJsch(jschDemo.channel, jschDemo.inputStream);
        }).start();
        Arrays.asList("ls", "pwd", "hello").forEach(cmd -> {
            try {
                jschDemo.shellV2(cmd);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    // 着重理解
    public void shellV2(String command) throws Exception {
        System.out.println("command: " + command);
        printStream.println(command);

    }

    public static void executeShellJsch(String command) throws JSchException {
        ChannelShell channelShell = null;
        PrintStream ps = null;
        Session session = new JSch().getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        String errorDebug = "DEBUG EMPTY";
        try {
            channelShell = (ChannelShell) session.openChannel("shell");
            channelShell.setPty(true);
            InputStream is = channelShell.getInputStream();
            channelShell.connect();
            ps = new PrintStream(channelShell.getOutputStream(), true);
            ps.println(command);
            ps.println("logout");

            // 为啥这里可以正常输出?
            logJsch(channelShell, is);
        } catch (Exception e) {

        } finally {
            if (ps != null) {
                ps.close();
            }

            if (channelShell.getExitStatus() != 0) {
                System.out.println("oooooo");
            }
            closeChannel(channelShell);
            closeSession(session);
        }
    }

    public static void closeSession(Session session) {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }

    public static void closeChannel(Channel channel) {
        if (channel != null && channel.isConnected()) {
            channel.disconnect();
        }
    }

    private static void logJsch(Channel channel, InputStream in) {
        try {
            byte[] tmp = new byte[1024];
            StringBuilder builder = new StringBuilder();
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0)
                        break;
                    builder.append((new String(tmp, 0, i)));
                }
                // 将此次读取到的数据输出
                if (StringUtils.isNotEmpty(builder.toString())) {
                    System.out.println("==:  " + builder.toString());
                    builder.setLength(0);
                }

                // 这里在等外面某人关闭，否则这里会一直阻塞
                if (channel.isClosed()) {
                    System.out.println("channel closed");
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("=== " + ex.getMessage());
        }
//        return builder.toString();
    }


    // 能正常工作的基础shell版本
    public static void basicShell() throws JSchException {
        JSch jSch = new JSch();
        Session session = jSch.getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        ChannelShell channel = (ChannelShell) session.openChannel("shell");

        channel.setOutputStream(System.out);
        channel.setInputStream(System.in);
        channel.connect();
    }

    public static void basicExec(String username, String password, String host, int port, String command) throws Exception {
        Session session = new JSch().getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        // 这是要执行的命令
        channel.setCommand(command);
        channel.connect();
        // 远程服务器的标准输出
        InputStream inputStream = channel.getInputStream();
        // 远程服务器的标准错误输出
        InputStream errStream = channel.getErrStream();
        byte[] tmp = new byte[1024];
        while (true) {
            // 处理远程服务器标准输出
            while (inputStream.available() > 0) {   // 注意这里是while不是if
                int readLen = inputStream.read(tmp, 0, 1024);
                if (readLen < 0)
                    break;
                System.out.println(new String(tmp, 0, readLen));
            }
            // 处理远程服务器标准错误输出
            while (errStream.available() > 0) {
                int readLen = errStream.read(tmp, 0, 1024);
                if (readLen < 0)
                    break;
                System.out.println(new String(tmp, 0, readLen));
            }
            // 重点：因为是一次性命令，因此命令执行后channel就关闭了
            if (channel.isClosed()) {
                break;
            }
        }
        channel.disconnect();
        session.disconnect();
    }

    public static void errorShell(String username, String password, String host, int port, String command) throws Exception {
        Session session = null;
        ChannelShell channel = null;
        try {
            session = new JSch().getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channel = (ChannelShell) session.openChannel("shell");

            channel.connect();
            System.out.println("===连接成功===：" + command);

            // 命令的输入
            OutputStream outputStream = channel.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(command);
//            bufferedWriter.println("exit");
            bufferedWriter.flush();

            // 命令的输出
            InputStream inputStream = channel.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();    // 这里会阻塞
            while (StringUtils.isNotEmpty(line)) {
                System.out.println("line: " + line);
                line = bufferedReader.readLine();
            }
            System.out.println("===haha===");
            /*if (session != null) {
                session.disconnect();
            }*/
            /*if (channel != null) {
                channel.disconnect();
            }*/
            System.out.println("end");
        } finally {
            System.out.println("finally");
            // session一直可用，不用在这里关闭
            if (session != null) {
                session.disconnect();
            }

            if (channel != null) {
                channel.disconnect();
            }
        }
    }

}
