package org.example;
import com.alibaba.fastjson.JSONObject;
import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import io.debezium.config.Configuration;
import io.debezium.jdbc.JdbcConfiguration;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.util.Collector;

import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main(String[] args) throws Exception {
        MySqlSource<String> mySqlSource = MySqlSource.<String>builder()
                .hostname("127.0.0.1")
                .port(3306)
                .databaseList("mytest") // monitor all tables under inventory database
                .tableList("mytest.company") // set captured table
                .username("flinkcdc0")
                .password("flinkcdc0")

                .deserializer(new JsonDebeziumDeserializationSchema()) // converts SourceRecord to JSON String
                .build();
        Configuration configuration = new JdbcConfiguration();
        configuration.setInteger(RestOptions.PORT,881);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(configuration);
        // enable checkpoint
        env.enableCheckpointing(60000);  //checkpoint需要什么条件?com/ververica/cdc/connectors/mysql/source/enumerator/MySqlSourceEnumerator.snapshotState()
//启动一个webUI，指定本地WEB-UI端口号

        env.fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "MySQL Source")
                // set 4 parallel source tasks
                .setParallelism(1)
                .process(new ProcessFunction<String, Object>() {
                    @Override
                    public void processElement(String s, ProcessFunction<String, Object>.Context context, Collector<Object> collector) throws Exception {
                        System.out.println(s);
                    }
                });
//                .print("最终数据===>").setParallelism(1); // use parallelism 1 for sink to keep message ordering

        env.execute("MySqlCdcPrint");
    }

    /*public static void main( String[] args )
    {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        Properties properties = new Properties();
        properties.setProperty("hostname", "127.0.0.1");
        properties.setProperty("port", "3306");
        properties.setProperty("database.server.name", "mytest");
        properties.setProperty("table.whitelist", "mytest.company111");
        properties.setProperty("username", "flinkcdc0");
        properties.setProperty("password", "flinkcdc0");
        // Add the "allowPublicKeyRetrieval" property
        properties.setProperty("allowPublicKeyRetrieval", "true");

        SourceFunction<JSONObject> sourceFunction = MySQLSource.<JSONObject>builder()
                .properties(properties)
                .deserializer(new CdcDwdDeserializationSchema()) // converts SourceRecord to String
                .build();

        DataStreamSource<JSONObject> stringDataStreamSource = env.addSource(sourceFunction);
        stringDataStreamSource.print("===>");

        try {
            env.execute("测试mysql-cdc");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
   /* public static void main( String[] args )
    {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        SourceFunction<JSONObject> sourceFunction = MySQLSource.<JSONObject>builder()
                .hostname("127.0.0.1")

                .port(3306)
                .databaseList("mytest") // monitor all tables under inventory database
                .tableList("mytest.company111")

                .username("flinkcdc0")
//                .username("root")
                //.password("abc123")
                .password("flinkcdc0")
//                .password("Cfcny362331,,")
                .deserializer(new CdcDwdDeserializationSchema()) // converts SourceRecord to String
                .build();


        DataStreamSource<JSONObject> stringDataStreamSource = env.addSource(sourceFunction);

        stringDataStreamSource.print("===>");


        try {
            env.execute("测试mysql-cdc");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
