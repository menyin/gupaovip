package com.example.springbootdemo.crawler;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springbootdemo.entity.Company;
import com.example.springbootdemo.mapper.CompanyMapper;
import com.example.springbootdemo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年03月17日 10:40
 */
@RestController()
@RequestMapping("/company/mysql")
@ConditionalOnProperty(prefix = "crawler",name = "mode",havingValue = "mysql")
public class CompanysMysqlController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyMapper companyMapper;
    @Value("${crawler.awaitTimeout}")
    private Integer awaitTimeout=20000;
    @Value("${crawler.cacheSize}")
    private Integer cacheSize=40;
    @Value("${crawler.filePath}")
    private String filePath="G:/companyTest.txt";

    private static CountDownLatch countDownLatch;
    private static LinkedBlockingDeque<String> cacheList = new LinkedBlockingDeque<>();
    @RequestMapping(value = "/baseTest")
    public String baseTest(HttpServletRequest req){
        Company company = new Company(null, "123", "2022-11-12", 1, 2);
        companyMapper.insert(company);
        QueryWrapper<Company> companyQueryWrapper = new QueryWrapper<>();
        companyQueryWrapper.inSql("id","select max(id) from company");//"select max(id) from company"
        Company lastCompany = companyMapper.selectOne(companyQueryWrapper);
        return lastCompany.getId();
    }

    /**
     * 获取最后一次采集的页码
     * @param req
     * @return
     */
    @RequestMapping(value = "/getLastPage")
    public String getLastPage(HttpServletRequest req) {
        String callback = req.getParameter("callback");

        QueryWrapper<Company> companyQueryWrapper = new QueryWrapper<>();
        companyQueryWrapper.inSql("id","select max(id) from company");
        Company lastCompany = companyMapper.selectOne(companyQueryWrapper);

        if(lastCompany!=null){
            Integer page = lastCompany.getPage();
            String regDate = lastCompany.getRegDate();
            if(page!=null&&lastCompany.getRegDate()!=null){
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
    public void writeJsons1() {
        int size = cacheList.size();
        if (size == 0) {
            return;
        }
        ArrayList<Company> companies = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            String json = null;
            try {
                json = cacheList.take();
                Company company = JSONObject.parseObject(json, Company.class);
                companies.add(company);
//                companyMapper.insert(company);
            } catch (InterruptedException e) {
                System.out.println("获取cacheList元素出现异常...");
            }
        }
        companyService.saveBatch(companies);
        System.out.println("缓冲区已满，执行一次写入...");
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
                Company company = JSONObject.parseObject(json, Company.class);
                companyMapper.insert(company);
            } catch (InterruptedException e) {
                System.out.println("获取cacheList元素出现异常...");
            }

        }
        System.out.println("缓冲区已满，执行一次写入...");
    }




}
