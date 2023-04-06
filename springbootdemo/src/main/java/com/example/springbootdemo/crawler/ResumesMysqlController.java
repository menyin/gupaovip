package com.example.springbootdemo.crawler;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springbootdemo.entity.Resume;
import com.example.springbootdemo.mapper.ResumeMapper;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年03月17日 10:40
 */
@RestController()
@RequestMapping("/mysql")
@ConditionalOnProperty(prefix = "crawler",name = "mode",havingValue = "mysql")
public class ResumesMysqlController {
    @Autowired
    private ResumeMapper resumeMapper;
    @Value("${crawler.awaitTimeout}")
    private Integer awaitTimeout=20000;
    @Value("${crawler.cacheSize}")
    private Integer cacheSize=40;
    @Value("${crawler.filePath}")
    private String filePath="G:/resumetTest.txt";

    private static CountDownLatch countDownLatch;
    private static LinkedBlockingDeque<String> cacheList = new LinkedBlockingDeque<>();
    @RequestMapping(value = "/baseTest")
    public String baseTest(HttpServletRequest req){
        Resume resume = new Resume(null, "123", "2022-11-12", 1, 2);
        resumeMapper.insert(resume);
        QueryWrapper<Resume> resumeQueryWrapper = new QueryWrapper<>();
        resumeQueryWrapper.inSql("id","select max(id) from resume");//"select max(id) from resume"
        Resume lastResume = resumeMapper.selectOne(resumeQueryWrapper);
        return resume.getId();
    }

    /**
     * 获取最后一次采集的页码
     * @param req
     * @return
     */
    @RequestMapping(value = "/getLastPage")
    public String getLastPage(HttpServletRequest req) {
        String callback = req.getParameter("callback");

        QueryWrapper<Resume> resumeQueryWrapper = new QueryWrapper<>();
        resumeQueryWrapper.inSql("id","select max(id) from resume");
        Resume lastResume = resumeMapper.selectOne(resumeQueryWrapper);

        if(lastResume!=null){
            Integer page = lastResume.getPage();
            String regDate = lastResume.getRegDate();
            if(page!=null&&lastResume.getRegDate()!=null){
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

        for (int i = 0; i < size; i++) {
            String json = null;
            try {
                json = cacheList.take();
                Resume resume = JSONObject.parseObject(json, Resume.class);
                resumeMapper.insert(resume);
            } catch (InterruptedException e) {
                System.out.println("获取cacheList元素出现异常...");
            }

        }
        System.out.println("缓冲区已满，执行一次写入...");
    }

}
