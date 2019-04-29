package com.baki.exam.code;

import java.io.*;

/**
 * @author yimting
 * @version 1.0.0
 * @ClassName FileImport.java
 * @Description 文件导入进来
 * @createTime 2019年04月27日 19:13:00
 */
public class FileImport {

    public static void main(String[] args) throws Exception {

        String outPath = "G:\\ideaProject\\dmp\\src\\main\\java\\com\\baki\\exam\\File\\Employee.txt";
        String path = "C:\\Users\\Administrator\\Desktop\\Employee.txt";
        InputStream inputStream = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(inputStream, "GBK");
        OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(outPath, true), "GBK");

        char[] array = new char[1024];
        int length = 0;

        while ((length = isr.read(array)) != -1) {
            // 说明读取到了内容
            String content = new String(array, 0, length);
            System.out.println(content);
            outputStream.write(content);
            outputStream.flush();
        }
        outputStream.close();
        inputStream.close();
    }

}
