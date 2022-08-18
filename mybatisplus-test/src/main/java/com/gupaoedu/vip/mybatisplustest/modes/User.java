package com.gupaoedu.vip.mybatisplustest.modes;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2022年02月15日 9:31
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;


    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
