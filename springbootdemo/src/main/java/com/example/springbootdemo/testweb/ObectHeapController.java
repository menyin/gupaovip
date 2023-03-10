package com.example.springbootdemo.testweb;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.stereotype.Controller;
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
 * @Description: TODO
 * @author: geduo
 * @date: 2023年03月03日 14:23
 */
@RestController
public class ObectHeapController {

    @GetMapping("/getPerson")

    public String getPerson(String req) {

//        String person2="{\"age\":18,\"name\":\"马化腾\",\"birthDate\":\"2022/02/02\",\"add\":\"广东省广州市\",\"school\":\"观音山小学\",decription:\"马化腾 ，男，1971年10月29日生于广东省汕头市潮南区。腾讯公司主要创办人之一，现担任腾讯公司控股董事会**兼首*执行官；全国青联副**他曾在深圳大学主修计算机及应用，于1993年取得深大理科学士学位。在创办腾讯之前，马化腾曾在中国电信服务和产品供应商深圳润迅通讯发展有限公司主管互联网传呼系统的研究开发工作，在电信及互联网行业拥有10多年经验。1998年和好友张志东注册成立深圳市腾讯计算机系统有限公司。2009年，腾讯入选《财富》“全球最受尊敬50家公司”。在2014年3000中国家族财富榜中马化腾以财富1007亿元荣登榜首，相比于2013年，财富增长了540亿元。\"}";
        return req + " 你好";

    }

    public static void main1(String[] args) {

    }
}
