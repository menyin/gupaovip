package com.cny.cdc.domain.companys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年08月01日 11:38
 */
@Data
@AllArgsConstructor
public class CompanyLicenceinfoData {
        private String type;//"3",
        private String cname;//"厦门市思明区荞海餐饮店                                                            ",
        private String _cname;//"厦门市思明区荞海餐饮店",
        private String comTel;//"13950103609",
        private String areaStr;//"福建省-泉州市-丰泽区", //所在地区
        private String comUser;//"阿宝",//招聘联系人
        private String isCheck;//1,//-2执照审核不通过,1执照审核通过,-1000000除了1和-2以外
        private String comMobile;//"13950103609",//招聘联系手机
        private String licenceCheck;//2 //2执照免审,0暂无执照相关信息,-100000除了0和2以外的情况
        private String licences;//2 //2执照免审,0暂无执照相关信息,-100000除了0和2以外的情况
        private String note;//"潜在客户" //
        /*
        //此字段前期因为抓取时_noteTime和note字段数据一样，所以该字段废除
        private String _noteTime;//"？" //
        */

        private String reply;//"传销组织" //
        private String createTime;//"？" //
        private String lastLogin;//"117.136.23.15 湖北 " //
}
