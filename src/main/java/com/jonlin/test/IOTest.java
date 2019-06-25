package com.jonlin.test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * description IO流测试
 * Author Jonlin
 * Date 2019/6/25 14:25
 **/
public class IOTest {
    /**
     * 功能描述 读取文件
     * 其它：字节流
     * 文字相关：字符流
     * author Jonlin
     * date 2019/6/25 14:26
     * param filePath 文件基于resource相对路径
     */
    public static void readFile(String filePath) throws IOException {
        System.out.println("系统当前默认编码:" + Charset.defaultCharset()); //可以显示系统当前默认编码是什么。默认编码会作为程序中各种编码使用
        String root = IOTest.class.getResource("/").getPath();
        System.out.println("root = " + root);
        String filePathRoot = root.substring(1) + filePath;

        InputStream in = new FileInputStream(filePathRoot); // 字节流
        InputStreamReader reader = new InputStreamReader(in, "GB2312"); // 字符流

        StringBuilder builder = new StringBuilder();
        byte[] bytes = new byte[1024];
        char[] chars = new char[1024];
        while ((reader.read(chars)) != -1) {
//          builder.append(new String(bytes, "ISO-8859-1"));
//          builder.append(new String(bytes));
            reader.read(chars);
            builder.append(new String(chars));
        }
        System.out.println(builder.toString());
    }

    /**
     * 读并且写文件(copy文件)
     * filePath: 文件相对路径
     */
    public static void readAndWhite(String filePath) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            String root = IOTest.class.getResource("/").getPath();
            String filePathRoot = root.substring(1) + filePath;
            System.out.println("filePathRoot = " + filePathRoot);

            in = new FileInputStream(filePathRoot); // 字节流
            out = new FileOutputStream(root + "final.txt");// 字节流
            byte[] buff = new byte[1024];
            while (in.read(buff) != -1) { // in读取文件，将读取的内容缓存到byte[]中
                out.write(buff); // out输出byte[]到out指定的文件中
            }
            // 刷新缓存区（把缓冲区的数据强行输出，没有flush不代表它就没有输出出，只是可能没有完全输出。调用flush是保证缓存清空输出）
            out.flush();
        } catch (IOException ioE) {
            ioE.printStackTrace();
            throw ioE;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 压缩所有文件
     * fileName 文件名
     */
    public static void zipFile(String fileName) throws IOException {
        String basePath = getResourcePath();
        File baseFile = new File(basePath);

        String zipFile = basePath + fileName + ".zip";
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        zipOut.setComment("first zip");

        InputStream input;
        if (baseFile.isDirectory()) {
            File[] files = baseFile.listFiles();
            for (File file1 : files) { // 逐个读取文件
                if (file1.isDirectory()) {
                    continue;
                }

                if ((fileName + ".zip").equals(file1.getName())) {
                    continue;
                }

                input = new FileInputStream(file1);
                zipOut.putNextEntry(new ZipEntry(file1.getName())); // (Entry为根路径:将成为压缩包内的层次结构)+文件名

                byte[] buff = new byte[1024];
                while (input.read(buff) != -1) { // 读取文件并用zipOut流写入zip文件
                    zipOut.write(buff);
                }

                input.close();
            }
        }
        zipOut.flush();
        zipOut.close();
    }

    /**
     * 获取resource目录
     */
    public static String getResourcePath() {
        String root = IOTest.class.getResource("/").getPath();
        String filePathRoot = root.substring(1);
        return filePathRoot;
    }

    /**
     * 读取指定文件所有内容
     * @throws IOException
     */
    public void getAllContent() throws IOException{
        // 获取resource目录下文件
        String pastr= this.getClass().getResource("/test.txt").getFile();
        System.out.println("pastr=" + pastr);
        String content = new String(Files.readAllBytes(Paths.get(pastr.substring(1))), StandardCharsets.UTF_8);
        System.out.println("content=" + content);
    }
}
