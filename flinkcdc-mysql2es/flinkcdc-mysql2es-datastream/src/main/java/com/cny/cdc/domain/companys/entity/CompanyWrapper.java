package com.cny.cdc.domain.companys.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年03月17日 16:00
 */
@Data
@AllArgsConstructor
public class CompanyWrapper implements Serializable {
    /**
     * id
     */
    private String id;
    /**
     * json
     */
    private String json;
    /**
     * 注册时间
     */
    private String regDate;
    /**
     * 在一页中的序号
     */

    private Integer pageIndex;
    /**
     * 页码
     */
    private String page;


}
