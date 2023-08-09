package com.cny.cdc.domain.companys.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年03月17日 16:00
 */
@Data
@NoArgsConstructor
public class Company implements Serializable {
    /**
     * id
     */
    private String id;
    /**
     * json
     */
    private Json json;
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
