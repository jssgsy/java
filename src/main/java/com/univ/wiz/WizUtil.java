package com.univ.wiz;

import com.univ.util.FileUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import org.junit.Test;

/**
 * 处理为知笔记导出为md文件后，很多index_files目录下没有图片资源，可直接删除，同时将md文件移动至正确位置，免去手动操作
 *
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

        totalFileNum = 0;
        calculateTotalFile(file);
        System.out.println("剩余文件数为： " + totalFileNum);
    }

    int totalFileNum = 0;

    /**
     * 目标：index_files目录下没有图片，删除此index_files目录及其下所有文件，且将md文件移动至正确的位置
     * 注：逻辑和为知笔记导出为md文件后的结构强耦合；
     *
     * @param dir
     * @throws IOException
     */
    public void traverse(String dir) throws IOException {
        File file = new File(dir);
        if (file.isDirectory()) {
            // 兼容有些文件是没有index_files
            if (noIndexFiles(file)) {
                File file1 = file.listFiles()[0];
                File parentDir = file.getParentFile();
                File dest = new File(parentDir.getAbsolutePath() + "/" + file1.getName() + "X");
                Files.move(file1.toPath(), dest.toPath());
                FileUtil.deleteDir(file);
                Files.move(dest.toPath(), new File(parentDir.getAbsolutePath() + "/" + file1.getName()).toPath());
                return;
            }

            if (FileUtil.isLeafDir(file) && file.getName().equals("index_files")) {
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
                    FileUtil.deleteDir(indexFilesParentDir);
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

    /**
     * 在目录下寻找markdown文件
     * @param dir
     * @return
     */
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

}
