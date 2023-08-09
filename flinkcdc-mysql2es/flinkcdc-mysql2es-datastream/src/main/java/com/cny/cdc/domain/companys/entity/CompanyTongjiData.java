package com.cny.cdc.domain.companys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年08月01日 11:37
 */
@Data
@AllArgsConstructor
public class CompanyTongjiData {
    private CompanyTongjiDataData data;
    private String type;
}
