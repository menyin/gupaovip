package com.example.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年03月17日 16:00
 */
@Data
@TableName("company")
@AllArgsConstructor
public class Company implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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
    private Integer page;
}
