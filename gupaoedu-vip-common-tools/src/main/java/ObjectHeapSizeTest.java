import com.alibaba.fastjson.JSONObject;
import org.apache.lucene.util.RamUsageEstimator;

import java.util.Date;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年03月03日 13:38
 */
public class ObjectHeapSizeTest {
    public static void main(String[] args) {

        String jack = "jack";
        //按byte为单位查看
        System.out.println("value is " + RamUsageEstimator.sizeOf(jack));
        //按单位查看
        System.out.println("value is " + RamUsageEstimator.humanSizeOf(jack));

        Person person0 = new Person(18, "马化腾", new Date("2022/02/02"), "广东省广州市", "观音山小学");
        String person1 = JSONObject.toJSONString(person0);
        String person2="{\"age\":18,\"name\":\"马化腾\",\"birthDate\":\"2022/02/02\",\"add\":\"广东省广州市\",\"school\":\"观音山小学\",decription:\"马化腾 ，男，1971年10月29日生于广东省汕头市潮南区。腾讯公司主要创办人之一，现担任腾讯公司控股董事会**兼首*执行官；全国青联副**他曾在深圳大学主修计算机及应用，于1993年取得深大理科学士学位。在创办腾讯之前，马化腾曾在中国电信服务和产品供应商深圳润迅通讯发展有限公司主管互联网传呼系统的研究开发工作，在电信及互联网行业拥有10多年经验。1998年和好友张志东注册成立深圳市腾讯计算机系统有限公司。2009年，腾讯入选《财富》“全球最受尊敬50家公司”。在2014年3000中国家族财富榜中马化腾以财富1007亿元荣登榜首，相比于2013年，财富增长了540亿元。\"}";

        //按单位查看
        System.out.println("person0 heap value is " + RamUsageEstimator.humanSizeOf(person0));
        System.out.println("person1 heap value is " + RamUsageEstimator.humanSizeOf(person1));
        System.out.println("person2 heap value is " + RamUsageEstimator.humanSizeOf(person2));


        int intObj=1;
        System.out.println("intObj heap value is " + RamUsageEstimator.humanSizeOf(intObj));
        Integer integer=1;
        System.out.println("integer heap value is " + RamUsageEstimator.humanSizeOf(integer));

        String resume="{\"graduationTime\":\"\",\"ischeck\":\"1\",\"practiceList\":\"\",\"jobtypeList\":[],\"fertility\":\"0\",\"qq\":\"\",\"intention\":\"0\",\"eduList\":\"\",\"htmlTime\":\"0\",\"gender\":\"2\",\"joinCity\":\"天津市-和平区,天津市-红桥区,天津市-塘沽区,天津市-南开区,天津市-北辰区\",\"postcode\":\"\",\"salary\":\"0\",\"marriage\":\"0\",\"residence\":\"天津市-宁河县\",\"schedule2\":\"0\",\"pdfTime\":\"0\",\"realname\":\"刘雨新\",\"residenceid\":\"120221\",\"workstate\":1,\"joinCityId\":\"120101,120106,120107,120104,120113\",\"pcLevel\":\"0\",\"joinTime\":\"0\",\"rid\":\"4244544\",\"Joblevel\":\"0\",\"joinType\":\"1\",\"languageList\":\"\",\"modTime\":\"1513841856\",\"expireFollowTime\":\"0\",\"isShowPhoto\":\"1\",\"wordTime\":\"0\",\"chkAdminId\":\"0\",\"keywords\":\"\",\"projectList\":\"\",\"stature\":\"\",\"nativeid\":\"120221\",\"updateTime\":\"1513841855\",\"birthday\":\"1994-02-10\",\"avoirdupois\":\"\",\"address\":\"\",\"weixinNo\":\"\",\"trainingList\":\"\",\"cardno\":\"\",\"loginTime\":\"0\",\"views\":\"0\",\"joinJobClass\":\"前台接待/总机/接待生,图书管理员/资料管理员,招聘专员/助理,后勤,质量检验员/测试员\",\"display\":\"1\",\"isAutoRefresh\":\"1\",\"isparttime\":\"0\",\"isShowFullName\":\"1\",\"workYear\":\"2017-07-01\",\"other\":\"\",\"skillList\":\"\",\"certificateList\":\"\",\"joinJobClassId\":\"5015,5017,4817,5016,2814\",\"political\":\"0\",\"attachment\":\"https://pic.597.com/photo/2017/12/21/17122103373635125.jpg\",\"chkJoblevel\":\"0\",\"isIdentity\":\"0\",\"email\":\"\",\"alertTime\":\"0\",\"schedule1\":\"0\",\"joinIndustryId\":\"101201\",\"site\":\"\",\"resumeKeyword\":\"\",\"joinIndustry\":\"石油/石化\",\"chksalary\":\"0\",\"isTop\":\"0\",\"workList\":\"\",\"englishLevel\":\"0\",\"telephone\":\"\",\"joinSalary\":\"3000\",\"otherinfoList\":\"\",\"native\":\"天津市-宁河县\",\"createTime\":\"1513841855\",\"nextTime\":\"0\",\"joinEvaluate\":\"\",\"joinOffice\":\"文职\",\"zuopinList\":\"\",\"banCom\":\"\",\"rTitle\":\"默认简历\",\"jobState\":\"1\",\"imageTime\":\"0\",\"hideSalary\":\"0\",\"cardtype\":\"1\",\"nationality\":\"0\",\"maxEdu\":\"60\",\"adminId\":\"0\",\"_rid\":\"d736864244544\",\"_display\":\"简历公开\",\"age\":\"29\",\"_gender\":\"女\",\"_workYear\":\"5年\",\"maxEduInfo\":\"本科\",\"residenceName\":\"天津市,宁河县\",\"marriageInfo\":\"婚育状况：未填写\",\"nativeName\":\"天津,宁河县\",\"statureInfo\":\"身高/体重:未填写\",\"politicalInfo\":\"政治面貌：未填写\",\"joblevelInfo\":null,\"joinSalaryInfo\":\"3000及以上\",\"_jobState\":\"离职-随时到岗\",\"_joinTime\":null,\"resumePrecent\":25,\"_modTime\":\"2017-12-21\",\"_updateTime\":\"2017-12-21\",\"_updateTimeStr\":\"最近没来过\",\"isUploadAttachment\":1,\"newResume\":0,\"_source\":314.16644,\"_age\":\"29\",\"_degree\":\"本科\",\"_lastVisit\":\"\",\"hasChat\":0,\"collect\":0,\"get_phone\":0,\"chatSign\":\"6904128fSshGbqoLbtvx1yfGl05OnbY35XP6VzMI6qvRzQ7DDDOcENxDRl7XhJ!Ab4zqM16H81GhrNj!zU5/DVU82k4E1h1bQbsDd1CgpdliPCI4hBcHpge1ZyDoHjMSf3Y\",\"ipCity\":\"\"}";
        System.out.println("resume heap value is " + RamUsageEstimator.humanSizeOf(resume));

    }



}
