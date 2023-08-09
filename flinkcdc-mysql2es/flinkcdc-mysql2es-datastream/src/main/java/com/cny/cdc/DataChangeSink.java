package com.cny.cdc;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSONObject;
import com.cny.cdc.domain.companys.CompanyIndex;
import com.cny.cdc.domain.companys.entity.CompanyTongjiDataData;
import com.cny.cdc.domain.companys.entity.CompanyWrapper;
import com.cny.cdc.domain.companys.entity.Json;
import com.cny.cdc.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.table.planner.plan.utils.OperatorType;
import org.springframework.stereotype.Component;
import com.cny.cdc.domain.companys.entity.Company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DataChangeSink implements SinkFunction<DataChangeInfo> {


    @Override
    public void invoke(DataChangeInfo dataChangeInfo, Context context) {

        if (dataChangeInfo.getTableName().equals("company")) {
            // 变更类型： 0 初始化 1新增 2修改 3删除 4导致源中的现有表被截断的操作
            Integer operatorType = dataChangeInfo.getOperatorType();
            String data = dataChangeInfo.getData();
//                String data = "{\"id\":2,\"page\":1,\"page_index\":1,\"reg_date\":\"2007-01-16\",\"json\":{\"_cid\": \"6fe52a6598760\", \"page\": 1, \"type\": \"3\", \"_cname\": \"<a href=\\\"https://www.597.com/com-6fe52a6598760/\\\" target=\\\"_blank\\\"  class=\\\"text-link\\\"><strong>厦门市湖里区淏淞餐饮店</strong></a><br><span><a class=\\\"btn btn-mini\\\" target=\\\"_blank\\\" href=\\\"/companyinfo/companyinfo.html?type=3&act=view&c_id=6fe52a6598760\\\">查看详情</a></span><br><span>执照状态:<font color=\\\"green\\\">已认证</font>(√)</span><br><span>绑定时间:23-04-04 14:08</span>\", \"_source\": \"自注<br><span class=\\\"adminType\\\">开发库</span>\", \"_modTime\": \"23-04-04 14:09/<br>\", \"_regTime\": \"23-04-04 14:00/<br>\", \"vipStyle\": \"normalStyle\", \"_nextType\": \"D类/可跟进\", \"_username\": \"<a href=\\\"/companyinfo/companyinfo.html?type=3&act=view&c_id=6fe52a6598760&uid=85f0137521824#followList\\\" class=\\\"text-link\\\" target=\\\"_blank\\\"><strong>MO168058640030754</strong></a><br/>\", \"comCityId\": \"厦门市\", \"operation\": \"\", \"pageIndex\": 4, \"_followTime\": \"23-04-04 14:20/<br>23-04-18 14:19\", \"adminUsername\": \"庄燕兰\", \"companyTypeStr\": \"新客户\", \"companyViewData\": {\"type\": \"3\", \"cname\": \"厦门市湖里区淏淞餐饮店\", \"comInfo\": \"\\n是一家制作休闲炸串食品的公司，在茶余饭后，休闲的时候来上一份炸串，也是一种不错的享受              \\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\", \"comSite\": \"\", \"comType\": \"民营、私营公司\", \"modTime\": \"23-04-04 14:09:17\", \"regTime\": \"23-04-04 14:00:11\", \"typeStr\": \" \", \"comAreaId\": \" 湖里区\", \"comCityId\": \"厦门市 \", \"loginTime\": \" \", \"comAddress\": \"厦门夸父炸串五缘湾天虹店\", \"comWorkers\": \"10人以下\", \"industryName\": \"餐饮\"}, \"_expireFollowTime\": \"23-07-03 14:08\", \"companyTongjiData\": {\"data\": {\"data\": [{\"id\": \"1185017\", \"cid\": \"6598760\", \"uid\": \"7521824\", \"_cid\": \"6fe52a6598760\", \"_uid\": \"85f0137521824\", \"name\": \"牛志军\", \"admin\": 1, \"isVip\": 0, \"phone\": \"18965853689\", \"photo\": null, \"_photo\": \"//pic.597.com/hrPhoto/default.png\", \"duties\": \"老板\", \"jobNum\": \"0\", \"wechat\": \"否\", \"weixin\": \"\", \"_status\": \"已是管理员\", \"banTime\": \"1680588071\", \"isAdmin\": \"1\", \"isCheck\": \"1\", \"_banTime\": \"2023-04-04 14:01:11\", \"isVipStr\": \"否\", \"username\": \"MO168058640030754\", \"chat_time\": \"\", \"_loginTime2\": \"2023-04-04 13:33:31\", \"app_login_time\": \"\", \"last_join_time\": \"\", \"isShareContract\": \"1\"}], \"totalCounts\": 1}, \"type\": \"3\"}, \"companyJobslistData\": {\"jobs\": [{\"jid\": \"5567776\", \"c_id\": \"6fe52a6598760\", \"jname\": \"服务员（临时工） \"}, {\"jid\": \"5567771\", \"c_id\": \"6fe52a6598760\", \"jname\": \"餐饮服务员 \"}], \"type\": \"3\", \"jobCount\": 0}, \"companyLicenceinfoData\": {\"type\": \"3\", \"cname\": \"厦门市湖里区淏淞餐饮店                                                            \", \"_cname\": \"厦门市湖里区淏淞餐饮店\", \"comTel\": \"18965853689\", \"areaStr\": \"庄燕兰\", \"comUser\": \"牛志军\", \"isCheck\": 1, \"comMobile\": \"18965853689\", \"licenceCheck\": 2}}}";
            log.info("########data={}", data);
            if (operatorType == 0 || operatorType == 1|| operatorType == 2) {

                Company company = null;
                try {
                    CompanyWrapper companyWrapper = JSONObject.parseObject(data, CompanyWrapper.class);
                    Json json = JSONObject.parseObject(companyWrapper.getJson(), Json.class);
                    company = new Company();
                    company.setId(companyWrapper.getId());
                    company.setPage(companyWrapper.getPage());
                    company.setPageIndex(companyWrapper.getPageIndex());
                    company.setRegDate(companyWrapper.getRegDate());
                    company.setJson(json);
                } catch (Exception exception) {
                    System.out.println("company  json反序列化失败. data=" + data);
                    return;
                }

                if (company != null) {
                    try {
                        CompanyIndex companyIndex = company2index(company);
                        CompanyRepository companyRepository = SpringUtil.getBean(CompanyRepository.class);
                        // 初始化/新增/修改（非逻辑删除）
                        companyRepository.save(companyIndex);

                        // 删除/修改（逻辑删除）
                        // bean.delete(systemDept);
                    } catch (Exception exception) {
//                        throw exception;
                        System.out.println("companyIndex 索引保存错误. id=" + company.getId() +",异常信息："+exception);
                    }

                }

            }  else if (operatorType == 3) {
                try {
                    CompanyWrapper companyWrapper = JSONObject.parseObject(data, CompanyWrapper.class);
                    CompanyRepository companyRepository = SpringUtil.getBean(CompanyRepository.class);
                    companyRepository.deleteById(companyWrapper.getId());
                } catch (Exception exception) {
                    System.out.println("company  删除记录失败. data=" + data);
                    return;
                }
            }


        }

    }

    public CompanyIndex company2index(Company company) {

        CompanyIndex companyIndex = new CompanyIndex();

        try {
            companyIndex.setId(company.getId());
            companyIndex.set_cid(company.getJson().get_cid());
        } catch (Exception exception) {
            System.out.println("companyIndex _cid 转化错误. id=" + company.getId() );
        }
        try {
            String source = company.getJson().get_source();//形如：自注<br><span class=\"adminType\">开发库</span>
            if (StringUtils.isNotBlank(source)) {
                String[] sourceArr = source.split("<br><span class=\"adminType\">");
                if(sourceArr.length>1){
                    String sourceArr0 = sourceArr[0];
                    if(StringUtils.isNotBlank(sourceArr0)){
                        companyIndex.setSourceReg(sourceArr0.trim());
                    }
                    String sourceArr1 = sourceArr[1].replace("</span>", "");
                    if(StringUtils.isNotBlank(sourceArr1)){
                        companyIndex.setSourceLib(sourceArr1.trim());
                    }
                }
            }
        } catch (Exception exception) {
            System.out.println("companyIndex source 转化错误. id=" + company.getId() );
        }
        try {

            String[] _modTimeArr = company.getJson().get_modTime().trim().split("/");
            if (_modTimeArr.length > 0) {
                String _modTimeArr0 = "20" + _modTimeArr[0];
                if (StringUtils.isNotBlank(_modTimeArr[0])) {
                    try {
                        companyIndex.set_modTime(stringToDateLong(_modTimeArr0));
                    } catch (ParseException e) {
                        System.out.println("companyIndex _modTime 时间转化错误. id=" + company.getId() + ",originDateStr=" + _modTimeArr0);
                    }
                }

                if (_modTimeArr.length > 1) {
                    String replace = _modTimeArr[1].replace("<br>", "");
                    if (StringUtils.isNotBlank(replace)) {
                        String _modTimeArr1 = "20" + replace;
                        try {
                            companyIndex.set_expireModTime(stringToDateLong(_modTimeArr1));
                        } catch (ParseException e) {
                            System.out.println("companyIndex _expireModTime 时间转化错误. id=" + company.getId() + ",originDateStr=" + _modTimeArr1);
                        }
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("companyIndex _modTimeArr 转化错误. id=" + company.getId());
        }
//        companyIndex.set_modTime();
        try {
            companyIndex.setVipStyle(company.getJson().getVipStyle().trim());
        } catch (Exception exception) {
            System.out.println("companyIndex vipStyle 转化错误. id=" + company.getId());
        }

        try {
            String[] _nextTypeArr = company.getJson().get_nextType().trim().split("/");
            if (_nextTypeArr.length > 0) {
                if (!_nextTypeArr[0].equals("")) {
                    companyIndex.set_nextType(_nextTypeArr[0]);
                }
                if (_nextTypeArr.length > 1) {
                    if (!_nextTypeArr[1].equals("")) {
                        companyIndex.set_followType(_nextTypeArr[1]);
                    }
                }
            }
        } catch (Exception exception) {
            System.out.println("companyIndex _nextTypeArr 转化错误. id=" + company.getId());
        }


        try {
            String[] _followTime = company.getJson().get_followTime().trim().split("/");
            if (_followTime.length > 0) {
                if (StringUtils.isNotBlank(_followTime[0])) {
                    String _followTime0 = "20" + _followTime[0];
                    try {
                        companyIndex.set_followTime(stringToDateLong(_followTime0));
                    } catch (ParseException e) {
                        System.out.println("companyIndex _followTime 时间转化错误. id=" + company.getId() + ",originDateStr=" + _followTime0);
                    }
                }


                if (_followTime.length > 1) {
                    String replace = _followTime[1].replace("<br>", "");
                    if (StringUtils.isNotBlank(replace)) {
                        String _followTime1 = "20" + replace;
                        try {
                            companyIndex.set_visitTime(stringToDateLong(_followTime1));
                        } catch (ParseException e) {
                            System.out.println("companyIndex _visitTime 时间转化错误. id=" + company.getId() + ",originDateStr=" + _followTime1);
                        }
                    }
                }
            }
        } catch (Exception exception) {
            System.out.println("companyIndex _followTime 转化错误. id=" + company.getId());
        }

        try {
            companyIndex.set_id_username(extractUidAndUsername(company.getJson().get_username()).stream().collect(Collectors.joining("丨")));
        } catch (Exception exception) {
            System.out.println("companyIndex _username 转化错误. id=" + company.getId());
        }

        try {
            companyIndex.setAdminUsername(company.getJson().getAdminUsername());
        } catch (Exception exception) {
            System.out.println("companyIndex adminUsername 转化错误. id=" + company.getId());
        }

        try {
            companyIndex.setCompanyTypeStr(company.getJson().getCompanyTypeStr());
        } catch (Exception exception) {
            System.out.println("companyIndex companyTypeStr 转化错误. id=" + company.getId());
        }


        try {
            String expireFollowTime = company.getJson().get_expireFollowTime();
            if (StringUtils.isNotBlank(expireFollowTime)) {
                try {
                    companyIndex.set_expireFollowTime(stringToDateLong("20" + expireFollowTime));
                } catch (ParseException e) {
                    System.out.println("companyIndex _expireFollowTime 时间转化错误. id=" + company.getId() + ",originDateStr=" + company.getJson().get_expireFollowTime());
                }
            }
        } catch (Exception exception) {
            System.out.println("companyIndex expireFollowTime 转化错误. id=" + company.getId());
        }


        try {
            companyIndex.setType(Integer.parseInt(company.getJson().getType()));
        } catch (Exception e) {
            System.out.println("companyIndex type 数字转化错误. id=" + company.getId() + ",originDateStr=" + company.getJson().getType());
        }

        /*公司信息 CompanyViewData  begin*/
        if (company.getJson().getCompanyViewData() != null) {
            try {
                companyIndex.setCname(company.getJson().getCompanyViewData().getCname());
            } catch (Exception exception) {
                System.out.println("companyIndex cname 转化错误. id=" + company.getId());
            }
            try {
                companyIndex.setComInfo(company.getJson().getCompanyViewData().getComInfo());
            } catch (Exception exception) {
                System.out.println("companyIndex comInfo 转化错误. id=" + company.getId());
            }

            try {
                companyIndex.setComSite(company.getJson().getCompanyViewData().getComSite());
            } catch (Exception exception) {
                System.out.println("companyIndex comSite 转化错误. id=" + company.getId());
            }
            try {
                companyIndex.setComType(company.getJson().getCompanyViewData().getComType());
            } catch (Exception exception) {
                System.out.println("companyIndex comType 转化错误. id=" + company.getId());
            }

            try {
                String modTime = company.getJson().getCompanyViewData().getModTime().trim();
                if (StringUtils.isNotBlank(modTime)) {

                    try {
                        companyIndex.setModTime(stringToDateLong("20" + modTime));
                    } catch (ParseException e) {
                        System.out.println("companyIndex modTime 时间转化错误. id=" + company.getId() + ",originDateStr=" + company.getJson().getCompanyViewData().getModTime());
                    }
                }
            } catch (Exception exception) {
                System.out.println("companyIndex modTime 转化错误. id=" + company.getId());
            }


            try {
                String regTime = company.getJson().getCompanyViewData().getRegTime().trim();
                if (StringUtils.isNotBlank(regTime)) {
                    try {
                        companyIndex.setRegTime(stringToDateLong("20" + regTime));
                    } catch (ParseException e) {
                        System.out.println("companyIndex regTime 时间转化错误. id=" + company.getId() + ",originDateStr=" + company.getJson().getCompanyViewData().getRegTime());
                    }
                }
            } catch (Exception exception) {
                System.out.println("companyIndex regTime 转化错误. id=" + company.getId());
            }

            try {
                companyIndex.setTypeStr(company.getJson().getCompanyViewData().getTypeStr());

            } catch (Exception exception) {
                System.out.println("companyIndex typeStr 转化错误. id=" + company.getId());
            }
            try {
                companyIndex.setComAreaId(company.getJson().getCompanyViewData().getComAreaId().trim());
            } catch (Exception exception) {
                System.out.println("companyIndex comAreaId 转化错误. id=" + company.getId());
            }
            try {
                companyIndex.setComCityId(company.getJson().getCompanyViewData().getComCityId().trim());
            } catch (Exception exception) {
                System.out.println("companyIndex comCityId 转化错误. id=" + company.getId());
            }
            try {
                companyIndex.setComAddress(company.getJson().getCompanyViewData().getComAddress());
            } catch (Exception exception) {
                System.out.println("companyIndex comAddress 转化错误. id=" + company.getId());
            }


            try {
                String loginTime = company.getJson().getCompanyViewData().getLoginTime().trim();
                if (StringUtils.isNotBlank(loginTime)&&!loginTime.contains("没有登录")) {
                    try {
                        companyIndex.setLoginTime(stringToDateLong("20"+loginTime.trim()));
                    } catch (ParseException e) {
                        System.out.println("companyIndex loginTime 时间转化错误. id=" + company.getId() + ",originDateStr=" + company.getJson().getCompanyViewData().getLoginTime());
                    }
                }
            } catch (Exception exception) {
                System.out.println("companyIndex loginTime 转化错误. id=" + company.getId());
            }

            try {
                companyIndex.setComWorkers(company.getJson().getCompanyViewData().getComWorkers());
            } catch (Exception exception) {
                System.out.println("companyIndex comWorkers 转化错误. id=" + company.getId());
            }
            try {
                companyIndex.setIndustryName(company.getJson().getCompanyViewData().getIndustryName());
            } catch (Exception exception) {
                System.out.println("companyIndex industryName 转化错误. id=" + company.getId());
            }

            try {
                String replyTime0 = company.getJson().getCompanyViewData().getReplyTime();
                if(StringUtils.isNotBlank(replyTime0)){
                    String replyTime =replyTime0.trim();
                    if (StringUtils.isNotBlank(replyTime)) {
                        try {
                            companyIndex.setReplyTime(stringToDateShort(replyTime));
                        } catch (ParseException e) {
                            System.out.println("companyIndex replyTime 时间转化错误. id=" + company.getId() + ",originDateStr=" + company.getJson().getCompanyViewData().getReplyTime());
                        }
                    }
                }


            } catch (Exception exception) {
                System.out.println("companyIndex replyTime 转化错误. id=" + company.getId());
            }


        }
        /*公司信息 CompanyViewData  end*/

        /*统计信息 CompanyTongjiData  end*/
        // company.json.companyTongjiData.data.data[n].app_login_time
        CompanyTongjiDataData companyTongjiDataData = company.getJson().getCompanyTongjiData().getData();
        try {
            if(Integer.parseInt(companyTongjiDataData.getTotalCounts().trim())>0){
                companyIndex.setCompanyTongjiDataData(companyTongjiDataData);
            }
        } catch (Exception exception) {
            System.out.println("companyIndex companyTongjiDataData 转化错误. id=" + company.getId());
        }

        // 此部分拆分为子集
        /*统计信息 CompanyTongjiData  end*/

        try {
            String jids = company.getJson().getCompanyJobslistData().getJobs().stream().map(job -> {
                return job.getJid();
            }).collect(Collectors.joining("丨"));
            companyIndex.setJids(jids);
        } catch (Exception exception) {
            System.out.println("companyIndex jids 转化错误. id=" + company.getId());
        }
        try {
            String jobKeywords = company.getJson().getCompanyJobslistData().getJobs().stream().map(job -> {
                return job.getJobKeyword();
            }).collect(Collectors.joining("丨"));
            companyIndex.setJobKeywords(jobKeywords);
        } catch (Exception exception) {
            System.out.println("companyIndex jobKeywords 转化错误. id=" + company.getId());
        }
        try {
            String jid_jname_jobKeyword = company.getJson().getCompanyJobslistData().getJobs().stream().map(job -> {
                return job.getJid() + "∞" + job.getJname() + "∞" + job.getJobKeyword();
            }).collect(Collectors.joining("丨"));
            companyIndex.setJid_jname_jobKeyword(jid_jname_jobKeyword);
        } catch (Exception exception) {
            System.out.println("companyIndex jid_jname_jobKeyword 转化错误. id=" + company.getId());
        }
        try {
            companyIndex.setJobCount(Integer.parseInt(company.getJson().getCompanyJobslistData().getJobCount()));

        } catch (Exception exception) {
            System.out.println("companyIndex jobCount 转化错误. id=" + company.getId());
        }

        /*职位信息 CompanyJobslistData  end*/


        /*公司认证信息 CompanyLicenceinfoData  begin*/
        if (company.getJson().getCompanyLicenceinfoData() != null) {
            try {
                companyIndex.setReg_cname(company.getJson().getCompanyLicenceinfoData().getCname());
            } catch (Exception exception) {
                System.out.println("companyIndex reg_cname 转化错误. id=" + company.getId());
            }
            try {
                companyIndex.set_reg_cname(company.getJson().getCompanyLicenceinfoData().get_cname());
            } catch (Exception exception) {
                System.out.println("companyIndex _reg_cname 转化错误. id=" + company.getId());
            }
            try {
                companyIndex.setComTel(company.getJson().getCompanyLicenceinfoData().getComTel());
            } catch (Exception exception) {
                System.out.println("companyIndex comTel 转化错误. id=" + company.getId());
            }
            try {
                companyIndex.setAreaStr(company.getJson().getCompanyLicenceinfoData().getAreaStr());
            } catch (Exception exception) {
                System.out.println("companyIndex areaStr 转化错误. id=" + company.getId());
            }
            try {
                companyIndex.setComUser(company.getJson().getCompanyLicenceinfoData().getComUser());
            } catch (Exception exception) {
                System.out.println("companyIndex comUser 转化错误. id=" + company.getId());
            }
            try {
                companyIndex.setIsCheck(Integer.parseInt(company.getJson().getCompanyLicenceinfoData().getIsCheck().trim()));
            } catch (Exception exception) {
                System.out.println("companyIndex isCheck 转化错误. id=" + company.getId());
            }
            try {
                companyIndex.setComMobile(company.getJson().getCompanyLicenceinfoData().getComMobile());
            } catch (Exception exception) {
                System.out.println("companyIndex comMobile 转化错误. id=" + company.getId());
            }
            try {
                companyIndex.setLicenceCheck(Integer.parseInt(company.getJson().getCompanyLicenceinfoData().getLicenceCheck()));
            } catch (Exception exception) {
                System.out.println("companyIndex licenceCheck 转化错误. id=" + company.getId());
            }
            try {
                companyIndex.setLicences(company.getJson().getCompanyLicenceinfoData().getLicences());
            } catch (Exception exception) {
                System.out.println("companyIndex licences 转化错误. id=" + company.getId());
            }
            try {
                companyIndex.setNote(company.getJson().getCompanyLicenceinfoData().getNote());

            } catch (Exception exception) {
                System.out.println("companyIndex note 转化错误. id=" + company.getId());
            }

           /*
           //此字段前期因为抓取时_noteTime和note字段数据一样，所以该字段废除
           try {
                String _noteTime = company.getJson().getCompanyLicenceinfoData().get_noteTime();
                if (StringUtils.isNotBlank(_noteTime)) {
                    try {
                        companyIndex.set_noteTime(stringToDateLong(_noteTime.trim()));
                    } catch (ParseException e) {
                        System.out.println("companyIndex _noteTime 时间转化错误. id=" + company.getId() + ",originDateStr=" + company.getJson().getCompanyLicenceinfoData().get_noteTime());
                    }
                }
            } catch (Exception exception) {
                System.out.println("companyIndex _noteTime 转化错误. id=" + company.getId());
            }*/

            try {
                companyIndex.setReply(company.getJson().getCompanyLicenceinfoData().getReply());
            } catch (Exception exception) {
                System.out.println("companyIndex reply 转化错误. id=" + company.getId());
            }

            try {
                String createTime0 = company.getJson().getCompanyLicenceinfoData().getCreateTime();
                if(StringUtils.isNotBlank(createTime0)){
                    String createTime=createTime0.trim();
                    try {
                        companyIndex.setCreateTime(stringToDateLong(createTime));
                    } catch (ParseException e) {
                        System.out.println("companyIndex _noteTime 时间转化错误. id=" + company.getId() + ",originDateStr=" + company.getJson().getCompanyLicenceinfoData().getCreateTime());
                    }
                }

            } catch (Exception exception) {
                System.out.println("companyIndex createTime 转化错误. id=" + company.getId());
            }


            try {
                String lastLogin = company.getJson().getCompanyLicenceinfoData().getLastLogin();
                if(StringUtils.isNotBlank(lastLogin)){
                    String[] lastLoginArr = lastLogin.split(" ");
                    if (lastLoginArr.length>0){
                        if(StringUtils.isNotBlank(lastLoginArr[0])){
                            companyIndex.setLastLoginIp(lastLoginArr[0].trim());
                        }
                    }
                    if (lastLoginArr.length>1){
                        if(StringUtils.isNotBlank(lastLoginArr[1])){
                            companyIndex.setLastLoginArea(lastLoginArr[1].trim());
                        }
                    }
                }
            } catch (Exception exception) {
                System.out.println("companyIndex lastLogin 转化错误. id=" + company.getId());
            }

        }

        return companyIndex;
        /*公司认证信息 CompanyLicenceinfoData  end*/

    }


    public static Date stringToDate(String dateString, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(dateString);
    }

    public static Date stringToDateLong(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.parse(dateString);
    }

    public static Date stringToDateShort(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateString);
    }

    /**
     * 提取_username
     *
     * @param html 如："<a href="/companyinfo/companyinfo.html?type=3&act=view&c_id=7a0a51823265&uid=fe8d9d6651347#followList" class="text-link" target="_blank"><strong>AUTO1803004573591</strong></a><br/><a href="/companyinfo/companyinfo.html?type=3&act=view&c_id=7a0a51823265&uid=5fc89d6651701#followList" class="text-link" target="_blank"><strong>AUTO1803004572528</strong></a><br/><a href="/companyinfo/companyinfo.html?type=3&act=view&c_id=7a0a51823265&uid=7a0a51823265#followList" class="text-link" target="_blank"><strong>hk59777</strong></a><br/>"
     * @return
     */
    public static List<String> extractUidAndUsername(String html) {
        List<String> results = new ArrayList<>();
        String regex = "<a\\s+href=\"/companyinfo/companyinfo\\.html\\?type=3&act=view&c_id=(\\w+)&uid=(\\w+)#followList\"\\s+class=\"text-link\"\\s+target=\"_blank\"><strong>(.+?)</strong></a>";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            String uid = matcher.group(2);
            String username = matcher.group(3);
            results.add(uid + ":" + username);
        }
        return results;
    }

}
