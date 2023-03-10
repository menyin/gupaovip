package com.example.springbootdemo.crawler;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Description: 测试时请将 @RestController 注释打开
 * @author: geduo
 * @date: 2023年03月03日 14:23
 */
//@RestController
public class TestController {
    //    private static List<String> cacheList = new LinkedList<>();
    private static LinkedBlockingDeque<String> cacheList = new LinkedBlockingDeque<>();
    private static Integer cacheSize = 2;
    private static CountDownLatch countDownLatch = new CountDownLatch(cacheSize);
    private static String filePath="G:/resumes.txt";

    @RequestMapping(value = "/getLastPage")
    public String getLastPage(HttpServletRequest req) {
        String callback = req.getParameter("callback");
        String lastJson = readLastLineV2(filePath);
        if(lastJson!=null&&!StringUtils.isEmpty(lastJson)){
            JSONObject jsonObject = JSONObject.parseObject(lastJson);
            Integer page = jsonObject.getInteger("page");
            if(page!=null){
                return callback + "({\"status\":\"1\",\"page\" : \"" + page + "\"})";
            }
        }
        return callback + "({\"status\":\"0\",\"page\" : \"" + 1 + "\"})";
    }
    @RequestMapping(value = "/jsonp")
    public String jsonp(HttpServletRequest req, @RequestParam(name = "json") String json) {

        String callback = req.getParameter("callback");
        try {
            cacheList.put(json);
            countDownLatch.countDown();
            System.out.println("countDown");

            if (countDownLatch.getCount() == 0) {
                countDownLatch = new CountDownLatch(cacheSize);
            }
        } catch (InterruptedException e) {
            System.out.println("({\"status\":\"0\",\"json\" : \"" + "write error" + "\"})");
            return callback + "({\"status\":\"0\",\"json\" : \"" + "write error" + "\"})";
        }

        return callback + "({\"status\":\"1\",\"json\" : \"" + "write error" + "\"})";
    }


    public static void dd() {
        try {

            String encoding = "utf-8"; //设定自己需要的字符编码集

            File file = new File("G:/jsonsr.txt");

            if (file.exists() && file.isFile()) {

                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
                //FileReader read = new FileReader(file); //不考虑转码，可使用FileReader;
                BufferedReader br = new BufferedReader(read);//使用缓冲流按行读取
                String lineText = null;
                StringBuffer sb = new StringBuffer();

                while ((lineText = br.readLine()) != null) {
                    System.out.println(lineText);
                    sb.append(lineText + "\n");
                }
                br.close();
                read.close();


            } else {

                System.out.println("找不到指定的文件");

            }

        } catch (IOException e) {

// TODO Auto-generated catch block

            e.printStackTrace();

        }
    }


    /**
     * 传入txt路径读取txt文件
     *
     * @param txtPath
     * @return 返回读取到的内容
     */
    public static String readTxt(String txtPath) {
        File file = new File(txtPath);
        if (file.isFile() && file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer sb = new StringBuffer();
                String text = null;
                while ((text = bufferedReader.readLine()) != null) {
                    sb.append(text);
                }
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    static {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            while (true) {
                writeJsons();
            }
        });
    }

    /**
     * 使用FileOutputStream来写入txt文件
     *
     * @param txtPath txt文件路径
     * @param jsons   需要写入的文本
     */
    public static void writeJsons() throws IOException, InterruptedException {
        System.out.println("消费线程持续执行1....");
        countDownLatch.await(30, TimeUnit.SECONDS);
        System.out.println("消费线程持续执行2....");

        int size = cacheList.size();
        if (size == 0) {
            return;
        }
        FileWriter fw = new FileWriter(filePath, true);
        final BufferedWriter bw = new BufferedWriter(fw);
        LinkedList<String> strings = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            String json = cacheList.take();
            strings.add(json);
        }
        String collect = strings.stream().collect(Collectors.joining("\n"));
        try {
            bw.append(collect + "\n");
        } catch (Exception e) {
            System.out.println("({\"json\" : \"" + "write error" + "\"})");
        }
        bw.close();
        fw.close();
        Thread.sleep(100);//喘息，使得生产线程有机会抢时间片
    }


    public static void writeTest() throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        final BufferedWriter bw = new BufferedWriter(fw);
        LinkedList<String> strings = new LinkedList<>();
        try {
            bw.write("111" + "\n");
            bw.write("222" + "\n");
            bw.write("333" + "\n");
        } catch (Exception e) {
            System.out.println("({\"json\" : \"" + "write error" + "\"})");
        }
        bw.close();
        fw.close();
    }
    public static void writeBigTest() throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        final BufferedWriter bw = new BufferedWriter(fw);
        LinkedList<String> strings = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <10000 ; i++) {
            stringBuilder.append("社会主义核心价值观践行标准..."+"\n");
        }
        try {
            for (int i = 0; i <20000 ; i++) {
                bw.write(stringBuilder.toString());
            }
        } catch (Exception e) {
            System.out.println("({\"json\" : \"" + "write error" + "\"})");
        }
        bw.close();
        fw.close();
    }


    public static String readLastLineV2(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            String lastLine = "";
            ReversedLinesFileReader reversedLinesReader = null;
            try {
                reversedLinesReader = new ReversedLinesFileReader(file, Charset.defaultCharset());

                while (true) {
                    lastLine = reversedLinesReader.readLine();
                    if(StringUtils.isEmpty(lastLine)){
                        continue;
                    }else{
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("file read error, msg:{}");
            }finally {
                return lastLine;
            }
        }
        return null;
    }

    public static void main1(String[] args) throws IOException {
//        readTxt("G:/test.txt");
//        readLastLineV2("G:/test.txt");
        writeBigTest();
    }
}
