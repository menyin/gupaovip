package com.cny.cdc.domain.companys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年08月01日 11:34
 */
@Data
@AllArgsConstructor
public class CompanyViewData {
            private String type;//"3",
            private String cname;//"厦门市思明区荞海餐饮店",
            private String comInfo;//"\n              \t\t\t\t\t\t\t\t\t\t\t\t\t",
            private String comSite;//"http://www.baidu.com",
            private String comType;//"民营、私营公司",//公司性质
            private String modTime;//"23-04-03 8:31:05",
            private String regTime;//"23-04-02 23:19:12",
            private String typeStr;//" ",
            private String comAreaId;//" 思明区",
            private String comCityId;//"厦门市 ",
            private String loginTime;//"23-04-04 13:39:09 ",
            private String comAddress;//"",
            private String comWorkers;//"10人以下",
            private String industryName;//"餐饮"

            private String replyTime;//"2019-10-14"
}
