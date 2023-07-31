/*
package org.example;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.ververica.cdc.debezium.DebeziumDeserializationSchema;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.util.Collector;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;

import java.util.List;

*/
/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年07月29日 14:22
 *//*

public class CdcDwdDeserializationSchema implements DebeziumDeserializationSchema<JSONObject> {
    private static final long serialVersionUID = -3168848963265670603L;

    public CdcDwdDeserializationSchema() {
    }

    @Override
    public void deserialize(SourceRecord record, Collector<JSONObject> out) {
        Struct dataRecord = (Struct) record.value();

        Struct afterStruct = dataRecord.getStruct("after");
        Struct beforeStruct = dataRecord.getStruct("before");
        */
/*
          todo 1，同时存在 beforeStruct 跟 afterStruct数据的话，就代表是update的数据
               2,只存在 beforeStruct 就是delete数据
               3，只存在 afterStruct数据 就是insert数据
         *//*


        JSONObject logJson = new JSONObject();

        String canal_type = "";
        List<Field> fieldsList = null;
        if (afterStruct != null && beforeStruct != null) {
            System.out.println("这是修改数据");
            canal_type = "update";
            fieldsList = afterStruct.schema().fields();
            //todo 字段与值
            for (Field field : fieldsList) {
                String fieldName = field.name();
                Object fieldValue = afterStruct.get(fieldName);
//            System.out.println("*****fieldName=" + fieldName+",fieldValue="+fieldValue);
                logJson.put(fieldName, fieldValue);
            }
        } else if (afterStruct != null) {
            System.out.println("这是新增数据");

            canal_type = "insert";
            fieldsList = afterStruct.schema().fields();
            //todo 字段与值
            for (Field field : fieldsList) {
                String fieldName = field.name();
                Object fieldValue = afterStruct.get(fieldName);
//            System.out.println("*****fieldName=" + fieldName+",fieldValue="+fieldValue);
                logJson.put(fieldName, fieldValue);
            }
        } else if (beforeStruct != null) {
            System.out.println("这是删除数据");
            canal_type = "detele";
            fieldsList = beforeStruct.schema().fields();
            //todo 字段与值
            for (Field field : fieldsList) {
                String fieldName = field.name();
                Object fieldValue = beforeStruct.get(fieldName);
//            System.out.println("*****fieldName=" + fieldName+",fieldValue="+fieldValue);
                logJson.put(fieldName, fieldValue);
            }
        } else {
            System.out.println("一脸蒙蔽了");
        }


        //todo 拿到databases table信息
        Struct source = dataRecord.getStruct("source");
        Object db = source.get("db");
        Object table = source.get("table");
        Object ts_ms = source.get("ts_ms");

        logJson.put("canal_database", db);
        logJson.put("canal_database", table);
        logJson.put("canal_ts", ts_ms);
        logJson.put("canal_type", canal_type);

        //todo 拿到topic
        String topic = record.topic();
        System.out.println("topic = " + topic);

        //todo 主键字段
        Struct pk = (Struct) record.key();
        List<Field> pkFieldList = pk.schema().fields();
        int partitionerNum = 0;
        for (Field field : pkFieldList) {
            Object pkValue = pk.get(field.name());
            partitionerNum += pkValue.hashCode();

        }
        int hash = Math.abs(partitionerNum) % 3;
        logJson.put("pk_hashcode", hash);
        out.collect(logJson);
    }

    @Override
    public TypeInformation<JSONObject> getProducedType() {
        return BasicTypeInfo.of(JSONObject.class);
    }
}
*/
