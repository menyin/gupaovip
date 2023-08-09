package com.cny.cdc;

import lombok.Data;

@Data
public class DataChangeInfo {
    /**
     * 变更类型： 0 初始化 1新增 2修改 3删除 4导致源中的现有表被截断的操作
     */
    private Integer operatorType;
    /**
     * 变更前数据
     */
    private String beforeData;
    /**
     * 变更后数据
     */
    private String afterData;

    /**
     * 操作的数据
     */
    private String data;
    /**
     * binlog文件名
     */
    private String fileName;
    /**
     * binlog当前读取点位
     */
    private Integer filePos;
    /**
     * 数据库名
     */
    private String database;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 变更时间
     */
    private Long operatorTime;

}
