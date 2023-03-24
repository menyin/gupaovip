package com.example.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年03月17日 16:00
 */
@Data
@TableName("resume")
@AllArgsConstructor
public class Resume implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private java.lang.String id;
    /**
     * json
     */
    private java.lang.String json;
    /**
     * 注册时间
     */
    private java.lang.String regDate;
    /**
     * 在一页中的序号
     */
    private Integer pageIndex;
    /**
     * 页码
     */
    private Integer page;
}
