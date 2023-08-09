package com.cny.cdc;

import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class MysqlEventListener implements ApplicationRunner {

    private final DataChangeSink dataChangeSink;
    private final MysqlCdcInfo mysqlCdcInfo;

    @Override
    public void run(ApplicationArguments args) {
        CompletableFuture.runAsync(() -> {
            try {
                StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
                // 设置2个并行源任务
                env.setParallelism(2);
                MySqlSource<DataChangeInfo> mySqlSource = buildDataChangeSource(mysqlCdcInfo);
                DataStream<DataChangeInfo> streamSource = env
                        .fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "mysql-source")
                        //对接收器使用并行1来保持消息的顺序
                        .setParallelism(1);
                streamSource.addSink(dataChangeSink);
                env.executeAsync("mysql-cdc-es");
            } catch (Exception e) {
                log.error("mysql --> es, Exception=", e);
            }
        }).exceptionally(ex -> {
            ex.printStackTrace();
            return null;
        });
    }

    /**
     * 构造变更数据源
     *
     * @return DebeziumSourceFunction<DataChangeInfo>
     */
    private MySqlSource<DataChangeInfo> buildDataChangeSource(MysqlCdcInfo mysqlCdcInfo) {
        return MySqlSource.<DataChangeInfo>builder()
                .hostname(mysqlCdcInfo.getIp())
                .port(mysqlCdcInfo.getPort())
                .databaseList(mysqlCdcInfo.getDbs())
                // 支持正则匹配
                .tableList(mysqlCdcInfo.getTables())
                .username(mysqlCdcInfo.getUser())
                .password(mysqlCdcInfo.getPwd())
                // initial:初始化快照,即全量导入后增量导入(检测更新数据写入)
                .startupOptions(StartupOptions.initial())
                .deserializer(new MysqlDeserialization())
                .serverTimeZone("GMT+8")
                .build();
    }

}
