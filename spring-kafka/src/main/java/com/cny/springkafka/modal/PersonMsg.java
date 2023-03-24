package com.cny.springkafka.modal;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年03月16日 17:21
 */
@Data
@AllArgsConstructor
public class PersonMsg {
    private String name;
    private String addr;
    private Integer age;
    private Date birth;
    private String discrp;
}
