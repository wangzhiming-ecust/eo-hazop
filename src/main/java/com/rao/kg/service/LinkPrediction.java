package com.rao.kg.service;

import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by wangzhiming on 2022/10/27 21:12
 * 链接预测
 */
@Service
public class LinkPrediction {
    public void LinkPre() {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("D:\\毕业论文\\程序\\TransE - sinopec\\transE-master\\transE.py");
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python D:\\毕业论文\\程序\\TransE-sinopec\\transE-master\\test.py");// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
//    private static String PATH="D:\\TransE-sinopec\\transE-master\\test.py";
//    public static void main(String[] args) throws IOException, InterruptedException {
//    System.out.println(System.getProperty("file.encoding"));
//    //     System.out.println(Charset.defaultCharset().name());
//    System.out.println("开始");
//    final ProcessBuilder processBuilder = new ProcessBuilder("python", PATH);
//    processBuilder.redirectErrorStream(true);
//
//    final Process process = processBuilder.start();
//
//    final BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//    String s = null;
//    while ((s = in.readLine()) != null) {
//        System.out.println(s);
//    }
//    final int result = process.waitFor();
//    System.out.println("执行结果:" + result);
//    System.out.println("结束");
//
//}


}
