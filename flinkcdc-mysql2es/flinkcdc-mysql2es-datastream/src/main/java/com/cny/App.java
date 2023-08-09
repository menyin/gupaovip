package com.cny;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import com.cny.cdc.domain.companys.entity.Company;
import com.cny.cdc.domain.companys.entity.CompanyWrapper;
import com.cny.cdc.domain.companys.entity.Json;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App
{
    public static ConfigurableApplicationContext ac;
    public static void main( String[] args )
    {
        System.out.println( "将Mysql数据进行清洗同步到ES begin" );
         ac = SpringApplication.run(App.class, args);
        System.out.println( "将Mysql数据进行清洗同步到ES end" );
//        testJsonParse();
//        testJsonParse1();
    }

    public static void testJsonParse(){
//        String json="{\"_cid\": \"6fe52a6598760\", \"page\": 1, \"type\": \"3\", \"_cname\": \"<a href=\\\"https://www.597.com/com-6fe52a6598760/\\\" target=\\\"_blank\\\"  class=\\\"text-link\\\"><strong>厦门市湖里区淏淞餐饮店</strong></a><br><span><a class=\\\"btn btn-mini\\\" target=\\\"_blank\\\" href=\\\"/companyinfo/companyinfo.html?type=3&act=view&c_id=6fe52a6598760\\\">查看详情</a></span><br><span>执照状态:<font color=\\\"green\\\">已认证</font>(√)</span><br><span>绑定时间:23-04-04 14:08</span>\", \"_source\": \"自注<br><span class=\\\"adminType\\\">开发库</span>\", \"_modTime\": \"23-04-04 14:09/<br>\", \"_regTime\": \"23-04-04 14:00/<br>\", \"vipStyle\": \"normalStyle\", \"_nextType\": \"D类/可跟进\", \"_username\": \"<a href=\\\"/companyinfo/companyinfo.html?type=3&act=view&c_id=6fe52a6598760&uid=85f0137521824#followList\\\" class=\\\"text-link\\\" target=\\\"_blank\\\"><strong>MO168058640030754</strong></a><br/>\", \"comCityId\": \"厦门市\", \"operation\": \"\", \"pageIndex\": 4, \"_followTime\": \"23-04-04 14:20/<br>23-04-18 14:19\", \"adminUsername\": \"庄燕兰\", \"companyTypeStr\": \"新客户\", \"companyViewData\": {\"type\": \"3\", \"cname\": \"厦门市湖里区淏淞餐饮店\", \"comInfo\": \"\\n是一家制作休闲炸串食品的公司，在茶余饭后，休闲的时候来上一份炸串，也是一种不错的享受              \\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\", \"comSite\": \"\", \"comType\": \"民营、私营公司\", \"modTime\": \"23-04-04 14:09:17\", \"regTime\": \"23-04-04 14:00:11\", \"typeStr\": \" \", \"comAreaId\": \" 湖里区\", \"comCityId\": \"厦门市 \", \"loginTime\": \" \", \"comAddress\": \"厦门夸父炸串五缘湾天虹店\", \"comWorkers\": \"10人以下\", \"industryName\": \"餐饮\"}, \"_expireFollowTime\": \"23-07-03 14:08\", \"companyTongjiData\": {\"data\": {\"data\": [{\"id\": \"1185017\", \"cid\": \"6598760\", \"uid\": \"7521824\", \"_cid\": \"6fe52a6598760\", \"_uid\": \"85f0137521824\", \"name\": \"牛志军\", \"admin\": 1, \"isVip\": 0, \"phone\": \"18965853689\", \"photo\": null, \"_photo\": \"//pic.597.com/hrPhoto/default.png\", \"duties\": \"老板\", \"jobNum\": \"0\", \"wechat\": \"否\", \"weixin\": \"\", \"_status\": \"已是管理员\", \"banTime\": \"1680588071\", \"isAdmin\": \"1\", \"isCheck\": \"1\", \"_banTime\": \"2023-04-04 14:01:11\", \"isVipStr\": \"否\", \"username\": \"MO168058640030754\", \"chat_time\": \"\", \"_loginTime2\": \"2023-04-04 13:33:31\", \"app_login_time\": \"\", \"last_join_time\": \"\", \"isShareContract\": \"1\"}], \"totalCounts\": 1}, \"type\": \"3\"}, \"companyJobslistData\": {\"jobs\": [{\"jid\": \"5567776\", \"c_id\": \"6fe52a6598760\", \"jname\": \"服务员（临时工） \"}, {\"jid\": \"5567771\", \"c_id\": \"6fe52a6598760\", \"jname\": \"餐饮服务员 \"}], \"type\": \"3\", \"jobCount\": 0}, \"companyLicenceinfoData\": {\"type\": \"3\", \"cname\": \"厦门市湖里区淏淞餐饮店                                                            \", \"_cname\": \"厦门市湖里区淏淞餐饮店\", \"comTel\": \"18965853689\", \"areaStr\": \"庄燕兰\", \"comUser\": \"牛志军\", \"isCheck\": 1, \"comMobile\": \"18965853689\", \"licenceCheck\": 2}}";
       try{
           String data="{\"id\":2,\"page\":1,\"page_index\":1,\"reg_date\":\"2007-01-16\",\"json\":{\"_cid\": \"6fe52a6598760\", \"page\": 1, \"type\": \"3\", \"_cname\": \"<a href=\\\"https://www.597.com/com-6fe52a6598760/\\\" target=\\\"_blank\\\"  class=\\\"text-link\\\"><strong>厦门市湖里区淏淞餐饮店</strong></a><br><span><a class=\\\"btn btn-mini\\\" target=\\\"_blank\\\" href=\\\"/companyinfo/companyinfo.html?type=3&act=view&c_id=6fe52a6598760\\\">查看详情</a></span><br><span>执照状态:<font color=\\\"green\\\">已认证</font>(√)</span><br><span>绑定时间:23-04-04 14:08</span>\", \"_source\": \"自注<br><span class=\\\"adminType\\\">开发库</span>\", \"_modTime\": \"23-04-04 14:09/<br>\", \"_regTime\": \"23-04-04 14:00/<br>\", \"vipStyle\": \"normalStyle\", \"_nextType\": \"D类/可跟进\", \"_username\": \"<a href=\\\"/companyinfo/companyinfo.html?type=3&act=view&c_id=6fe52a6598760&uid=85f0137521824#followList\\\" class=\\\"text-link\\\" target=\\\"_blank\\\"><strong>MO168058640030754</strong></a><br/>\", \"comCityId\": \"厦门市\", \"operation\": \"\", \"pageIndex\": 4, \"_followTime\": \"23-04-04 14:20/<br>23-04-18 14:19\", \"adminUsername\": \"庄燕兰\", \"companyTypeStr\": \"新客户\", \"companyViewData\": {\"type\": \"3\", \"cname\": \"厦门市湖里区淏淞餐饮店\", \"comInfo\": \"\\n是一家制作休闲炸串食品的公司，在茶余饭后，休闲的时候来上一份炸串，也是一种不错的享受              \\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\", \"comSite\": \"\", \"comType\": \"民营、私营公司\", \"modTime\": \"23-04-04 14:09:17\", \"regTime\": \"23-04-04 14:00:11\", \"typeStr\": \" \", \"comAreaId\": \" 湖里区\", \"comCityId\": \"厦门市 \", \"loginTime\": \" \", \"comAddress\": \"厦门夸父炸串五缘湾天虹店\", \"comWorkers\": \"10人以下\", \"industryName\": \"餐饮\"}, \"_expireFollowTime\": \"23-07-03 14:08\", \"companyTongjiData\": {\"data\": {\"data\": [{\"id\": \"1185017\", \"cid\": \"6598760\", \"uid\": \"7521824\", \"_cid\": \"6fe52a6598760\", \"_uid\": \"85f0137521824\", \"name\": \"牛志军\", \"admin\": 1, \"isVip\": 0, \"phone\": \"18965853689\", \"photo\": null, \"_photo\": \"//pic.597.com/hrPhoto/default.png\", \"duties\": \"老板\", \"jobNum\": \"0\", \"wechat\": \"否\", \"weixin\": \"\", \"_status\": \"已是管理员\", \"banTime\": \"1680588071\", \"isAdmin\": \"1\", \"isCheck\": \"1\", \"_banTime\": \"2023-04-04 14:01:11\", \"isVipStr\": \"否\", \"username\": \"MO168058640030754\", \"chat_time\": \"\", \"_loginTime2\": \"2023-04-04 13:33:31\", \"app_login_time\": \"\", \"last_join_time\": \"\", \"isShareContract\": \"1\"}], \"totalCounts\": 1}, \"type\": \"3\"}, \"companyJobslistData\": {\"jobs\": [{\"jid\": \"5567776\", \"c_id\": \"6fe52a6598760\", \"jname\": \"服务员（临时工） \"}, {\"jid\": \"5567771\", \"c_id\": \"6fe52a6598760\", \"jname\": \"餐饮服务员 \"}], \"type\": \"3\", \"jobCount\": 0}, \"companyLicenceinfoData\": {\"type\": \"3\", \"cname\": \"厦门市湖里区淏淞餐饮店                                                            \", \"_cname\": \"厦门市湖里区淏淞餐饮店\", \"comTel\": \"18965853689\", \"areaStr\": \"庄燕兰\", \"comUser\": \"牛志军\", \"isCheck\": 1, \"comMobile\": \"18965853689\", \"licenceCheck\": 2}}}";
           Company company = JSONObject.parseObject(data, Company.class);
           String dataError="{\"id\":1,\"json\":\"123388\",\"page\":1,\"page_index\":1,\"reg_date\":\"2006-01-16\"}";
           Company companyError = JSONObject.parseObject(dataError, Company.class);
           System.out.println(company);
       }catch (Exception ex){
           System.out.println(ex.getMessage());
           if(ex instanceof JSONException){
               System.out.println("json解析错误");
           }
       }

    }
    public static void testJsonParse1(){
        /**
         * 因为mysql中company.json字段是json类型，在flinkCDC接收到的binlog中，该字段又做了一层转移，所以要逐渐层进行反序列化
         */
        String data="{\"id\":71,\"json\":\"{\\\"_cid\\\": \\\"d838a86598803\\\", \\\"page\\\": 1, \\\"type\\\": \\\"3\\\"}\",\"page\":1,\"page_index\":0,\"reg_date\":\"2023-04-04\"}";
        CompanyWrapper companyWrapper =  JSONObject.parseObject(data, CompanyWrapper.class);
        Json json = JSONObject.parseObject(companyWrapper.getJson(), Json.class);
        Company company = new Company();
        company.setId(companyWrapper.getId());
        company.setPage(companyWrapper.getPage());
        company.setPageIndex(companyWrapper.getPageIndex());
        company.setRegDate(companyWrapper.getRegDate());
        company.setJson(json);
        System.out.println(company);
    }
}
