package com.example.springbootdemo.crawler;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年03月10日 8:54
 */

@RestController()
@RequestMapping("/mysql")
@ConditionalOnProperty(prefix = "crawler",name = "mode",havingValue = "file")
public class ResumesController implements ApplicationContextAware {

    @Value("${crawler.awaitTimeout}")
    private Integer awaitTimeout=20000;
    @Value("${crawler.cacheSize}")
    private Integer cacheSize=20;
    @Value("${crawler.filePath}")
    private String filePath="G:/resumetTest.txt";

    private static CountDownLatch countDownLatch;
    private static LinkedBlockingDeque<String> cacheList = new LinkedBlockingDeque<>();

    /**
     * 获取最后一次采集的页码
     * @param req
     * @return
     */
    @RequestMapping(value = "/getLastPage")
    public String getLastPage(HttpServletRequest req) {
        String callback = req.getParameter("callback");
        String lastJson = readLastLine(filePath);
        if(lastJson!=null&&!StringUtils.isEmpty(lastJson)){
            JSONObject jsonObject = JSONObject.parseObject(lastJson);
            Integer page = jsonObject.getInteger("page");
            String regDate = jsonObject.getString("regDate");
            if(page!=null){
                return callback + "({\"status\":\"1\",\"page\" : \"" + page + "\",\"regDate\" : \"" + regDate + "\"})";
            }
        }
        return callback + "({\"status\":\"0\",\"page\" : \"" + 0 + "\",\"regDate\" : \"" +new Date().toLocaleString().split(" ")[0] + "\"})";
    }

    /**
     * 接收采集json数据
     * @param req
     * @param json
     * @return
     */
    @RequestMapping(value = "/jsonp")
    public String jsonp(HttpServletRequest req, @RequestParam(name = "json") String json) {

        String callback = req.getParameter("callback");
        try {
            cacheList.put(json);
            countDownLatch.countDown();
            System.out.println("countDown");
//            Thread.sleep(10);
            if (countDownLatch.getCount() == 0) {
                countDownLatch = new CountDownLatch(cacheSize);
            }
        } catch (InterruptedException e) {
            System.out.println("({\"status\":\"0\",\"json\" : \"" + "thread error" + "\"})");
            return callback + "({\"status\":\"0\",\"json\" : \"" + "thread error" + "\"})";
        }

        return callback + "({\"status\":\"1\",\"json\" : \"" + "write success" + "\"})";
    }


    /**
     * 读文件测试
     */
    public static void readFileTest() {
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
     * 读文件测试
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

    /**
     * 初始化启动消费线程
     */
    @PostConstruct
    public void init() {
        this.countDownLatch = new CountDownLatch(this.cacheSize);
        System.out.println("缓冲区大小："+cacheSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            while (true) {
                System.out.println("等待缓冲区满再执行写操作...");
                this.countDownLatch.await(awaitTimeout, TimeUnit.MILLISECONDS);
                writeJsons();
//                Thread.sleep(100);//喘息，使得生产线程有机会抢时间片重建countDownLatch
            }
        });
    }

    /**
     * 使用FileOutputStream来写入txt文件
     */
    public void writeJsons() {
        int size = cacheList.size();
        if (size == 0) {
            return;
        }

        LinkedList<String> strings = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            String json = null;
            try {
                json = cacheList.take();
            } catch (InterruptedException e) {
                System.out.println("获取cacheList元素出现异常...");
            }
            strings.add(json);
        }
        String collect = strings.stream().collect(Collectors.joining("\n"));

        //判断文件对象是否存在
        /*File file=new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("创建文件出现异常...");
            }
        }*/


         FileWriter fw =null;
         BufferedWriter bw =null;
        try {
            fw = new FileWriter(filePath, true);
            bw = new BufferedWriter(fw);
            bw.append(collect + "\n");
        } catch (Exception e) {
            System.out.println("({\"json\" : \"" + "write error" + "\"})");
        }finally {
            try {
                bw.close();
                fw.close();

            } catch (IOException e) {
                System.out.println("关闭文件出现异常...");
            }
            System.out.println("缓冲区已满，执行一次写入...");
        }

    }


    public static void writeTest() throws IOException {
        FileWriter fw = new FileWriter("G:/jsonsr.txt", true);
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


    public static String readLastLine(String filePath) {
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
        readLastLine("G:/test.txt");
    }

    /**
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}

