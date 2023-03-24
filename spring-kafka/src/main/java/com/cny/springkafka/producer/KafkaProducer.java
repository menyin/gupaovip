package com.cny.springkafka.producer;

import com.cny.springkafka.modal.PersonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Description: 基本实战看：csdn《SpringBoot集成kafka全面实战》
 * @author: geduo
 * @date: 2023年03月16日 16:37
 */

@RestController
public class KafkaProducer {
    private static String discr = "马化腾 ，男，1971年10月29日生于广东省汕头市潮南区。腾讯公司主要创办人之一，现担任腾讯公司控股董事会**兼首*执行官；全国青联副**他曾在深圳大学主修计算机及应用，于1993年取得深大理科学士学位。在创办腾讯之前，马化腾曾在中国电信服务和产品供应商深圳润迅通讯发展有限公司主管互联网传呼系统的研究开发工作，在电信及互联网行业拥有10多年经验。1998年和好友张志东注册成立深圳市腾讯计算机系统有限公司。2009年，腾讯入选《财富》“全球最受尊敬50家公司”。在2014年3000中国家族财富榜中马化腾以财富1007亿元荣登榜首，相比于2013年，财富增长了540亿元。";
    /***
     * kafkaTemplate.send()发送消息时，kafka会帮我们自动完成topic的创建工作，但这种情况下创建的topic默认只有一个分区，分区也没有副本。
     */
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    // 发送消息

    /***
     *
     * @param normalMessage
     * @param nums 发送数量
     * @return
     * @description
     * kafka集群为3台
     * 1、默认配置下 qps=13w “helloword”
     */
    @GetMapping("/kafka/normal/{message}")
    public String sendMessage1(@PathVariable("message") String normalMessage, @RequestParam(name = "nums") Integer nums) {
        long time = new Date().getTime();
        for (int i = 0; i < nums; i++) {
            kafkaTemplate.send("topic1", normalMessage);
        }
        long userTimes=new Date().getTime()-time;
        String str="发送kafka"+nums+"条"+normalMessage+"消息成功：，用时："+userTimes+"毫秒";
        System.out.println(str);
        return str;
    }

    /***
     *
     * @param nums 发送数量
     * @return
     *  @description
     *   kafka集群为3台
     *   1、默认配置下 qps=4.6w PersonMsg对象（单对象大概850B）
     */
    @GetMapping("/kafka/obj")
    public String sendMessageObj(@RequestParam(name = "nums") Integer nums) {
        PersonMsg personMsg = new PersonMsg("马化腾", "马化腾", 50, new Date("1962/02/02"), discr);//personMsg大小在856B左右
        long time = new Date().getTime();
        for (int i = 0; i < nums; i++) {
            kafkaTemplate.send("topicPersonMsg", personMsg);
        }
        long userTimes=new Date().getTime()-time;
        String str="发送kafka"+nums+"条personMsg对象消息成功，用时："+userTimes+"毫秒";
        System.out.println(str);
        return str;
    }



}
