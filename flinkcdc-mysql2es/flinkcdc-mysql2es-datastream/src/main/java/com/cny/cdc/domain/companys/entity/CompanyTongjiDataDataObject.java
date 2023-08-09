package com.cny.cdc.domain.companys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年08月01日 11:37
 */
@Data
@AllArgsConstructor
public class CompanyTongjiDataDataObject{
                    @Field(type = FieldType.Keyword)
                    private String id;//"1184794",//用户id？ 和以下的uid和_uid的区别
                    @Field(type = FieldType.Keyword)
                    private String cid;//"6598528", //公司id截取
                    @Field(type = FieldType.Keyword)
                    private String uid;//"7519178",//应该是_uid的截取
                    @Field(type = FieldType.Keyword)
                    private String _cid;//"ac84bd6598528",//公司id
                    @Field(type = FieldType.Keyword)
                    private String _uid;//"e7d6827519178",//用户id
                    @Field(type = FieldType.Text)
                    private String name;//"张先生",//用户名
    @Field(type = FieldType.Keyword)
                    private String admin;//1, //？
    @Field(type = FieldType.Keyword)
                    private String isVip;//0,//是否vip
    @Field(type = FieldType.Keyword)
                    private String phone;//"13950103609",//联系电话
    @Field(type = FieldType.Keyword)
                    private String photo;//null,//用户头像，要加前缀 http://pic.597.top/hrPhoto/
    @Field(type = FieldType.Keyword)
                    private String _photo;//"//pic.597.com/hrPhoto/default.png",//用户头像
    @Field(type = FieldType.Keyword)
                    private String duties;//"无",//职务
    @Field(type = FieldType.Keyword)
                    private String jobNum;//"0",//职位数量
    @Field(type = FieldType.Keyword)
                    private String wechat;//"否",//是否绑定公众号
    @Field(type = FieldType.Keyword)

                    private String weixin;//"",//微信号
    @Field(type = FieldType.Keyword)

                    private String _status;//"已是管理员",
    @Field(type = FieldType.Long)
                    private String banTime;//"1680481867",//秒为单位，绑定时间？
    @Field(type = FieldType.Integer)
                    private String isAdmin;//"1",//？
    @Field(type = FieldType.Integer)
                    private String isCheck;//"1", //审核通过与否 ？有几种类型
    @Field(type = FieldType.Date,format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd || yyyy/MM/dd HH:mm:ss|| yyyy/MM/dd ||epoch_millis")
                    private String _banTime;//"2023-04-03 08:31:07",//绑定时间？
    @Field(type = FieldType.Keyword)
                    private String isVipStr;//"否",//是否vip
    @Field(type = FieldType.Keyword)
                    private String username;//"AUTO1395010360921", //用户账号
    @Field(type = FieldType.Keyword)
                    private String chat_time;//"",//最近聊天时间
    @Field(type = FieldType.Date,format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd || yyyy/MM/dd HH:mm:ss|| yyyy/MM/dd ||epoch_millis")
                    private String _loginTime2;//"2023-04-04 13:39:09",//最近登录时间
    @Field(type = FieldType.Keyword)
                    private String last_join_time;//"",//最近查看收到的简历时间
    @Field(type = FieldType.Keyword)
    private String app_login_time;//"",//最近登录APP时间
    @Field(type = FieldType.Keyword)
                    private String isShareContract;//"1"//是否共享合同 ？几种

}
