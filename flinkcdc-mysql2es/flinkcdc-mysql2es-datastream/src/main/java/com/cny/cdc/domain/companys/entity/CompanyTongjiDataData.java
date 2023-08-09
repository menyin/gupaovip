package com.cny.cdc.domain.companys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年08月01日 11:37
 */
@Data
@AllArgsConstructor
public class CompanyTongjiDataData {
    @Field(type = FieldType.Object)
    private ArrayList<CompanyTongjiDataDataObject> data;
    @Field(type = FieldType.Integer)
    private String totalCounts;
}
