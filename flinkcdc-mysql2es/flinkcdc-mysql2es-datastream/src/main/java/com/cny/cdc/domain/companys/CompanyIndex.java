package com.cny.cdc.domain.companys;


import com.cny.cdc.domain.companys.entity.CompanyTongjiDataData;
import com.cny.cdc.domain.companys.entity.CompanyTongjiDataDataObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;
import java.text.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年03月17日 16:00
 */
@Data
@Document(indexName = "company",replicas=1,shards = 1)
@Setting
@NoArgsConstructor
public class CompanyIndex implements Serializable {
    /**
     * id
     */
    @Id
    private String id;//company.id
    @Field(type = FieldType.Keyword)
    private String _cid;//company.json._cid

    /*跟进信息 begin*/
    @Field(type = FieldType.Keyword)
//    private String _source;//为了不和ES的_source重复导致报错，此字段进行拆分2字段并更名
    private String sourceReg;//":"自注<br><span class=\"adminType\">私有库</span>"、"销注<br><span class=\"adminType\">续约库</span>", //company.json._source// //注册来源:0无,1自主注册(自注)、2客服注册（销注）。
    @Field(type = FieldType.Keyword)
//    private String _source;//为了不和ES的_source重复导致报错，此字段更名
    private String sourceLib;//":"自注<br><span class=\"adminType\">私有库</span>"、"销注<br><span class=\"adminType\">续约库</span>", //company.json._source// //库来源：开发库、续约库、私有库
    @Field(type = FieldType.Date,format = DateFormat.date_hour_minute_second)
    private Date _modTime;//":"23-04-03 08:31/<br>24-04-03 08:31"、"19-10-14 14:16/",//company.json._modTime.split("/<br>")[0]  //修改合同时间
    @Field(type = FieldType.Date)
    private Date _expireModTime;//":"23-04-03 08:31/<br>24-04-03 08:31",//company.json._modTime.split("/<br>")[1]  //合同到期时间
    @Field(type = FieldType.Keyword)
    private String vipStyle;//分"normalStyle"、"vipStyle"两类, //company.json.vipStyle
    @Field(type = FieldType.Keyword)
    private String _nextType;//"\/"、"D类/可跟进"、"B类/重要客户"、"/",//company.json._nextType.split("/")[0] //回访类型，分类：A~E类和非E类，分别对应值1~11
    @Field(type = FieldType.Keyword)
    private String _followType;//"\/"、"D类/可跟进"、"B类/重要客户"、"/",//company.json._nextType.split("/")[1] //跟进类型，分类："">请选择 "1">联系不上 "2">可跟进 "3">重要客户 "4">确定办理 "5">无意向 "6">已办理 "7">试用客户 "8">预约见面
    @Field(type = FieldType.Date)
    private Date _followTime;//":"23-04-03 10:08/<br>23-04-17 10:07",//company.json._followTime.split("/<br>")[0] //跟进时间 可能只包含"/"
  /*
    //uid和_username合并成一个字段 形如："123:jack|345:rose"
    @Field(type = FieldType.Text)
    private String uid;//":"<a href=\"/companyinfo/companyinfo.html?type=3&act=view&c_id=ac84bd6598528&uid=e7d6827519178#followList\" class=\"text-link\" target=\"_blank\"><strong>AUTO1395010360921</strong></a><br/><a>。。。</a>",//company.json._username.replace(...)
    @Field(type = FieldType.Text)
    private String _username;//"<a href="/companyinfo/companyinfo.html?type=3&act=view&c_id=7a0a51823265&uid=fe8d9d6651347#followList" class="text-link" target="_blank"><strong>AUTO1803004573591</strong></a><br/><a href="/companyinfo/companyinfo.html?type=3&act=view&c_id=7a0a51823265&uid=5fc89d6651701#followList" class="text-link" target="_blank"><strong>AUTO1803004572528</strong></a><br/><a href="/companyinfo/companyinfo.html?type=3&act=view&c_id=7a0a51823265&uid=7a0a51823265#followList" class="text-link" target="_blank"><strong>hk59777</strong></a><br/>",//有多个，用正则，company.json._username.regx...*/
    @Field(type = FieldType.Text)
    private String _id_username;

    @Field(type = FieldType.Date)
    private Date _visitTime;//":"23-04-03 10:08/<br>23-04-17 10:07",//company.json._followTime.split("/<br>")[1] //回访时间 可能只包含"/"


    @Field(type = FieldType.Text)
    private String adminUsername;//":"曾思雅",//company.json.adminUsername //招聘联系人|跟单员
    @Field(type = FieldType.Keyword)
    private String companyTypeStr;//":"新客户"、"老客户",//company.json.companyTypeStr //客户类型
    @Field(type = FieldType.Date)
    private Date _expireFollowTime;//":"23-07-01 23:32",//跟进释放时间

    /*跟进信息 end*/

