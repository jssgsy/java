package com.univ.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import lombok.SneakyThrows;

/**
 * @author univ date 2023/5/24
 */
public class FileUtil {

    /**
     * 删除具体文件中的空行
     * @param file 具体的文件
     */
    @SneakyThrows
    void deleteFileBlankLine(File file) {
        if (!file.isFile()) {
            return;
        }
        String originalFileContent = fileToString(file.getAbsolutePath());
        String destFileContent = originalFileContent.replaceAll("((\\r\\n)|\\n)[\\s\\t ]*(\\1)+", "$1");
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(destFileContent);
            fileWriter.flush();
            System.out.println("空格删除成功：" + file.getAbsolutePath());
        }
    }

    /**
     * 递归删除目录下所有文件中的空行
     * @param dir 文件目录
     */
    void recurseDeleteBlankLine(String dir) {
        File file = new File(dir);
        if (!file.isDirectory() || file.getName().equals("index_files") || file.getName().equals(".git")) {
            return;
        }
        File[] files = file.listFiles();
        // 空目录不处理
        if (null == files) {
            return;
        }
        for (File tmpFile : files) {
            if (tmpFile.isFile() &&
                !tmpFile.getName().equals(".DS_Store") &&
                !tmpFile.getName().toLowerCase().contains(".css") &&
                !tmpFile.getName().toLowerCase().contains(".jpg") &&
                !tmpFile.getName().toLowerCase().contains(".png") &&
                !tmpFile.getName().toLowerCase().contains(".jpeg")
            ) {
                deleteFileBlankLine(tmpFile);
            } else if (tmpFile.isDirectory()) {
                recurseDeleteBlankLine(tmpFile.getAbsolutePath());
            }
        }

    }

    /**
     * 文件内容转成String
     * @param filePath 具体文件的路径
     * @return
     * @throws IOException
     */
    public static String fileToString(String filePath) throws IOException {
        FileReader reader = new FileReader(filePath);
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[100];
        int size;
        while ((size = reader.read(buffer)) != -1) {
            stringBuilder.append(buffer, 0, size);
        }
        reader.close();
        return stringBuilder.toString();
    }

    /**
     * 删除目录下的所有文件，包含目录及本目录
     * @param dir 目录
     * @throws IOException
     */
    public static void deleteDir(File dir) throws IOException {

        File[] files = dir.listFiles();
        if (null == files || files.length == 0) {
            // 说明是空目录，删除之
            System.out.println("目录被删除了： " + dir.getAbsolutePath());
            Files.delete(dir.toPath());
            return;
        }
        for (File file : files) {
            if (file.isFile()) {
                System.out.println("文件被删除了：" + file.getAbsolutePath());
                Files.delete(file.toPath());
            } else {
                deleteDir(file);
            }
        }
        // 此句不能少，否则没法删除目录
        File[] files1 = dir.listFiles();
        if (null == files1 || files1.length == 0) {
            // 说明是空目录，删除之
            System.out.println("2目录被删除了： " + dir.getAbsolutePath());
            Files.delete(dir.toPath());
        }
    }

    /**
     * 是否是末级目录
     * @param dir
     * @return
     */
    public static boolean isLeafDir(File dir) {
        if (!dir.isDirectory()) {
            return false;
        }
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                return false;
            }
        }
        return true;
    }

}
