package com.univ.wiz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import org.junit.Test;

/**
 * @author univ date 2023/5/22
 */
public class WizUtil {
    @Test
    public void test1() throws IOException {
        // 导出后为知笔记的根目录
        String dir = "/Users/univ/gitRepos/mynote";
        File file = new File(dir);
        calculateTotalFile(file);
        System.out.println("总文件数为： " + totalFileNum);
        traverse(dir);
        System.out.println("删除掉的文件数为： " + deletedFileNum);

        totalFileNum = 0;
        calculateTotalFile(file);
        System.out.println("剩余文件数为： " + totalFileNum);
    }
    int deletedFileNum = 0;
    int totalFileNum = 0;

    public void traverse(String dir) throws IOException {
        File file = new File(dir);
        if (file.isDirectory()) {
            // 兼容有些文件是没有index_files
            if (noIndexFiles(file)) {
                File file1 = file.listFiles()[0];
                File parentDir = file.getParentFile();
                File dest = new File(parentDir.getAbsolutePath() + "/" + file1.getName() + "X");
                Files.move(file1.toPath(), dest.toPath());
                deleteDir(file);
                Files.move(dest.toPath(), new File(parentDir.getAbsolutePath() + "/" + file1.getName()).toPath());
                return;
            }

            if (isLeafDir(file) && file.getName().equals("index_files")) {
                boolean b = hasImage(file);
                if (!b) {
                    System.out.println("index_fle: " + file + " 下无图片资源，可删");
                    File indexFilesParentDir = file.getParentFile();
                    System.out.println("index_files's parent dir: " + indexFilesParentDir);
                    // 找到其下的md文件
                    File mdFile = findMdFile(indexFilesParentDir);
                    if (null == mdFile) {
                        return;
                    }
                    File mdFileDir = indexFilesParentDir.getParentFile();

                    File destDir = new File(mdFileDir.getAbsolutePath() + "/" + mdFile.getName() + "X");
                    Files.move(mdFile.toPath(), destDir.toPath());
                    deleteDir(indexFilesParentDir);
                    Files.move(destDir.toPath(), new File(mdFileDir.getAbsolutePath() + "/" + mdFile.getName()).toPath());
                    return;
                }
            }

            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isDirectory()) {
                    traverse(f.getAbsolutePath());
                }
            }
        }
    }

    /**
     * 是否是末级目录
     * @param dir
     * @return
     */
    public boolean isLeafDir(File dir) {
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

    public boolean hasImage(File dir) {
        if (!dir.isDirectory()) {
            return false;
        }
        File[] files = dir.listFiles();
        if (null == files) {
            // 兼容空目录
            return false;
        }
        for (File file : files) {
            String fileName = file.getName();
            String[] split = fileName.split("\\.");
            String fileExt = split[split.length - 1];
            if (Arrays.asList("png", "jpg").contains(fileExt)) {
                return true;
            }
        }
        return false;
    }

    public void deleteDir(File dir) throws IOException {

        File[] files = dir.listFiles();
        if (null == files || files.length == 0) {
            // 说明是空目录，删除之
            deletedFileNum++;
            System.out.println("目录被删除了： " + dir.getAbsolutePath());
            Files.delete(dir.toPath());
            return;
        }
        for (File file : files) {
            if (file.isFile()) {
                deletedFileNum++;
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
            deletedFileNum++;
            Files.delete(dir.toPath());
        }
    }

    public File findMdFile(File dir) {
        if (!dir.isDirectory()) {
            return null;
        }
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.getName().contains(".md")) {
                return file;
            }
        }
        return null;
    }


    //    @Test
    public void deletedir() throws IOException {
        String file = "/Users/univ/gitRepos/java/data/linux/vim/gn.md/index_files";
        deleteDir(new File(file));
    }

    /**
     * 包含目录
     * @param file
     */
    void calculateTotalFile(File file) {
        if (file.isFile() && !file.getName().contains(".DS_Store")) {
            totalFileNum++;
        } else if (file.isDirectory()){
            totalFileNum++;
            File[] files = file.listFiles();
            for (File file1 : files) {
                calculateTotalFile(file1);
            }
        }
    }

    public boolean noIndexFiles(File file) {
        // 必须是个目录
        if (!file.isDirectory()) {
            return false;
        }
        File[] files = file.listFiles();
        if (null != files && files.length == 1 && files[0].getName().equals(file.getName())) {
            return true;
        }
        return false;
    }

    //    @Test
    public void testNoIndexFiles() {
        String path = "/Users/univ/gitRepos/java/data/linux/常见模式/缓存的一种形式.md/index_files";
        System.out.println(noIndexFiles(new File(path)));
    }

}