    /*公司信息 CompanyViewData  begin*/
    @Field(type = FieldType.Integer)
    private Integer type;//":"3",//company.json.companyViewData.type.  ？
    @Field(type = FieldType.Text)
    private String cname;//":"厦门百度有限公司",//company.json.companyViewData.cname.
    @Field(type = FieldType.Text)
    private String comInfo;//"这是伟大公司描述",//company.json.companyViewData.comInfo.
    @Field(type = FieldType.Text)
    private String comSite;//"http://www.baidu.com/",//company.json.companyViewData.comSite.
    @Field(type = FieldType.Text)
    private String comType;//"中外合资(合营、合作)",//company.json.companyViewData.comType.  //公司性质？
    @Field(type = FieldType.Date)
    private Date modTime;//"23-04-03 8:31:05",//company.json.companyViewData.modTime.
    @Field(type = FieldType.Date)
    private Date regTime;//"23-04-02 23:19:12",//company.json.companyViewData.regTime.
    @Field(type = FieldType.Text)
    private String typeStr;//"名企、",//company.json.companyViewData.typeStr.   ？从<a 。。。>厦门才盛人才服务有限公司</a> (名企、)提取的"名企、"
    @Field(type = FieldType.Text)
    private String comAreaId;//" 思明区",//company.json.companyViewData.comAreaId.  //地址聚合可看【areaStr】字段
    @Field(type = FieldType.Text)
    private String comCityId;//"厦门市 ",//company.json.companyViewData.comCityId.
    @Field(type = FieldType.Text)
    private String comAddress;//"",//company.json.companyViewData.comAddress. //详细地址
    @Field(type = FieldType.Date)
    private Date loginTime;//"23-04-04 13:39:09 ",//company.json.companyViewData.loginTime.//登录时间
    @Field(type = FieldType.Keyword)
    private String comWorkers;//"10人以下",//company.json.companyViewData.comWorkers.  //公司规模 分类："" "请选择","9" "10人以下","49" "10～50人","199" "50～200人","499" "200～500人","999" "500～1000人","1000" "1000人以上","10000" "10000人以上"
    @Field(type = FieldType.Text)
    private String industryName;//"餐饮"//company.json.companyViewData.industryName.
    @Field(type = FieldType.Date)
    private Date replyTime;//"2019-10-14"

    /*公司信息 CompanyViewData  end*/

    /*统计信息 CompanyTongjiData  end*/
        // company.json.companyTongjiData.data.data[n].app_login_time
    @Field(type = FieldType.Object)
    private CompanyTongjiDataData companyTongjiDataData;//"123"//company.json.companyJobslistData.jobCount
        // 此部分拆分为子集
    /*统计信息 CompanyTongjiData  end*/

    /*职位信息 CompanyJobslistData  begin*/
    // 此部分自定义拼接为几个字段
    @Field(type = FieldType.Keyword)
    private String jids;//"123456"//company.json.companyJobslistData.jobs[n].jid 拼接，以汉字丨分割
    @Field(type = FieldType.Text)
    private String jobKeywords;//"销售"//company.json.companyJobslistData.jobs[n].jobKeyword 拼接，以汉字丨分割
    @Field(type = FieldType.Keyword)
    private String jid_jname_jobKeyword;//"12346∞销售总监∞电销丨7894∞销售助理∞电销"//company.json.companyJobslistData.jobs[n].jid+jname+jobKeyword 拼接 ，以汉字丨分割
    @Field(type = FieldType.Integer)
    private Integer jobCount;//"123"//company.json.companyJobslistData.jobCount
    /*职位信息 CompanyJobslistData  end*/


    /*公司认证信息 CompanyLicenceinfoData  begin*/
    //company.json.companyLicenceinfoData.*
    @Field(type = FieldType.Text)
    private String reg_cname;//"厦门市思明区荞海餐饮店 //执照公司名称 reg_cname
    @Field(type = FieldType.Text)//                                                       ",
    private String _reg_cname;//"厦门市思明区荞海餐饮店",//注册公司名称 _reg_cname
    @Field(type = FieldType.Keyword)
    private String comTel;//"13950103609",//招聘联系手机
    @Field(type = FieldType.Text)
    private String areaStr;//"福建省-泉州市-丰泽区", //所在地区
    @Field(type = FieldType.Text)
    private String comUser;//"阿宝",//招聘联系人
    @Field(type = FieldType.Integer)
    private Integer isCheck;//1,//-2执照审核不通过,1执照审核通过,-1000000除了1和-2以外
    @Field(type = FieldType.Keyword)
    private String comMobile;//"13950103609",//公司招聘联系手机
    @Field(type = FieldType.Integer)
    private Integer licenceCheck;//2 //2执照免审,0暂无执照相关信息,-100000除了0和2以外的情况（原来是有0未上传 1已通过 2免审 -1已上传 -2不通过几种）
    @Field(type = FieldType.Text)
    private String licences;//"//pic.597.com/licence/2023/04/12/2304120147417932K.jpg,//pic.597.com/licence/2023/04/12/2304120147519998D.jpg"//营业执照图片
    @Field(type = FieldType.Text)
    private String note;//本公司在海外注册的执照 //执照备注 ？一般都是怎么内容
    /*
    //此字段前期因为抓取时_noteTime和note字段数据一样，所以该字段废除
    @Field(type = FieldType.Date)
    private Date _noteTime;//备注时间 //执照备注 _noteTime*/
    @Field(type = FieldType.Text)
    private String reply;//该执照是假的 //执照审核不过原因
    @Field(type = FieldType.Date)
    private Date createTime;//该执照审核不通过时间 //该执照审核不通过时间 注意：此字段全库查询发现都没值
    /*@Field(type = FieldType.Ip)
    private String lastLogin;//"117.136.23.15 湖北 " //登录IP地址，拆分成lastLoginIp和lastLoginArea两个字段*/

    @Field(type = FieldType.Ip)
    private String lastLoginIp;//"117.136.23.15 湖北 " //登录IP号
    @Field(type = FieldType.Text)
    private String lastLoginArea;//"117.136.23.15 湖北 " //登录IP地址
    /*公司认证信息 CompanyLicenceinfoData  end*/









}
