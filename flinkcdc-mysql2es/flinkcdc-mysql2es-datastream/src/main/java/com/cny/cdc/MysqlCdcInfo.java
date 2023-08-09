package com.cny.cdc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mysql-cdc-es")
public class MysqlCdcInfo {
    private String ip;
    private int port;
    private String dbs;
    private String user;
    private String pwd;
    private String tables;
}
